package com.example.test.mvvmsampleapp.view.adapter

import android.databinding.BindingAdapter
import android.view.View

@BindingAdapter("visibleGone")
fun showHide(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.GONE
}