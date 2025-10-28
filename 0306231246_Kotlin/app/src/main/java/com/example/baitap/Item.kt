package com.example.baitap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

data class ItemModel(val title: String)
class Item : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_item)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    class MyAdapter(private val datalist:List<ItemModel>,private val onItemClick: (String)-> Unit):
            RecyclerView.Adapter<MyAdapter.MyViewHolder>(){
                interface OnItemClickListener{
                    fun itemclicklistener(position: Int)
                }
        inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            val TitleText: TextView=itemView.findViewById(R.id.tv_title)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_item,parent,false)
            return MyViewHolder(view)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val curItem=datalist[position]
            holder.TitleText.text=curItem.title
            holder.itemView.setOnClickListener {
                onItemClick(curItem.title)
            }
        }

        override fun getItemCount(): Int {
            return datalist.size
        }
            }
}