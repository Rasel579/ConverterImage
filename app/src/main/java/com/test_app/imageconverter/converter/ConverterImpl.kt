package com.test_app.imageconverter.converter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import io.reactivex.rxjava3.core.Single
import java.io.FileOutputStream

class ConverterImpl : Convert {
   override fun convert(uri: String?) : Single<Bitmap> {
       val img = BitmapFactory.decodeFile(uri)
           FileOutputStream(uri).use {
           img.compress(Bitmap.CompressFormat.PNG, 100, it)
           it.write(img.rowBytes)
      return Single.just(img).subscribeOn(Schedulers.thread())
     }
   }

}