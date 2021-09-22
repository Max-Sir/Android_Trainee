package com.coherentsolutions.by.max.sir.androidtrainingtasks.home.utils

import android.view.View
import com.mikhaellopez.circularprogressbar.CircularProgressBar

class ActivityProgressBar(val progressBar: CircularProgressBar) {

    init {
        progressBar.onIndeterminateModeChangeListener = { progress ->
            if (progress) {
                progressBar.animate()
            }
        }
    }

    fun show() {
        progressBar.visibility = View.VISIBLE
        progressBar.indeterminateMode = true
    }

    fun hide() {
        progressBar.indeterminateMode = false
        progressBar.visibility = View.GONE
    }
}