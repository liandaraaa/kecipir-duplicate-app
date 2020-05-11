package com.lianda.kecipirduplicateapp.ui.product.list

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kennyc.view.MultiStateView
import com.lianda.kecipirduplicateapp.R
import com.lianda.kecipirduplicateapp.data.model.Product
import com.lianda.kecipirduplicateapp.depth.service.model.Resource
import com.lianda.kecipirduplicateapp.ui.adapter.ProductAdapter
import com.lianda.kecipirduplicateapp.ui.product.detail.ProductDetailActivity
import com.lianda.kecipirduplicateapp.ui.viewmodel.ProductViewModel
import com.lianda.kecipirduplicateapp.utils.enum.ProductType
import kotlinx.android.synthetic.main.activity_product_list.*
import org.jetbrains.anko.startActivity
import org.koin.android.viewmodel.ext.android.viewModel

class ProductListActivity : AppCompatActivity() {

    companion object {
        const val KEY_PRODUCT_TYPE = "key_product_type"
        const val KEY_SELECTED_CATEGORY = "key_selcted_categroy"

        fun start(context: Context, productType: Int, selectedCategory:String) {
            context.startActivity<ProductListActivity>(
                KEY_PRODUCT_TYPE to productType,
                KEY_SELECTED_CATEGORY to selectedCategory
            )
        }
    }

    private var productType = ProductType.ALL_PRODUCT.type
    private var selectedCategory:String = ""

    private val productViewModel: ProductViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        setSupportActionBar(toolbar)

        productType = intent.getIntExtra(KEY_PRODUCT_TYPE, 2)
        selectedCategory = intent.getStringExtra(KEY_SELECTED_CATEGORY).orEmpty()

        supportActionBar?.title =
            when(productType){
                ProductType.CATEGORY.type -> selectedCategory
                ProductType.POPULAR.type -> getString(R.string.title_popular_product)
                ProductType.ALL_PRODUCT.type -> getString(R.string.title_all_product)
                else -> getString(R.string.title_all_product)
            }
        toolbar.setTitleTextColor(resources.getColor(R.color.colorWhite))

        productViewModel.getProducts()
        observeData()
    }

    private fun observeData() {
        productViewModel.products.observe(this, Observer {
            when (it) {
                is Resource.Loading -> msvProduct.viewState = MultiStateView.ViewState.LOADING
                is Resource.Empty -> msvProduct.viewState = MultiStateView.ViewState.EMPTY
                is Resource.Success -> {
                    msvProduct.viewState = MultiStateView.ViewState.CONTENT
                    showProducts(it.data)
                }
                is Resource.Error -> msvProduct.viewState = MultiStateView.ViewState.ERROR
            }
        })
    }

    private fun showProducts(products: List<Product>) {
        val productAdapter = ProductAdapter(
            context = this,
            datas =  when(productType){
                ProductType.CATEGORY.type -> products.filter { it.grade == selectedCategory }
                ProductType.POPULAR.type -> products.filter { it.rating > 4 }
                else -> products
            },
            onProductClick = { product ->
                toProductDetail(product)
            }
        )

        rvProduct.apply {
            layoutManager = GridLayoutManager(this@ProductListActivity, 3)
            adapter = productAdapter
        }
    }

    private fun toProductDetail(data: Product) {
        ProductDetailActivity.start(
            this,
            data
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}
