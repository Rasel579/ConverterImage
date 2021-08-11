package com.test_app.imageconverter.converter.imagepicker

import android.content.Context

object ImagePickerFactory {
    fun create(context: Context): DataStore = ImagePicker(context)
}