package com.kunal.actmobile.utils

import android.widget.ImageView
import com.bumptech.glide.Glide


fun ImageView.loadImage(imageUrl: String) {
    Glide.with(this.context).load(imageUrl).timeout(30000).into(this)
}