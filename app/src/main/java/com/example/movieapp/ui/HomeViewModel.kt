package com.example.movieapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.common.Resource
import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.model.Response
import com.example.movieapp.domain.use_case.FetchDetailsUseCase
import com.example.movieapp.domain.use_case.FetchMovieSliderUseCase
import com.example.movieapp.domain.use_case.FetchMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val fetchMovieUseCase: FetchMovieUseCase,
                        private val fetchMovieSliderUseCase: FetchMovieSliderUseCase,
                        private val fetchDetailsUseCase: FetchDetailsUseCase): ViewModel() {



    private val _state = MutableStateFlow<Resource<Response>?>(null)
    val state = _state.asStateFlow()

    private val _sliderState = MutableStateFlow<Resource<Response>?>(null)
    val sliderState = _sliderState.asStateFlow()

    private val _detailState = MutableStateFlow<Resource<Movie>?>(null)
    val detailState = _detailState.asStateFlow()

    private val _pageNumber = MutableLiveData(1)

    private var _totalPage = 0


    fun fetchMovie() = viewModelScope.launch {
        fetchMovieUseCase(_pageNumber.value!!).collect{
            _state.emit(it)
        }
    }

    fun fetchSlider() = viewModelScope.launch {
        fetchMovieSliderUseCase().collect{
            _sliderState.emit(it)
        }
    }

    fun fetchDetails(id : String) = viewModelScope.launch {
        fetchDetailsUseCase(id).collect {
            _detailState.emit(it)
        }
    }

    fun setTotalPage(number:Int){
        _totalPage = number
    }

    fun nextPage(){
        if(_totalPage > _pageNumber.value!!) _pageNumber.value = _pageNumber.value?.plus(1)
    }

    fun resetPage(){
        _pageNumber.value=1
    }

}