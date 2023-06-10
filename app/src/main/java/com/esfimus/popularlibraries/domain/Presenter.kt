package com.esfimus.popularlibraries.domain

import com.esfimus.popularlibraries.data.CountersModel
import moxy.MvpPresenter

class Presenter: MvpPresenter<UpdateView>() {

    private val data = CountersModel()

    fun increaseValue(id: Int) {
        data.setValueById(id)
    }

    fun getValue(id: Int, updateListener: UpdateView) {
        updateListener.setButtonText(data.getValueById(id).toString())
    }

}