package com.lianda.kecipirduplicateapp.utils

import java.text.SimpleDateFormat
import java.util.*

fun getCurrentDate():String{
    val sdf = SimpleDateFormat("EEE, dd MMMM yyyy", Locale("in", "ID"))
    return sdf.format(Date())
}