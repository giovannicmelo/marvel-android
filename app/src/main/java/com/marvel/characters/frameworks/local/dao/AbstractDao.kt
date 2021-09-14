package com.marvel.characters.frameworks.local.dao

interface AbstractDao<T> {

    fun setData(data: T)

    fun deleteData()

    fun getData(): T?
}