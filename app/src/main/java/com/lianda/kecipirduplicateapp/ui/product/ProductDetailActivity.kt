package com.lianda.kecipirduplicateapp.ui.product

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kennyc.view.MultiStateView
import com.lianda.kecipirduplicateapp.R
import com.lianda.kecipirduplicateapp.data.model.Product
import com.lianda.kecipirduplicateapp.depth.service.model.Resource
import com.lianda.kecipirduplicateapp.ui.adapter.ProductAdapter
import com.lianda.kecipirduplicateapp.ui.viewmodel.ProductViewModel
import com.lianda.kecipirduplicateapp.utils.getCurrency
import com.lianda.kecipirduplicateapp.utils.showImageUrl
import kotlinx.android.synthetic.main.activity_product_detail.*
import kotlinx.android.synthetic.main.layout_product.*
import org.jetbrains.anko.share
import org.jetbrains.anko.startActivity
import org.koin.android.viewmodel.ext.android.viewModel

class ProductDetailActivity : AppCompatActivity() {

    companion object {
        private const val PRODUCT_KEY = "prdouct_key"

        fun start(context: Context, product: Product) {
            context.startActivity<ProductDetailActivity>(
                PRODUCT_KEY to product
            )
        }
    }

    private var product: Product? = null

    private val productVieModel: ProductViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        setSupportActionBar(toolbar)
        product = intent.getParcelableExtra(PRODUCT_KEY)

        collapsingToolbar.title = product?.title.orEmpty()

        showProductDetail()

        productVieModel.getProducts()
        observeData()
    }

    private fun showProductDetail() {
        product?.apply {
            imgProduct.showImageUrl(this@ProductDetailActivity, photo)
            tvName.text = title
            tvDiscount.text = discount
            tvPromoPrice.text = getCurrency(promoPrice)
            tvPrice.text = getCurrency(sellPrice)
            tvGrade.text = grade
            tvGrade.setBackgroundColor(Color.parseColor(gradeColor))
            tvFarmer.text = farmer
            tvRating.text = rating.toString()
            ratingProduct.rating = rating.toFloat()
            tvUnit.text = unit
            tvStock.text = stock.toString()
            tvOrganicLevel.text = gradeNote

            tvDiscount.visibility = if (discount != "0%") View.VISIBLE else View.GONE
            tvPromoPrice.visibility = if (discount != "0%") View.VISIBLE else View.GONE
            if (discount != "0%") tvPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }
    }

    private fun observeData() {
        productVieModel.products.observe(this, Observer {
            when (it) {
                is Resource.Loading -> msvProduct.viewState = MultiStateView.ViewState.LOADING
                is Resource.Empty -> {
                    Log.d("product", "empty")
                }
                is Resource.Success -> {
                    msvProduct.viewState = MultiStateView.ViewState.CONTENT
                    showProducts(it.data)
                }
                is Resource.Error -> {
                    Log.d("product", "error ${it.message}")
                }
            }
        })
    }

    private fun showProducts(products: List<Product>) {
        val productAdapter = ProductAdapter(this, products) { product ->
            toProductDetail(product)
        }
        rvProduct.apply {
            layoutManager = LinearLayoutManager(
                this@ProductDetailActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = productAdapter
        }
    }

    private fun toProductDetail(data: Product) {
        start(this, data)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_share, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
            R.id.menuShare -> share()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun share(){
        share(
            text = product?.title.orEmpty(),
            subject = product?.shareLink.orEmpty()
        )
    }
}
