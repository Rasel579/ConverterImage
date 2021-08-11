package com.test_app.imageconverter.views

import android.graphics.Bitmap
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView {
    fun initListener()
    fun stopLoading()
    fun loading()
    fun showError(t: Throwable)
    fun showImage(image: Bitmap)
}