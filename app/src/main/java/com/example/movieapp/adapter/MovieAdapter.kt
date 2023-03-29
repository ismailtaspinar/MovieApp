package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.common.Constants.BASE_IMAGE
import com.example.movieapp.data.model.Data
import com.example.movieapp.databinding.MovieItemBinding
import com.example.movieapp.ui.HomeViewModel


class MovieAdapter(private val movieList : List<Data>,private val viewModel: HomeViewModel) : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val movieBinding = MovieItemBinding.inflate(LayoutInflater.from(parent.context))
        val navController = parent.findNavController()
        return MovieHolder(movieBinding,navController,viewModel)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(movieList[position])
    }



    class MovieHolder(val movieBinding: MovieItemBinding,val navController: NavController,
                        val viewModel: HomeViewModel) : RecyclerView.ViewHolder(movieBinding.root) {

        fun bind(movieItem : Data){
            movieBinding.apply {
                data = movieItem
            }
            movieItem.image?.let {
                loadImage(it)
            }

            movieBinding.root.setOnClickListener {
                viewModel.fetchDetails(movieItem.id!!)
                goToDetails()
            }
        }

        fun loadImage(url : String) {
            Glide.with(movieBinding.root).load(BASE_IMAGE+url).into(movieBinding.image)
        }

        fun goToDetails(){
            navController.navigate(R.id.action_homeFragment_to_detailFragment)
        }
    }

}