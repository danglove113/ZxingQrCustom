package com.canh.zxingqrcustom

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setEventsClick()
    }

    private fun setEventsClick() {
        btnDefault.setOnClickListener {
            IntentIntegrator(this).apply {
                setDesiredBarcodeFormats(IntentIntegrator.QR_CODE) //Chọn type của BarCode
                setPrompt("Đây là message thông báo hiển thị trên màn hình capture")
                setCameraId(0) //Id của camera sử dụng để thực hiện scan
                setBeepEnabled(false) //Âm thanh khi thực hiện scan
                setOrientationLocked(false) //cố đinh chiều của camera
                //vân vân và mây mây
                initiateScan() //bắt đầu scan
            }
        }

        btnCustom.setOnClickListener {
            IntentIntegrator(this).apply {
                captureActivity = CustomActivity::class.java
                setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
                setPrompt("Đây là message thông báo hiển thị trên màn hình capture")
                setCameraId(0)
                setBeepEnabled(false)
                setOrientationLocked(false)
                initiateScan()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        result?.let {
            Toast.makeText(this, result.contents, Toast.LENGTH_LONG).show()
        }
    }
}
