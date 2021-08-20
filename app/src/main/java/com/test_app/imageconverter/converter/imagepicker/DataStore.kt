package com.test_app.imageconverter.converter.imagepicker

import io.reactivex.rxjava3.core.Single

interface DataStore {
    fun getData(uri: String?): Single<String?>
}