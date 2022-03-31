package com.example.funnymemes.view

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.funnymemes.databinding.ItemRowBinding
import com.example.funnymemes.model.response.MemeResponse

class MemesRecyclerViewAdapter(private val memes: ArrayList<MemeResponse>,private val memeClickListener: (item:MemeResponse) -> Unit) :
        RecyclerView.Adapter<MemesRecyclerViewAdapter.MemesRecyclerViewHolder>(){

        inner class MemesRecyclerViewHolder(private val binding: ItemRowBinding,memeClickListener: (item:MemeResponse) -> Unit): RecyclerView.ViewHolder(binding.root) {
            fun bind(memeResponse: MemeResponse) {
                binding.mealImage.load(memeResponse.memeURL)//using  coil library's load to load the image URL
                binding.mealName.text = memeResponse.memeName

            }

        }
    fun updateList(newMemes: List<MemeResponse>) {
        memes.clear()
        memes.addAll(newMemes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemesRecyclerViewHolder {
       var binding = ItemRowBinding.inflate(
           LayoutInflater.from(parent.context),
           parent,
           false
       )
        return MemesRecyclerViewHolder(binding) {it-> memeClickListener(it)
        }
    }

    override fun onBindViewHolder(holder: MemesRecyclerViewHolder, position: Int) {
        holder.bind(memes[position])
            holder.itemView.setOnClickListener {
                memeClickListener(memes[position])
            }
    }

    override fun getItemCount(): Int {
       return memes.size
    }
}