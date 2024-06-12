package com.ingenieriajhr.testopencv

import android.graphics.Bitmap
import android.graphics.Color
import org.opencv.android.Utils
import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc


class OpenUtils {


    fun setUtil(bitmap: Bitmap):Bitmap{
        // Realce de bordes
        /*val mat = Mat()

        Utils.bitmapToMat(bitmap,mat)

        Imgproc.cvtColor(mat,mat,Imgproc.COLOR_BGR2GRAY)

        Imgproc.Laplacian(mat,mat, CvType.CV_8U)

        Utils.matToBitmap(mat,bitmap)

        return bitmap*/

        // Filtro negativo
        val width = bitmap.width
        val height = bitmap.height

        val pixels = IntArray(width * height)
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height)

        for (i in pixels.indices) {
            val color = pixels[i]

            val red = 255 - Color.red(color)
            val green = 255 - Color.green(color)
            val blue = 255 - Color.blue(color)

            pixels[i] = Color.argb(Color.alpha(color), red, green, blue)
        }

        val negativeBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        negativeBitmap.setPixels(pixels, 0, width, 0, 0, width, height)

        return negativeBitmap

    }

}