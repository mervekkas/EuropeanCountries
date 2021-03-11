package com.mvks.europeancountries.util

import android.widget.ImageView
import coil.ImageLoader
import coil.api.load
import coil.decode.SvgDecoder
import coil.request.LoadRequest
import java.util.*

fun ImageView.imageDownload(url: String) {
    if (url.toLowerCase(Locale.ENGLISH).endsWith("svg")) {
        val imageLoader = ImageLoader.Builder(this.context)
            .componentRegistry {
                add(SvgDecoder(context))
            }
            .build()
        val request = LoadRequest.Builder(this.context)
            .data(url)
            .target(this)
            .build()
        imageLoader.execute(request)
    } else {
        this.load(url)
    }

}