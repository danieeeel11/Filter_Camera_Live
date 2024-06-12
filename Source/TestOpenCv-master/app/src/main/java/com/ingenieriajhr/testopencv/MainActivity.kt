package com.ingenieriajhr.testopencv

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.ImageProxy
import com.ingenieriajhr.testopencv.databinding.ActivityMainBinding
import com.ingenieriiajhr.jhrCameraX.BitmapResponse
import com.ingenieriiajhr.jhrCameraX.CameraJhr
import com.ingenieriiajhr.jhrCameraX.ImageProxyResponse
import org.opencv.android.OpenCVLoader
import androidx.activity.result.contract.ActivityResultContracts.*

class MainActivity : AppCompatActivity() {

    public lateinit var binding : ActivityMainBinding
    lateinit var cameraJhr: CameraJhr
    var openUtils = OpenUtils()
    lateinit var btnImage : Button
    lateinit var ivImage : ImageView
    lateinit var btnCamera: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(OpenCVLoader.initDebug()) Log.d("OPENCV2023","TRUE")
        else Log.d("OPENCV2023","INCORRECTO")

        //init cameraJHR
        cameraJhr = CameraJhr(this)

        btnImage = findViewById(R.id.btnImage)
        ivImage = findViewById(R.id.ivImage)

        btnImage.setOnClickListener{
            pickMedia.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))
        }

        btnCamera = findViewById(R.id.btnCamera)
        btnCamera.setOnClickListener {
            openCamera()
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (cameraJhr.allpermissionsGranted() && !cameraJhr.ifStartCamera){
            startCameraJhr()
        }else{
            cameraJhr.noPermissions()
        }
    }

    /**
     * start Camera Jhr
     */
    private fun startCameraJhr() {
        cameraJhr.addlistenerBitmap(object : BitmapResponse {
            override fun bitmapReturn(bitmap: Bitmap?) {
                val newBitmap = openUtils.setUtil(bitmap!!)
                if (bitmap!=null){
                    runOnUiThread {
                        binding.imgBitMap.setImageBitmap(newBitmap)
                    }
                }
            }
        })

        cameraJhr.initBitmap()
        //selector camera LENS_FACING_FRONT = 0;    LENS_FACING_BACK = 1;
        //aspect Ratio  RATIO_4_3 = 0; RATIO_16_9 = 1;  false returImageProxy, true return bitmap
        cameraJhr.start(1,0,binding.cameraPreview,true,false,true)
    }

    /**
     * @return bitmap rotate degrees
     */
    fun Bitmap.rotate(degrees:Float) = Bitmap.createBitmap(this,0,0,width,height,
        Matrix().apply { postRotate(degrees) },true)

    // boton galeria
    val pickMedia = registerForActivityResult(PickVisualMedia()){ uri ->
        if (uri != null){
            val inputStream = contentResolver.openInputStream(uri)
            val bitmap = BitmapFactory.decodeStream(inputStream)

            // Aplicar la utilidad de openUtils al bitmap obtenido de la galería
            val modifiedBitmap = openUtils.setUtil(bitmap)

            ivImage.setImageBitmap(modifiedBitmap)
        } else {
            Log.i("aris", "Ninguna foto seleccionada")
        }
    }

    //boton apertura camara
    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            val modifiedBitmap = openUtils.setUtil(imageBitmap)
            ivImage.setImageBitmap(modifiedBitmap)
        }
    }

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1
    }


}
