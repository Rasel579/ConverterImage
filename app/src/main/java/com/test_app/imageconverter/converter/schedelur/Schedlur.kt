package com.test_app.imageconverter.converter

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers.computation

object Schedulers {
    fun io(): Scheduler = io()
    fun main(): Scheduler = AndroidSchedulers.mainThread()
    fun thread(): Scheduler = computation()
}