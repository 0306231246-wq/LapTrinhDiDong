package com.example.myapplication

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import kotlinx.serialization.json.Json
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.getSystemService
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text
import android.content.Context
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private val Channel_ID="Channel_ID_01"
    private val NOTIFICATION_ID=1
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        createNotificationChannel()
        val jsonText = """{
            |"Datas": [{
            |"id": 1, 
            |"name": "Samsung Galaxy S25 Ultra",
            | "descriptionText": "$1000"}, {"id": 2, "name": "Iphone 17 Promax", "descriptionText": "2000$"},{"id": 3, "name": "Iphone 15 Promax", "descriptionText": "500$"},{"id": 4, "name": "Iphone 16 Promax", "descriptionText": "900$"}]}""".trimMargin()
        val dataObject = Json.decodeFromString<Datas>(jsonText)
        val Item = dataObject.Datas
        findViewById<Button>(R.id.btnShowNotification).setOnClickListener {
            val randomIndex = (0..3).random()
            showNotification(Item[randomIndex].name,Item[randomIndex].descriptionText)
        }
    }
    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "Channel name"
            val descriptionText="Description Text"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(Channel_ID,name,importance).apply {
                description=descriptionText
            }
            val notificationManager: NotificationManager=
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

    }


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun showNotification(contentTitle: String,contentText: String){
        var builder = NotificationCompat.Builder(this,Channel_ID)
            .setSmallIcon(android.R.drawable.alert_dark_frame)
            .setContentTitle(contentTitle)
            .setContentText(contentText)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
        with(NotificationManagerCompat.from(this)){
            if(ActivityCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.POST_NOTIFICATIONS
                )!= PackageManager.PERMISSION_GRANTED
            ){
                requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),100)
            }
            notify(NOTIFICATION_ID,builder.build())
        }
    }
}