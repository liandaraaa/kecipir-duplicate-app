package com.lianda.kecipirduplicateapp.utils

import java.text.NumberFormat
import java.util.*

fun getCurrency(price:String,locale: Locale = Locale("in", "ID")):String{
    val currencyFormat: NumberFormat = NumberFormat.getCurrencyInstance(locale)
    return currencyFormat.format(price.toDouble())
}