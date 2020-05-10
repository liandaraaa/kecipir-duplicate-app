package com.lianda.kecipirduplicateapp.ui.groupie

import android.content.Context
import com.lianda.kecipirduplicateapp.R
import com.lianda.kecipirduplicateapp.data.model.Banner
import com.lianda.kecipirduplicateapp.data.model.Product
import com.lianda.kecipirduplicateapp.ui.adapter.BannerPagerAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.layout_banner.*

class BannerGroupieItem (val context:Context, val datas:List<Product>, val onBannerClicked:((product:Product)->Unit)? =null ): Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        with(viewHolder){
            val bannerAdapter = BannerPagerAdapter(context, datas, onBannerClicked)
            vpBanner.adapter = bannerAdapter
            bannerIndicator.setViewPager(vpBanner)
        }
    }

    override fun getLayout(): Int = R.layout.layout_banner

}