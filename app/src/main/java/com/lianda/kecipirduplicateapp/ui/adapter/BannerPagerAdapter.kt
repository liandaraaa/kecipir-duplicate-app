package com.lianda.kecipirduplicateapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.lianda.kecipirduplicateapp.R
import com.lianda.kecipirduplicateapp.data.model.Banner
import com.lianda.topstoryapp.utils.showImageUrl
import kotlinx.android.synthetic.main.item_banner.view.*

class BannerPagerAdapter (val context:Context, val datas:List<Banner>): PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layout = LayoutInflater.from(context).inflate(R.layout.item_banner, container, false)
        container.addView(layout)

        with(layout){
            tvName.text = datas[position].title
            imgBanner.showImageUrl(context, datas[position].photo)
        }

        return container
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return  view == obj
    }

    override fun getCount(): Int {
        return datas.size
    }

}