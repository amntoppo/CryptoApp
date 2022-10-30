package io.aman.cyptoapp.utils

import android.widget.ImageView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import io.aman.cyptoapp.R

const val EMPTY = "empty"
const val VALUE = "value"


fun ImageView.loadUrl(url: String) {

    val imageLoader = ImageLoader.Builder(this.context)
        .componentRegistry { add(SvgDecoder(this@loadUrl.context)) }
        .build()

    val request = ImageRequest.Builder(this.context)
        .crossfade(true)
        .crossfade(500)
        .placeholder(R.drawable.ic_bitcoin)
        .data(url)
        .target(this)
        .build()

    imageLoader.enqueue(request)
}