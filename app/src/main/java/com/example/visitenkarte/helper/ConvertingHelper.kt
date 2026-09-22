package com.example.visitenkarte.helper

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import java.io.ByteArrayOutputStream

fun drawableResourceToBitmap(
    resources: Resources,
    @DrawableRes resourceid: Int
): Bitmap {
   return BitmapFactory.decodeResource(resources,resourceid)
}

fun imageBitmapFromResources(
    resources: Resources,
    @DrawableRes resourceid: Int
):ImageBitmap{
    return drawableResourceToBitmap(resources,resourceid).asImageBitmap()
}

fun Bitmap.toByteArray(compressionFormat: Bitmap.CompressFormat = Bitmap.CompressFormat.PNG):ByteArray{
    val baos = ByteArrayOutputStream()
    compress(
        compressionFormat,
        90,
        baos
    )
    return baos.toByteArray()
}

fun ByteArray.toBitmap():Bitmap{
    return BitmapFactory.decodeByteArray(this,0,size)
}