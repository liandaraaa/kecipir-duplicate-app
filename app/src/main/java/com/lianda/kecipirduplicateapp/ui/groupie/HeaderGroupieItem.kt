package com.lianda.kecipirduplicateapp.ui.groupie

import android.view.View
import com.lianda.kecipirduplicateapp.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_header.*

class HeaderGroupieItem (val title:String, val action:(()->Unit)? = null): Item(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        with(viewHolder){
            tvTitle.text = title
            btnSeeAll.setOnClickListener {
                action?.invoke()
            }
            btnSeeAll.visibility = if(action != null) View.VISIBLE else View.INVISIBLE
        }
    }

    override fun getLayout(): Int = R.layout.item_header

}