package com.example.movieapp.ui


import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.PagerAdapter
import com.example.movieapp.adapter.MovieAdapter
import com.example.movieapp.adapter.SliderAdapter
import com.example.movieapp.common.Resource
import com.example.movieapp.data.model.Data
import com.example.movieapp.data.model.Response
import com.example.movieapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import me.relex.circleindicator.CircleIndicator


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel : HomeViewModel by activityViewModels()
    private var movieList = mutableListOf<Data>()
    private var movieSliderList = mutableListOf<Data>()
    private var isLoading = false
    private lateinit var mLayoutManager: LinearLayoutManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater,container,false)

        if(movieList.isEmpty() && movieSliderList.isEmpty()){
            viewModel.fetchMovie()
            viewModel.fetchSlider()
        }

        activity?.window?.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }

        mLayoutManager = LinearLayoutManager(context)
        binding.movieRecycler.layoutManager = mLayoutManager
        binding.movieRecycler.isNestedScrollingEnabled = false
        binding.movieRecycler.adapter = MovieAdapter(movieList,viewModel)
        binding.viewpager.adapter = SliderAdapter(movieSliderList,viewModel)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect{
                when(it){
                    is Resource.Loading -> {
                        binding.refresh.isRefreshing = true
                        isLoading = true
                    }
                    is Resource.Success -> {
                        movieList.addAll((it.response as Response).data)
                        binding.movieRecycler.adapter = MovieAdapter(movieList,viewModel)
                        viewModel.setTotalPage(it.response.total_pages.toInt())
                        binding.refresh.isRefreshing = false
                        isLoading = false
                    }
                    is Resource.Error -> {
                        Toast.makeText(context,it.throwable.toString(),Toast.LENGTH_SHORT).show()
                        binding.refresh.isRefreshing = false
                        isLoading = false
                    }
                    else -> { }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.sliderState.collect{
                when(it) {
                    is Resource.Loading -> {
                        binding.refresh.isRefreshing = true
                        isLoading = true
                    }
                    is Resource.Success -> {
                        movieSliderList.addAll((it.response as Response).data)
                        binding.viewpager.adapter = SliderAdapter(movieSliderList,viewModel)
                        val indicator = binding.indicator as CircleIndicator
                        indicator.setViewPager(binding.viewpager)
                        binding.refresh.isRefreshing = false
                        isLoading = false
                    }
                    is Resource.Error -> {
                        Toast.makeText(context,it.throwable.toString(),Toast.LENGTH_SHORT).show()
                        binding.refresh.isRefreshing = false
                        isLoading = false
                    }
                    else -> null
                }

            }
        }

        binding.nestedScroll.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if ((scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) && !isLoading) {
                isLoading = true
                viewModel.nextPage()
                viewModel.fetchMovie()
            }
        })

        binding.refresh.setOnRefreshListener {
            binding.refresh.isRefreshing = true
            viewModel.resetPage()
            movieList.clear()
            movieSliderList.clear()
            viewModel.fetchSlider()
            viewModel.fetchMovie()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}