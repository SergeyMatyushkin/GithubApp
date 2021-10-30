package com.example.githubapp.ui.utils

import android.content.Context
import android.widget.ImageView
import com.example.githubapp.App
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

val Context.app: App
    get() {
        return  applicationContext as App
    }