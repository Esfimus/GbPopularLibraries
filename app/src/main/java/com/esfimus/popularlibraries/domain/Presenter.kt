package com.esfimus.popularlibraries.domain

import com.esfimus.popularlibraries.data.CountersModel

class Presenter {

    private val data = CountersModel()

    fun updateValue(id: Int, updateListener: UpdateView) {
        data.setValueById(id)
        updateListener.setButtonText(data.getValueById(id).toString())
    }

}