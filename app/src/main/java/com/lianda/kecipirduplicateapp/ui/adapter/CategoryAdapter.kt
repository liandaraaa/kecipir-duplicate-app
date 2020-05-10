package com.lianda.kecipirduplicateapp.ui.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lianda.kecipirduplicateapp.R
import com.lianda.kecipirduplicateapp.data.model.Category
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter(val context:Context, val datas:List<Category>) :RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.item_category, parent, false))
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class CategoryViewHolder(view:View):RecyclerView.ViewHolder(view){
        fun bind(data:Category){
            with(itemView){
                tvCategory.text = data.category
                tvCategory.setBackgroundColor(Color.parseColor(data.backgroundColor))
            }
        }
    }

}