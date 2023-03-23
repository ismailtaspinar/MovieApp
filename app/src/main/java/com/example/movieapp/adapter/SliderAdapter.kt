package com.example.movieapp.adapter

import android.graphics.Point
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movieapp.R
import com.example.movieapp.common.Constants.BASE_IMAGE_SLIDER
import com.example.movieapp.data.model.Data
import com.example.movieapp.databinding.SliderItemBinding
import com.example.movieapp.ui.HomeViewModel
import kotlin.math.roundToInt

class SliderAdapter(private var movieList: List<Data>,private val viewModel: HomeViewModel) : PagerAdapter() {
    override fun getCount(): Int {
        return movieList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = SliderItemBinding.inflate(LayoutInflater.from(container.context))
        binding.data = movieList[position]
        val size = Point()
        val width = (size.x) / 2
        val height = ((size.y) / 2.2).roundToInt()
        Glide
            .with(binding.root)
            .load(BASE_IMAGE_SLIDER+movieList[position].backdrop_path)
            .apply(RequestOptions.overrideOf(width,height))
            .into(binding.image)

        binding.root.setOnClickListener {
            viewModel.fetchDetails(movieList[position].id!!)
            container.findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
        }

        val vp = container as ViewPager
        vp.addView(binding.root, 0)

        return binding.root
    }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)
    }


}