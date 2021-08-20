package com.test_app.imageconverter.converter

import android.graphics.Bitmap
import io.reactivex.rxjava3.core.Single

interface Convert {
    fun convert(uri: String?): Single<Bitmap>
}