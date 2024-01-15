package ru.acediat.finances.model

import android.content.Context
import android.graphics.drawable.Drawable
import javax.inject.Inject


class AssetsFolder @Inject constructor(
    private val applicationContext: Context,
) {

    private val assets get() = applicationContext.assets

    fun getDrawable(path: String) = assets.open(path).use { inputStream ->
        return@use Drawable.createFromStream(inputStream, null)
    }

    fun getAllIcons() = assets.list(ICONS)?.map { getDrawable(it) } ?: listOf()

    companion object {
        const val ICONS = "icons/"
    }
}