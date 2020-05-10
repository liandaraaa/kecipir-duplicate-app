package com.lianda.kecipirduplicateapp.ui.groupie

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.lianda.kecipirduplicateapp.R
import com.lianda.kecipirduplicateapp.data.model.Product
import com.lianda.kecipirduplicateapp.ui.adapter.ProductAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.layout_product.*

class ProductGroupieItem (val context:Context, val datas:List<Product>): Item(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        with(viewHolder){
            val productAdapter = ProductAdapter(context, datas)
            rvProduct.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = productAdapter
            }
        }
    }

    override fun getLayout(): Int = R.layout.layout_product

}