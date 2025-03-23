package com.anhndt.quotedetail.ui.viewmodel

import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuoteDetailViewModel @Inject constructor(
) : ViewModel() {

    // Helper function to calculate inSampleSize for efficient decoding
    private fun calculateInSampleSize(
        options: BitmapFactory.Options,
        reqWidth: Int = 1080,
        reqHeight: Int = 1920
    ): Int {
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1
        if (height > reqHeight || width > reqWidth) {
            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2
            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }
}
