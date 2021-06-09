package com.example.test_movie.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.test_movie.databinding.FragmentMovieDetailsBinding
import com.squareup.picasso.Picasso

class MovieDetails : Fragment() {
    private val args: MovieDetailsArgs by navArgs()
    private lateinit var binding: FragmentMovieDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        init()
        binding.contentDetails.title.text = args.movie!!.title
        binding.contentDetails.calificacion.text = args.movie!!.vote_average.toString()
        binding.contentDetails.estreno.text = args.movie!!.release_date
        binding.contentSinopsis.sinopsis.text = args.movie!!.overview
        Toast.makeText(
            requireContext(),
            "vysor ad in 10 seconds upgrade for an ad free experience",
            Toast.LENGTH_SHORT
        ).show()
        binding.contentImage.back.setOnClickListener {
            val action = MovieDetailsDirections.actionMovieDetailsToMovieFragment()
            findNavController().navigate(action)
        }
        return binding.root
    }

    private fun init() {
        Picasso.get()
            .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + args.movie!!.backdrop_path)
            .into((binding.contentImage.imageView3))
    }
}