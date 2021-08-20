package com.test_app.imageconverter.presenter

import com.test_app.imageconverter.converter.Convert
import com.test_app.imageconverter.converter.Schedulers
import com.test_app.imageconverter.converter.imagepicker.DataStore
import com.test_app.imageconverter.views.MainView
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter

class Presenter(private val converter: Convert, private val imagePicker: DataStore) :
    MvpPresenter<MainView>() {
    private val disposable = CompositeDisposable()
    override fun onFirstViewAttach() {
        viewState.initListener()
    }

    fun imagePicked(uri: String?) {
        viewState.loading()
        disposable += imagePicker
            .getData(uri)
            .observeOn(Schedulers.main())
            .subscribe(
                {
                    converter.convert(it)
                        .observeOn(Schedulers.main())
                        .subscribe(
                            viewState::showImage,
                            viewState::showError
                        )
                },
                viewState::showError
            )
    }

    fun stopClicked() {
        disposable.clear()
        viewState.stopLoading()
    }

    override fun onDestroy() {
        disposable.clear()
    }


}