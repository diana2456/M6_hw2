package com.example.m6_hw2

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.m6_hw2.databinding.ItemGetBinding

class GetAdapter:RecyclerView.Adapter<GetAdapter.ViewHolder>(){

    private val listGet = arrayListOf<Uri>()

    @SuppressLint("NotifyDataSetChanged")
    fun addPhoto(listTask: List<Uri>) {
        listGet.addAll(listTask)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemGetBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(get: Uri) {
            Glide.with(binding.ivImg).load(get).into(binding.ivImg)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      return ViewHolder(ItemGetBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return listGet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.onBind(listGet[position])
    }
}