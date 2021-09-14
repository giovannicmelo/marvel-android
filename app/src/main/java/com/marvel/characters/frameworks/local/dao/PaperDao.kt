package com.marvel.characters.frameworks.local.dao

import android.util.Log
import io.paperdb.Paper

open class PaperDao<T>(private val KEY: String): AbstractDao<T> {

    private val data = try {
        Paper.book().read<T>(KEY)
    } catch (t: Throwable) {
        Paper.book().delete(KEY)
        null
    }

    private fun refresh() {
        val persisted = Paper.book().read<T>(KEY)
        if(persisted != data) Paper.book().write(KEY, data)
    }

    override fun setData(data: T) {
        Log.d(this::class.java.simpleName, "PaperDao Saving $KEY")
        Paper.book().write(KEY, data)
        refresh()
    }

    override fun deleteData() {
        Log.d(this::class.java.simpleName, "PaperDao Deleting $KEY")
        Paper.book().delete(KEY)
        refresh()
    }

    override fun getData(): T? {
        Log.d(this::class.java.simpleName, "PaperDao Reading $KEY")
        return data
    }
}