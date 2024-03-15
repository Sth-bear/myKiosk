package com.example.mykiosk

import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BasketAdapter(private val commitList: List<BasketData>) : RecyclerView.Adapter<BasketAdapter.CommitViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.basket, parent, false)

        return CommitViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommitViewHolder, position: Int) {
       val item = commitList[position]
       holder.bind(item)
    }

    override fun getItemCount(): Int {
        return  commitList.size
    }

    class CommitViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val text : TextView = view.findViewById(R.id.menu_name)
        val logo = view.findViewById<ImageView>(R.id.logo)
        val cancel = view.findViewById<ImageButton>(R.id.shop_cancel)

        fun bind(item: BasketData) {
            text.text = item.text
            logo.background = item.logo
        }
    }
}