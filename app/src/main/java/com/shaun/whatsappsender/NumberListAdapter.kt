package com.shaun.whatsappsender

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random


class NumberListAdapter internal constructor(
    context: Context, private val listener: onDelete
) : RecyclerView.Adapter<NumberListAdapter.NumberViewHolder>() {
    private val inflater = LayoutInflater.from(context)
    private var numbers = emptyList<Number>()

    interface onDelete {
        fun OnDelete(number: String)
        fun OnEmptty()

    }

    inner class NumberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val NumberItemView: TextView = itemView.findViewById(R.id.textView)
        val delete = itemView.findViewById<ImageButton>(R.id.delete)
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
            holder.delete.setOnClickListener {
            val phoneNum = holder.NumberItemView.text
            listener.OnDelete(phoneNum as String)
        }
    }


    internal fun setWords(numbers: List<Number>) {
        this.numbers = numbers.reversed()
        if (numbers.isEmpty())
            listener.OnEmptty()
        notifyDataSetChanged()
    }

    private var lastPosition = -1


}
