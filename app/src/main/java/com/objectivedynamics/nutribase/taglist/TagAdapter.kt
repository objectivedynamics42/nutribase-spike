package com.objectivedynamics.nutribase.taglist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
//TODO  import com.objectivedynamics.nutribase.models.FoodGroup
import com.objectivedynamics.nutribase.R
import com.objectivedynamics.nutribase.models.Tag

class NutritionDataViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val name: TextView = view.findViewById(R.id.name)

    fun bind(tag: Tag){
        name.text = tag.name
    }
}

val diffCallback = object:DiffUtil.ItemCallback<Tag>(){
    override fun areItemsTheSame(oldItem: Tag, newItem: Tag): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Tag, newItem: Tag): Boolean {
        return oldItem == newItem
    }
}
class TagAdapter : ListAdapter<Tag,NutritionDataViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NutritionDataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tag, parent, false)
        return NutritionDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: NutritionDataViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}