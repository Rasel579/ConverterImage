package com.test_app.imageconverter

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.test_app.imageconverter.converter.ConverterFactory
import com.test_app.imageconverter.converter.imagepicker.ImagePickerFactory
import com.test_app.imageconverter.databinding.ActivityMainBinding
import com.test_app.imageconverter.presenter.Presenter
import com.test_app.imageconverter.util.IntentImage
import com.test_app.imageconverter.views.MainView
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {
    private val viewBinding: ActivityMainBinding by viewBinding()
    private var uri: Uri? = null
    private val IMAGE_PICK = 100
    private val presenter: Presenter by moxyPresenter {
        Presenter(
            ConverterFactory.create(),
            ImagePickerFactory.create(this)
        )
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initPermission()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun initPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                baseContext,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 100)
            requestPermissions(arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 100)
        }
    }


    override fun initListener() {
        viewBinding.convertBtn.setOnClickListener {
            startActivityForResult(IntentImage, IMAGE_PICK)
        }
        viewBinding.stop.setOnClickListener { presenter.stopClicked() }
    }

    override fun stopLoading() {
        viewBinding.loadingLayout.visibility = View.GONE
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK) {
            uri = data?.data
            presenter.imagePicked(uri?.toString())
        }
    }


    override fun loading() {
        viewBinding.loadingLayout.visibility = View.VISIBLE
    }

    override fun showError(t: Throwable) {
        Toast.makeText(this, t.message, Toast.LENGTH_LONG).show()
    }

    override fun showImage(image: Bitmap) {
        viewBinding.imageConverter.setImageBitmap(image)
        viewBinding.loadingLayout.visibility = View.GONE
    }
}