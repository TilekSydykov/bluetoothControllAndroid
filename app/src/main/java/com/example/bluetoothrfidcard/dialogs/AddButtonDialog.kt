package com.example.bluetoothrfidcard.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.example.bluetoothrfidcard.R

class AddButtonDialog(context: Context) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_ad_button)


    }
}