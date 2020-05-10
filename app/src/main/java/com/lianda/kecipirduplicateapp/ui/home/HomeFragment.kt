package com.lianda.kecipirduplicateapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kennyc.view.MultiStateView

import com.lianda.kecipirduplicateapp.R
import com.lianda.kecipirduplicateapp.data.model.Category
import com.lianda.kecipirduplicateapp.data.model.Product
import com.lianda.kecipirduplicateapp.depth.service.model.Resource
import com.lianda.kecipirduplicateapp.ui.groupie.BannerGroupieItem
import com.lianda.kecipirduplicateapp.ui.groupie.CategoryGroupieItem
import com.lianda.kecipirduplicateapp.ui.groupie.HeaderGroupieItem
import com.lianda.kecipirduplicateapp.ui.groupie.ProductGroupieItem
import com.lianda.kecipirduplicateapp.ui.product.detail.ProductDetailActivity
import com.lianda.kecipirduplicateapp.ui.product.list.ProductListActivity
import com.lianda.kecipirduplicateapp.ui.viewmodel.ProductViewModel
import com.lianda.kecipirduplicateapp.utils.getCurrentDate
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val groupieAdapter:GroupAdapter<GroupieViewHolder> by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    private val section :Section by lazy {
        Section()
    }

    private val productViewModel:ProductViewModel by viewModel()

    private var bannerGroupieItem:BannerGroupieItem ? = null
    private var categoryGroupieItem:CategoryGroupieItem ? = null
    private var popularProductGroupieItem:ProductGroupieItem ? = null
    private var allProductGroupieItem:ProductGroupieItem ? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showCurrentDate()
        initGroupie()

        productViewModel.getProducts()
        observeData()

    }

    private fun showCurrentDate(){
        tvDeliveryDate.text = getCurrentDate()
    }

    private fun initGroupie(){
        bannerGroupieItem = BannerGroupieItem(requireContext(), mutableListOf()) { product->
            toProductDetail(product)
        }
        categoryGroupieItem = CategoryGroupieItem(requireContext(), mutableListOf())
        popularProductGroupieItem = ProductGroupieItem(requireContext(), mutableListOf()){ product->
            toProductDetail(product)
        }
        allProductGroupieItem = ProductGroupieItem(requireContext(), mutableListOf()){ product->
            toProductDetail(product)
        }

        initHomeContent()
    }

    private fun initHomeContent(){
        section.add(HeaderGroupieItem(getString(R.string.title_todays_discount)))
        section.add(bannerGroupieItem!!)

        section.add(HeaderGroupieItem(getString(R.string.title_product_category)))
        section.add(categoryGroupieItem!!)

        section.add(HeaderGroupieItem(getString(R.string.title_popular_product)) {
            toProductList(true)
        })
        section.add(popularProductGroupieItem!!)

        section.add(HeaderGroupieItem(getString(R.string.title_all_product)){
            toProductList()
        })
        section.add(allProductGroupieItem!!)

        groupieAdapter.add(section)

        rvHomeContent.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = groupieAdapter
        }
    }

    private fun observeData(){
        productViewModel.products.observe(this, Observer {
            when(it){
                is Resource.Loading -> showHomeLoading()
                is Resource.Empty -> showHomeEmpty()
                is Resource.Success -> showHomeContent(it.data)
                is Resource.Error -> showHomeError()
            }
        })
    }

    private fun showHomeLoading(){
        bannerGroupieItem?.viewState = MultiStateView.ViewState.LOADING
        categoryGroupieItem?.viewState = MultiStateView.ViewState.LOADING
        popularProductGroupieItem?.viewState = MultiStateView.ViewState.LOADING
        allProductGroupieItem?.viewState = MultiStateView.ViewState.LOADING

        groupieAdapter.notifyDataSetChanged()
    }

    private fun showHomeEmpty(){
        bannerGroupieItem?.viewState = MultiStateView.ViewState.EMPTY
        categoryGroupieItem?.viewState = MultiStateView.ViewState.EMPTY
        popularProductGroupieItem?.viewState = MultiStateView.ViewState.EMPTY
        allProductGroupieItem?.viewState = MultiStateView.ViewState.EMPTY

        groupieAdapter.notifyDataSetChanged()
    }

    private fun showHomeError(){
        bannerGroupieItem?.viewState = MultiStateView.ViewState.ERROR
        categoryGroupieItem?.viewState = MultiStateView.ViewState.ERROR
        popularProductGroupieItem?.viewState = MultiStateView.ViewState.ERROR
        allProductGroupieItem?.viewState = MultiStateView.ViewState.ERROR

        groupieAdapter.notifyDataSetChanged()
    }

    private fun showHomeContent(datas: List<Product>){
        bannerGroupieItem?.viewState = MultiStateView.ViewState.CONTENT
        categoryGroupieItem?.viewState = MultiStateView.ViewState.CONTENT
        popularProductGroupieItem?.viewState = MultiStateView.ViewState.CONTENT
        allProductGroupieItem?.viewState = MultiStateView.ViewState.CONTENT

        showBannerItem(datas)
        showCategoryItem(datas)
        showPopularProductItem(datas)
        showAllProductItem(datas)

        groupieAdapter.notifyDataSetChanged()
    }

    private fun showBannerItem(datas:List<Product>){
        val banners = datas.filter { it.discount != "0%" }
        bannerGroupieItem?.add(banners)
    }

    private fun showCategoryItem(datas:List<Product>){
        val categories = mutableListOf<Category>()
        datas.distinctBy { it.grade }.forEach {product->
            val category = Category(product.gradeColor, product.grade)
            categories.add(category)
        }
        categoryGroupieItem?.add(categories)
    }

    private fun showPopularProductItem(datas: List<Product>){
        val popularProducts = datas.filter { it.rating > 4 }
        popularProductGroupieItem?.add(popularProducts)
    }

    private fun showAllProductItem(datas: List<Product>){
        allProductGroupieItem?.add(datas)
    }

    private fun toProductDetail(data:Product){
        ProductDetailActivity.start(requireContext(), data)
    }

    private fun toProductList(isPopularProduct:Boolean = false){
        ProductListActivity.start(requireContext(), isPopularProduct)
    }
}
