package com.lianda.kecipirduplicateapp.ui.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lianda.kecipirduplicateapp.R
import com.lianda.kecipirduplicateapp.data.model.Product
import com.lianda.kecipirduplicateapp.utils.getCurrency
import com.lianda.kecipirduplicateapp.utils.showImageUrl
import kotlinx.android.synthetic.main.item_product.view.*

class ProductAdapter(
    val context:Context,
    val datas:List<Product>,
    val onProductClick:((data:Product)->Unit)? = null,
    val type:String = ALL_PRODUCT
) :RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){

    companion object{
        const val ALL_PRODUCT = "all_product"
        const val FEW_PRODUCT = "few_product"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(LayoutInflater.from(context).inflate(R.layout.item_product, parent, false))
    }

    override fun getItemCount(): Int = if(type == ALL_PRODUCT) datas.size else 5

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ProductViewHolder(view:View):RecyclerView.ViewHolder(view){
        fun bind(data:Product){
            with(itemView){
                imgProduct.showImageUrl(context, data.photo)
                tvName.text = data.title
                tvDiscount.text = data.discount
                tvPrice.text = getCurrency(data.sellPrice)
                tvPromoPrice.text = getCurrency(data.promoPrice)
                ratingProduct.rating = data.rating.toFloat()

                tvDiscount.visibility = if(data.discount != "0%") View.VISIBLE else View.GONE
                if(data.discount != "0%") tvPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

                setOnClickListener {
                    onProductClick?.invoke(data)
                }
            }
        }
    }

}