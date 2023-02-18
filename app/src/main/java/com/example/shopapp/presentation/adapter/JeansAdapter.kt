package com.example.shopapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.shopapp.R
import com.example.shopapp.databinding.JeanItemBinding
import com.example.shopapp.domain.model.Jeans

class JeansAdapter(private val jeans: List<Jeans>, private val  itemListener: ListenerItem) :
    RecyclerView.Adapter<JeansAdapter.JeansHolder>() {
    class JeansHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding: JeanItemBinding by viewBinding(JeanItemBinding::bind)
        val new = binding.tvNew
        val title = binding.tvTitle
        val price = binding.tvPrice
        val likeBtn = binding.tgFavorite
        val image = binding.ivJean
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JeansHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.jean_item, parent, false)
        return JeansHolder(itemView)
    }

    override fun onBindViewHolder(holder: JeansHolder, position: Int) {
        val jean = jeans[position]
        if (!jean.new!!)
            holder.new.visibility = View.INVISIBLE
        else
            holder.new.visibility = View.VISIBLE
        holder.itemView.setOnClickListener {
            val newJean = jean.copy(favourite = holder.likeBtn.isChecked)
            itemListener.onItemClick(newJean, position)
        }
        Glide.with(holder.itemView).load(jean.image).into(holder.image)
        holder.price.text = "${jean.price} Р"
        holder.title.text = jean.title



        holder.likeBtn.isChecked = jean.favourite!!

        holder.likeBtn.setOnCheckedChangeListener { _, isChecked ->
            holder.likeBtn.setOnClickListener {
                itemListener.onLikeClickItem(jean.id!!, isChecked)
            }
        }
    }
    override fun getItemCount() = jeans.size
    interface ListenerItem {
        fun onLikeClickItem(id: Int, add: Boolean)
        fun onItemClick(jean: Jeans, position: Int)
    }
}