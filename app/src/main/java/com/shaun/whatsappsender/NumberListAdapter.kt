package com.shaun.whatsappsender

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class NumberListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<NumberListAdapter.NumberViewHolder>() {
    private val inflater = LayoutInflater.from(context)
    private var numbers = emptyList<Number>()

    inner class NumberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val NumberItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val itemView = inflater.inflate(R.layout.number_adapter, parent, false)
        return NumberViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return numbers.size
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: $numbers")
        val current = numbers[position]
        holder.NumberItemView.text = current.number

    holder.itemView.setOnClickListener {
        val browserIntent =
            Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/${holder.NumberItemView.text}"))
        browserIntent.setPackage("com.whatsapp")
        holder.itemView.context.startActivity(browserIntent)
    }

    }


    internal fun setWords(numbers: List<Number>) {
        this.numbers=numbers.reversed()
        notifyDataSetChanged()
    }
}
