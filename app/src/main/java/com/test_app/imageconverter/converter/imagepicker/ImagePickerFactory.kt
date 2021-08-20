package com.test_app.imageconverter.converter.imagepicker

import android.content.Context
import com.test_app.imageconverter.converter.Schedulers

object ImagePickerFactory {
    fun create(context: Context): DataStore = ImagePicker(context, Schedulers)
}