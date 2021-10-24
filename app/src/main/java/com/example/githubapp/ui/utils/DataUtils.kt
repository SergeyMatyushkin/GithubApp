package com.example.githubapp.ui.utils

import android.content.Context
import com.example.githubapp.App

val Context.app: App
    get() {
        return  applicationContext as App
    }