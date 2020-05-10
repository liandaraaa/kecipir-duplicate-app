package com.lianda.kecipirduplicateapp.ui.groupie

import com.xwray.groupie.Item
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder

abstract class BaseGroupieItem<Holder :GroupieViewHolder> : Item<GroupieViewHolder>() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
    }

    abstract fun showLoadingState()
    abstract fun showEmptyState()
    abstract fun showSuccessState()
    abstract fun showErrorState()
}