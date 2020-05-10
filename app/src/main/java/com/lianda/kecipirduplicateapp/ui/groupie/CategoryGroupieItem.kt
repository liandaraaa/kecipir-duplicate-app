package com.lianda.kecipirduplicateapp.ui.groupie

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import com.kennyc.view.MultiStateView
import com.lianda.kecipirduplicateapp.R
import com.lianda.kecipirduplicateapp.data.model.Category
import com.lianda.kecipirduplicateapp.ui.adapter.CategoryAdapter
import com.lianda.kecipirduplicateapp.utils.hideLoading
import com.lianda.kecipirduplicateapp.utils.showLoading
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.layout_category_content.*
import kotlinx.android.synthetic.main.layout_shimmer_category.*

class CategoryGroupieItem (val context: Context, var datas:List<Category>): Item(){

    var viewState = MultiStateView.ViewState.LOADING

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        with(viewHolder){
            msvCategory.viewState = viewState
            val categoryAdapter = CategoryAdapter(context, datas)
            rvCategory.apply {
                layoutManager = GridLayoutManager(context, 3)
                adapter = categoryAdapter
            }
        }
    }

    override fun getLayout(): Int = R.layout.layout_category_content

    fun add(datas: List<Category>){
        this.datas = datas
    }
}