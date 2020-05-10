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
import kotlinx.android.synthetic.main.activity_product_list.*
import org.jetbrains.anko.startActivity
import org.koin.android.viewmodel.ext.android.viewModel

class ProductListActivity : AppCompatActivity() {

    companion object {
        const val KEY_PRODUCT_TYPE = "key_product_type"

        fun start(context: Context, isPopularProduct: Boolean) {
            context.startActivity<ProductListActivity>(
                KEY_PRODUCT_TYPE to isPopularProduct
            )
        }
    }

    private var isPopularProduct:Boolean = false

    private val productViewModel: ProductViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        setSupportActionBar(toolbar)

        isPopularProduct = intent.getBooleanExtra(KEY_PRODUCT_TYPE, false)
        supportActionBar?.title = if (isPopularProduct) getString(R.string.title_popular_product)
        else getString(R.string.title_all_product)
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
        if (isPopularProduct) products.filter { it.rating > 4 }
        val productAdapter = ProductAdapter(
            context = this,
            datas = products,
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
