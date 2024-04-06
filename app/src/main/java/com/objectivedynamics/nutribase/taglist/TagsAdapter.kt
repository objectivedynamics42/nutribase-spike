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

class TagsViewHolder(view: View) : RecyclerView.ViewHolder(view){

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

class TagsAdapter(private val tagClickHAndler:(Tag) -> Unit) : ListAdapter<Tag,TagsViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsViewHolder {
        //MikeB - this is invoked once per Tag - before onBindViewHolder is invoked
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tag, parent, false)
        return TagsViewHolder(view)
    }

    override fun onBindViewHolder(holder: TagsViewHolder, position: Int) {
        //MikeB - This is invoked once per Tag - after onCreateViewHolder is invoked
        //MikeB - The position values range from 0 thru len(tags)-1
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener{
            tagClickHAndler(getItem(position))
        }
    }
}
