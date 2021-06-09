package com.example.test_movie.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test_movie.databinding.ItemMovieBinding
import com.example.test_movie.pojos.Results
import com.squareup.picasso.Picasso

class AdapterMovie(
    var listPost: ArrayList<Results>,
    private val onclicListenerHelper: AdapterMovieViewHolder.ModelAdapterOnclicListenerHelper
) : RecyclerView.Adapter<AdapterMovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterMovieViewHolder {
        return AdapterMovieViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return listPost.size
    }

    override fun onBindViewHolder(holder: AdapterMovieViewHolder, position: Int) {
        val item = listPost[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onclicListenerHelper.modelAdapterOnclicListenerHelper(item)
        }
    }

    fun updatelist(list: ArrayList<Results>) {
        listPost = list
        notifyDataSetChanged()
    }
}

class AdapterMovieViewHolder private constructor(var binding: ItemMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): AdapterMovieViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemMovieBinding.inflate(layoutInflater, parent, false)
            return AdapterMovieViewHolder(
                itemBinding
            )
        }
    }

    fun bind(itemParam: Results) {
        if (itemParam.title != null) {
            binding.nombre.text = itemParam.title
        } else {
            binding.nombre.text = "Sin Nombre"
        }
        if (itemParam.vote_average != null) {
            binding.calificacion.text = itemParam.vote_average.toString()
        } else {
            binding.calificacion.text = "Sin Calificación"
        }
        if (itemParam.backdrop_path != null) {
            Picasso.get()
                .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + itemParam.backdrop_path)
                .into((binding.imageView))
        } else {
            Picasso.get()
                .load("https://bangbranding.com/blog/wp-content/uploads/2016/11/700x511_SliderInterior.jpg")
                .into((binding.imageView))
            binding.nombre.text = itemParam.title
            binding.calificacion.text = itemParam.vote_average.toString()
        }
    }

    interface ModelAdapterOnclicListenerHelper {
        fun modelAdapterOnclicListenerHelper(itemparam: Results)
    }
}