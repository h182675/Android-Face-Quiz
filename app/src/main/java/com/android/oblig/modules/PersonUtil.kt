package com.android.oblig.modules

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ThumbnailUtils
import java.io.ByteArrayOutputStream

object PersonUtil {

    const val THUMBSIZE = 48;

    fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 30, stream)
        return stream.toByteArray()
    }

    fun byteArrayToBitmap(picture: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(picture, 0, picture.size)
    }

    fun byteArrayToThumbnail(picture: ByteArray): Bitmap{

        return ThumbnailUtils.extractThumbnail(BitmapFactory.decodeByteArray(picture, 0, picture.size),THUMBSIZE,THUMBSIZE)
    }
}