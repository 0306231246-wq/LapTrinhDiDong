package com.example.baitap

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.baitap.Item.MyAdapter
import android.content.Intent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val recView = findViewById<RecyclerView>(R.id.Entertaiment)
        val datalist=mutableListOf<ItemModel>(
            ItemModel("Xã hội"),
            ItemModel("Công nghệ"),
            ItemModel("Thể thao"),
            ItemModel("Giải trí"),
            ItemModel("Xe"),
        )
        val adapter = MyAdapter(datalist){
            click ->if(click.toString()=="Xã hội"){
                val intent=Intent(this, MainActivity2::class.java)
            startActivity(intent)
            }
        }
        recView.layoutManager= LinearLayoutManager(this)
        recView.adapter=adapter
    }
}