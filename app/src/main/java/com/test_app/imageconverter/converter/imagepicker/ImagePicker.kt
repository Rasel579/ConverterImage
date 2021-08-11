package com.test_app.imageconverter.converter.imagepicker

import android.content.Context
import android.provider.MediaStore
import androidx.core.net.toUri
import com.test_app.imageconverter.converter.Schedulers
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

class ImagePicker(private val context: Context) : DataStore {

    override fun getData(uri: String?): Single<String?> {
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        val convertUri = uri?.toUri()
        val cursor =
            convertUri?.let { context.contentResolver.query(it, filePathColumn, null, null, null) }
        cursor?.moveToFirst()

        val columnIndex = cursor?.getColumnIndex(filePathColumn[0])
        val resImage = columnIndex?.let { cursor.getString(it) }
        cursor?.close()
        return Single.just(resImage).subscribeOn(Schedulers.thread()).delay(5, TimeUnit.SECONDS)
    }
}