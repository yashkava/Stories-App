package com.example.story.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

import com.example.story.model.StoryType
import com.example.story.R
import com.example.story.view.MainActivity

class StoryAdapter(val context: MainActivity, private val storyList:ArrayList<StoryType>): RecyclerView.Adapter<StoryAdapter.storyViewHolder>() {
    private lateinit var mListener: onItemClickListener
    interface  onItemClickListener{

        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }
    inner class storyViewHolder(v :View,listener: onItemClickListener): RecyclerView.ViewHolder(v){
        val name = v.findViewById<TextView>(R.id.rvtextView)!!
        val image = v.findViewById<ImageView>(R.id.rvimageView)!!
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): storyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.item_card,parent,false)
        return storyViewHolder(v,mListener)
    }


    override fun onBindViewHolder(holder: storyViewHolder, position: Int)  {

        val newList = storyList[position]
        holder.name.text = newList.storyName
        holder.image.setImageResource(newList.storyImg)

    }

    override fun getItemCount(): Int {
        return storyList.size
    }
}