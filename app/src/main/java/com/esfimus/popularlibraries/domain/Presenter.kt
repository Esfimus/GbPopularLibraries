package com.esfimus.popularlibraries.domain

import com.esfimus.popularlibraries.data.CountersModel

class Presenter {

    private val data = CountersModel()

    fun updateValue(id: Int) {
        data.setValueById(id)
    }

    fun getValue(id: Int) = data.getValueById(id)

}