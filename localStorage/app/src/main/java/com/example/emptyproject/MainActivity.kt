package com.example.emptyproject

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    val File_Name: String = "example.txt"
    lateinit var btnSave: Button
    lateinit var btnLoad: Button
    lateinit var zoneTxt: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSave = findViewById(R.id.btn_save)
        btnLoad = findViewById(R.id.btn_load)
        zoneTxt = findViewById(R.id.text_zone)

        btnSave.setOnClickListener {
            save()
        }
        btnLoad.setOnClickListener {
            load()
        }
    }

    fun save() {
        val data: String = zoneTxt.text.toString()
        val fileOutputStream: FileOutputStream
        try {
            fileOutputStream = openFileOutput(File_Name, Context.MODE_PRIVATE)
            fileOutputStream.write(data.toByteArray())
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        Toast.makeText(applicationContext, "data save", Toast.LENGTH_LONG).show()
        zoneTxt.text.clear()
    }
    fun load(){
        if(File_Name.toString()!=null && File_Name.toString().trim()!=""){
            var fileInputStream: FileInputStream? = null
            fileInputStream = openFileInput(File_Name)
            var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder: StringBuilder = StringBuilder()
            var text: String? = null
            while ({ text = bufferedReader.readLine(); text }() != null) {
                stringBuilder.append(text)
            }
            //Displaying data on EditText
            zoneTxt.setText(stringBuilder.toString()).toString()
        }else{
            Toast.makeText(applicationContext,"file name cannot be blank",Toast.LENGTH_LONG).show()
        }
    }
    }
