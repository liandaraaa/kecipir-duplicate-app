package com.lianda.kecipirduplicateapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.lianda.kecipirduplicateapp.R
import com.lianda.kecipirduplicateapp.data.model.Banner
import com.lianda.kecipirduplicateapp.data.model.Category
import com.lianda.kecipirduplicateapp.data.model.Product
import com.lianda.kecipirduplicateapp.ui.groupie.BannerGroupieItem
import com.lianda.kecipirduplicateapp.ui.groupie.CategoryGroupieItem
import com.lianda.kecipirduplicateapp.ui.groupie.HeaderGroupieItem
import com.lianda.kecipirduplicateapp.ui.groupie.ProductGroupieItem
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val groupieAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun showHomeContent(datas: List<Product>){
        groupieAdapter.add(showBannerItem(datas))
        groupieAdapter.add(showCategoryItem(datas))

        groupieAdapter.add(HeaderGroupieItem("Produk Terlaris"))
        groupieAdapter.add(showProductItem(datas))

        groupieAdapter.add(HeaderGroupieItem("Semua Produk"))
        groupieAdapter.add(showProductItem(datas))

        rvHomeContent.apply { 
            layoutManager = LinearLayoutManager(requireContext())
            adapter = groupieAdapter
        }
    }

    private fun showBannerItem(datas:List<Product>):Group{
        val banners = mutableListOf<Banner>()
        datas.forEach {product->
            val banner = Banner(product.photo, product.title)
            banners.add(banner)
        }
        return BannerGroupieItem(requireContext(),banners)
    }

    private fun showCategoryItem(datas:List<Product>):Group{
        val categories = mutableListOf<Category>()
        datas.forEach {product->
            val category = Category(product.gradeColor, product.grade)
            categories.add(category)
        }
        return CategoryGroupieItem(requireContext(),categories)
    }

    private fun showProductItem(datas: List<Product>):Group{
        return ProductGroupieItem(requireContext(), datas)
    }
}
