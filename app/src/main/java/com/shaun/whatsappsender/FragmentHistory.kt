package com.shaun.whatsappsender

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentHistory : Fragment(), NumberListAdapter.onDelete {

    private lateinit var numberViewModel: NumberViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        val recycler = view?.findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = NumberListAdapter(context!!, this)
        recycler?.layoutManager = LinearLayoutManager(context)
        recycler?.adapter = adapter

        numberViewModel = ViewModelProvider(this).get(NumberViewModel::class.java)
        numberViewModel.allNumbers.observe(viewLifecycleOwner, Observer { ok ->
            ok?.let { adapter.setWords(it) }
        })



        return view
    }

    override fun OnDelete(number: String) {
        numberViewModel.delete(number)
    }

    override fun OnEmptty() {
        val empty_animation = view?.findViewById<ConstraintLayout>(R.id.empty_animation)
        val recycler = view?.findViewById<RecyclerView>(R.id.recycler_view)
        empty_animation?.visibility = View.VISIBLE
        recycler?.visibility = View.GONE
    }


}