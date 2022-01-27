package com.shaun.whatsappsender

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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

            val view = inflater.inflate(R.layout.fragment_message, container, false)


            numberViewModel = ViewModelProvider(requireActivity()).get(NumberViewModel::class.java)

            val send = view.findViewById<Button>(R.id.send)
            val phone_entry = view.findViewById<EditText>(R.id.phone_entry)
            phone_entry.setText(numberViewModel.number)

            phone_entry.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    numberViewModel.number = s.toString()
                }

                override fun afterTextChanged(s: Editable?) {

                }

            })

            send.setOnClickListener {

                var phoneNumber =
                    phone_entry.text.toString().filter { it.isWhitespace().not() }.also {
                        phone_entry.setText(it)
                    }
                if (isValidNum(phoneNumber)) {
                    if (phoneNumber.length == 10) {
                        phoneNumber = "+91$phoneNumber"
                    }

                    val number = Number(phoneNumber)
                    numberViewModel.insert(number)
                    val browserIntent =
                        Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/$phoneNumber"))
                    browserIntent.setPackage("com.whatsapp")
                    startActivity(browserIntent)
                } else {
                    Toast.makeText(
                        context,
                        "Enter valid Number with country code",
                        Toast.LENGTH_SHORT
                    ).show()
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