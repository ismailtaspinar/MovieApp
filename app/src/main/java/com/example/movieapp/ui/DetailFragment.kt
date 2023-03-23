package com.example.movieapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.movieapp.common.Constants.BASE_IMAGE_SLIDER
import com.example.movieapp.common.Resource
import com.example.movieapp.data.model.Movie
import com.example.movieapp.databinding.FragmentDetailBinding
import kotlin.math.roundToInt


class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel : HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailBinding.inflate(layoutInflater,container,false)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.detailState.collect {
                when(it) {
                    is Resource.Loading -> binding.progress.visibility = View.VISIBLE
                    is Resource.Success -> {
                        loadViews(it.response as Movie)
                        binding.progress.visibility = View.GONE
                    }
                    is Resource.Error -> {
                        Toast.makeText(context,it.throwable.toString(),Toast.LENGTH_SHORT).show()
                        binding.progress.visibility = View.GONE
                    }
                    else -> {

                    }
                }
            }
        }

        return binding.root
    }

    fun loadViews(movie : Movie) {
        Glide.with(requireContext()).load(BASE_IMAGE_SLIDER+movie.backdrop_path).into(binding.image)
        binding.date.text = movie.date
        binding.title.text = movie.title
        binding.nominator.text = ((movie.vote * 10).roundToInt() / 10.0).toString()
        binding.description.text = movie.overview
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}