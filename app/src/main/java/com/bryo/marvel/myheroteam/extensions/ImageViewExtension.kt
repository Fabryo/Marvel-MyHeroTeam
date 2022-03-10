package com.bryo.marvel.myheroteam.extensions

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy.ALL
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL

fun ImageView.loadImageUrlAsBadge(image: String, placeholder: Drawable? = null) {

    var options: RequestOptions = RequestOptions()
        .diskCacheStrategy(ALL)
        .encodeFormat(Bitmap.CompressFormat.PNG)
        .fitCenter()

    if (placeholder != null) {
        options = options.placeholder(placeholder)
    }
    options = options.circleCrop()

    Glide.with(this)
        .load(image)
        .apply(options)
        .into(this)
}

fun ImageView.loadImageUrlAsToolbarBackground(image: String, placeholder: Drawable? = null) {

    var options: RequestOptions = RequestOptions()
        .override(SIZE_ORIGINAL, SIZE_ORIGINAL)
        .encodeFormat(Bitmap.CompressFormat.PNG)

    if (placeholder != null) {
        options = options.placeholder(placeholder)
    }

    Glide.with(this)
        .load(image)
        .apply(options)
        .into(this)
}