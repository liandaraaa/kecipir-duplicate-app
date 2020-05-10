package com.lianda.kecipirduplicateapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.lianda.kecipirduplicateapp.R
import com.lianda.kecipirduplicateapp.data.model.Product
import com.lianda.kecipirduplicateapp.utils.showImageUrl
import kotlinx.android.synthetic.main.item_banner.view.*

class BannerPagerAdapter (val context:Context, val datas:List<Product>, val onBannerClicked:((product:Product)->Unit)? =null): PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layout = LayoutInflater.from(context).inflate(R.layout.item_banner, container, false)

        with(layout){
            tvName.text = datas[position].title
            tvDiscount.text = datas[position].discount
            imgBanner.showImageUrl(context, datas[position].photo)

            setOnClickListener {
                onBannerClicked?.invoke(datas[position])
            }
        }

        container.addView(layout)
        return layout
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return  view == obj
    }

    override fun getCount(): Int {
        return datas.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

}