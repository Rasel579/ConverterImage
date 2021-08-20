package com.test_app.imageconverter.converter

object ConverterFactory {
    fun create(): Convert = ConverterImpl(Schedulers)
}