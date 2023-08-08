package com.example.surftestproject.data

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.widget.AppCompatImageButton
import java.io.ByteArrayOutputStream

class ImageDecoder {
    companion object {
        fun toByteArray(image: AppCompatImageButton): ByteArray {
            val bitmap = (image.drawable as BitmapDrawable).bitmap
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream)
            return stream.toByteArray()
        }

        fun toBitmap(item: ByteArray): Bitmap {
            return BitmapFactory.decodeByteArray(item, 0, item.size)
        }
    }
}