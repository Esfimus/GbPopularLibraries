package com.esfimus.popularlibraries.data

class CountersModel {

    private val data = mutableMapOf<Int, Int>()

    fun getValueById(id: Int): Int {
        if (!data.containsKey(id)) data[id] = 0
        return data[id] ?: 0
    }

    fun setValueById(id: Int) {
        if (data.containsKey(id)) {
            data[id] = data[id]!! + 1
        } else {
            data[id] = 0
        }
    }
}