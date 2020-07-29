package com.uttarakhand.kisanseva2.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uttarakhand.kisanseva2.R
import com.uttarakhand.kisanseva2.model.allOrders.AllOrders

class OrdersAdapter(private val allOrders: AllOrders,
                    private val context: Context) :
        RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val buyerName: TextView = itemView.findViewById(R.id.tvBuyerName)
        val address: TextView = itemView.findViewById(R.id.tvAddress)
        val date: TextView = itemView.findViewById(R.id.tvDate)
        val items: RecyclerView = itemView.findViewById(R.id.rvItemOrdered)
        val totalAmout: TextView = itemView.findViewById(R.id.tvAmount)
        val status: TextView = itemView.findViewById(R.id.status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.buyer_orders_item_row, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return allOrders.data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = allOrders.data[position]
        holder.buyerName.text = order.buyer.first_name
        holder.address.text = order.buyer.address
        holder.date.text = order.transactionId.createdAt
        holder.items.adapter = OrderItemsAdapter(order.orderQuantity, context)
        holder.items.layoutManager = LinearLayoutManager(context)
        holder.totalAmout.text = order.amount.toString()
        holder.status.text = if (order.completed) "Ordered" else "Delivered"
    }
}