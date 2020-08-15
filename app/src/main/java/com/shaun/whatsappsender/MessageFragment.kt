package com.shaun.whatsappsender

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider


class MessageFragment : Fragment() {
    private lateinit var numberViewModel: NumberViewModel
        //https://api.whatsapp.com/send?phone=xxxxxxxxxx
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_message, container, false)





            val send=view.findViewById<Button>(R.id.send)
            val phone_entry=view.findViewById<EditText >(R.id.phone_entry)
            send.setOnClickListener {

                var phoneNumber=phone_entry.text.toString()
                if(isValidNum(phoneNumber)){
                    if (phoneNumber.length==10){
                        phoneNumber="+91$phoneNumber"
                    }
                    val number=Number(phoneNumber)
                    numberViewModel=ViewModelProvider(this).get(NumberViewModel::class.java)
                      numberViewModel.insert(number)
                    val browserIntent =
                        Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/$phoneNumber"))
                    browserIntent.setPackage("com.whatsapp")
                    startActivity(browserIntent)
                }else{
                    Toast.makeText(context, "No", Toast.LENGTH_SHORT).show()
                }
            }

        return view
    }

    private fun isValidNum(num:String):Boolean{
      val  validNumber = "^[+]?[0-9]{8,15}$".toRegex()

        return num.matches(validNumber)
    }
    companion object {
        const val EXTRA_REPLY = "com.shaun.whatsappsender.wordlistsql.REPLY"
    }

}