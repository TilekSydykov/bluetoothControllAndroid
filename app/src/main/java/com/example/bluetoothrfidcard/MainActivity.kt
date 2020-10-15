package com.example.bluetoothrfidcard

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.util.*


class MainActivity : AppCompatActivity() {

    private var mBluetoothAdapter: BluetoothAdapter? = null
    private lateinit var mmSocket: BluetoothSocket
    private var mmDevice: BluetoothDevice? = null
    private lateinit var mmOutputStream: OutputStream
    private lateinit var mmInputStream: InputStream

    private var listOfDevices = arrayListOf<BluetoothDevice>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        actionBar?.title = "BlueTooth"
        findBT()

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, i: Int, p3: Long) {
                mmDevice = listOfDevices[i]
                openBT()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

    }

    private fun findBT() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

        mBluetoothAdapter?.let {
            if (mBluetoothAdapter!!.isEnabled) {
                val enableBluetooth = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(enableBluetooth, 0)
            }
            val pairedDevices: Set<BluetoothDevice> = mBluetoothAdapter!!.bondedDevices
            val names = arrayListOf<String>()
            if (pairedDevices.isNotEmpty()) {
                listOfDevices = arrayListOf()
                for (device in pairedDevices) {
                    if (device.name.startsWith("HC")) {
                        listOfDevices.add(device)
                        names.add(device.name)
                    }
                }
                spinner.adapter = ArrayAdapter<String>(this, R.layout.spinner_item, names)
            }
        }
    }

    @Throws(IOException::class)
    fun openBT() {
        val uuid: UUID =
            UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
        mmDevice?.let {
            mmSocket = mmDevice!!.createRfcommSocketToServiceRecord(uuid)
            mmSocket.connect()
            mmOutputStream = mmSocket.outputStream
            mmInputStream = mmSocket.inputStream
            Toast.makeText(applicationContext, "Connected", Toast.LENGTH_SHORT).show()
        }
    }



}