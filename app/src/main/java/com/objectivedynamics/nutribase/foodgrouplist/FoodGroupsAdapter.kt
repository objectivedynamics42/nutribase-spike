package com.objectivedynamics.nutribase.foodgrouplist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.objectivedynamics.nutribase.models.FoodGroup
import com.objectivedynamics.nutribase.R

class RepoViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val name: TextView = view.findViewById(R.id.name)
    fun bind(repo: FoodGroup){
        name.text = repo.name
    }
}

val diffCallback = object:DiffUtil.ItemCallback<FoodGroup>(){
    override fun areItemsTheSame(oldItem: FoodGroup, newItem: FoodGroup): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: FoodGroup, newItem: FoodGroup): Boolean {
        return oldItem == newItem
    }
}
class FoodGroupsAdapter : ListAdapter<FoodGroup,RepoViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return RepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}