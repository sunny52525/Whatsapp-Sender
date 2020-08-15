package com.shaun.whatsappsender

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentHistory : Fragment() {

    private lateinit var numberViewModel: NumberViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_history, container, false)
        val recycler=view?.findViewById<RecyclerView>(R.id.recycler_view)
        val adapter=NumberListAdapter(context!!)

        recycler?.adapter=adapter

        recycler?.layoutManager=LinearLayoutManager(context)
       numberViewModel=ViewModelProvider(this).get(NumberViewModel::class.java)
        numberViewModel.allNumbers.observe(viewLifecycleOwner, Observer {ok->
                ok?.let { adapter?.setWords(it) }
        })

        return view
    }


}