package com.lambdaschool.m04servicesandbroadcasts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var imageDownloadReciever: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_download.setOnClickListener {
            val intent = Intent(this, LargeImageDownloadService::class.java)
            this.startService(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        imageDownloadReciever = object: BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent?.action == LargeImageDownloadService.FILE_DOWNLOADED_ACTION) {
                    val bitmap = intent.getParcelableExtra<Bitmap>(LargeImageDownloadService.DOWNLOADED_IMAGE)
                    iv_image.setImageBitmap(bitmap)
                }
            }
        }

        val intentFilter = IntentFilter().apply {
            addAction(LargeImageDownloadService.FILE_DOWNLOADED_ACTION)
        }

        LocalBroadcastManager.getInstance(this).registerReceiver(imageDownloadReciever, intentFilter)
    }

    override fun onPause() {
        super.onPause()

        this.unregisterReceiver(imageDownloadReciever)
    }
}
