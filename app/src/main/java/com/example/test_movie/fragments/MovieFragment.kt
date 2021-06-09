package com.example.test_movie.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.test_movie.ViewModels.MovieViewModel
import com.example.test_movie.ViewModels.MovieViewModelFactory
import com.example.test_movie.adapters.AdapterMovie
import com.example.test_movie.adapters.AdapterMovieViewHolder
import com.example.test_movie.databinding.FragmentMovie2Binding
import com.example.test_movie.datasource.UtilsNetwork
import com.example.test_movie.pojos.Results
import com.example.test_movie.repository.Repository
import com.example.test_movie.retrofit.ServiceApi
import java.util.*

class MovieFragment : Fragment(), AdapterMovieViewHolder.ModelAdapterOnclicListenerHelper {
    private lateinit var binding: FragmentMovie2Binding
    private lateinit var model: MovieViewModel
    private lateinit var movieModel: MovieViewModel
    private var factory: MovieViewModelFactory? = null
    val adapter = AdapterMovie(arrayListOf(), this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovie2Binding.inflate(inflater, container, false)
        //Instanseando
        factory = MovieViewModelFactory(Repository(ServiceApi().getUserApi()))
        movieModel = ViewModelProvider(this, factory!!).get(MovieViewModel::class.java)
        model = ViewModelProvider(this).get(MovieViewModel::class.java)
        observers()
        initMovie()

        return binding.root

    }

    private fun initMovie() {
        model.movieFromServer.value?.clear()
        movieModel.getInfoMovieUser("93e2ba3b2b8eeac423224f7798b294a4")
        binding.Recycler.adapter = adapter
    }

    private fun observers() {
        //MOVIE
        movieModel.movieInfoSuccessResponse.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                movieModel.movieFromServer.value = it.results as ArrayList<Results>
            } else {
                model.movieFromServer.value = arrayListOf()
            }
        })
        movieModel.movieBadResponse.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }
        })
        model.movieFromServer.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                adapter.updatelist(it)
                Toast.makeText(requireContext(), "" + it.size, Toast.LENGTH_SHORT).show()
            }
        })
        movieModel.networksState.observe(viewLifecycleOwner, Observer {
            if (it.status == UtilsNetwork.RUNNING) {
                binding.progressBar.visibility = View.VISIBLE
                binding.progressBar.isShown
            }
            if (it.status == UtilsNetwork.SUCCESS || it.status == UtilsNetwork.FAILED) {
                binding.progressBar.visibility = View.GONE
            }
        })
    }

    override fun modelAdapterOnclicListenerHelper(itemparam: Results) {
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetails(itemparam)
        findNavController().navigate(action)
    }
}