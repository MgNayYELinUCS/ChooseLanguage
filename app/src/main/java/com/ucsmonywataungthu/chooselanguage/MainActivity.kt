package com.ucsmonywataungthu.chooselanguage

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.res.Configuration
import android.content.res.Resources
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadLocale()
        setContentView(R.layout.activity_main)
        val actionBar=supportActionBar
        actionBar!!.setTitle(R.string.welcome)


        btnLanguageChange.setOnClickListener{
            showDialog()
        }
    }

    private fun showDialog() {
        val items = arrayOf("Myanmarm Unicode", "English")
        val builder =AlertDialog.Builder(this)
        builder.setTitle("Change Language")
        builder.setSingleChoiceItems(items, -1) { dialogInterface, i ->
            if(i==0){
                setLocale("my")
                recreate()
            }else {
                setLocale("en")
                recreate()
            }

            dialogInterface.dismiss()
        }
        val dialog=builder.create()
        dialog.show()
    }

    private fun setLocale(language: String) {
        val locale=Locale(language)
        Locale.setDefault(locale)
        val config=Configuration()
        config.locale=locale
        baseContext.resources.updateConfiguration(config,baseContext.resources.displayMetrics)

        val prefs=getSharedPreferences("Setting", Context.MODE_PRIVATE).edit()
        prefs.putString("Language",language)
        prefs.apply()

    }
     fun loadLocale(){
        val pref=getSharedPreferences("Setting",Activity.MODE_PRIVATE)
        val language=pref.getString("Language","")
        setLocale(language)
    }
}
