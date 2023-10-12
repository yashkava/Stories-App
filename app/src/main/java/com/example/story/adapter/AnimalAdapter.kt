package com.example.story.adapter



import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.story.model.AnimalType
import com.example.story.R

class AnimalAdapter(private val animList:ArrayList<AnimalType>, private val context: Context):
    RecyclerView.Adapter<AnimalAdapter.MyViewHolder>() {
    private lateinit var mListener: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setonItemClickListener(listener: onItemClickListener){
        mListener = listener
    }
    class MyViewHolder(itemView: View,listener: onItemClickListener):RecyclerView.ViewHolder(itemView) {
        val titlev: TextView = itemView.findViewById(R.id.tvAnimal)

        val imagev: ImageView = itemView.findViewById(R.id.ivAnimal)

        init {

            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }

        }
    }
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)
        return MyViewHolder(itemView,mListener)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.titlev.text = animList[position].title
        //holder.briefv.text = animList[position].brief
        Glide.with(context).load(animList[position].image).into(holder.imagev)
    }

    override fun getItemCount(): Int {
        return animList.size
    }




}