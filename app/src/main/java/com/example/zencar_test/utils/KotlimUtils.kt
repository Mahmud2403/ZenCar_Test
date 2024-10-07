package com.example.zencar_test.utils

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore

fun Boolean.toggle() = !this


fun Uri.toBitmap(contentResolver: ContentResolver): Bitmap? {
    var bitmap: Bitmap? = null
    try {
        bitmap = if (Build.VERSION.SDK_INT < 28) {
            MediaStore.Images.Media.getBitmap(
                contentResolver,
                this
            )
        } else {
            val source =
                ImageDecoder.createSource(
                    contentResolver,
                    this
                )
            ImageDecoder.decodeBitmap(source)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return bitmap
}
