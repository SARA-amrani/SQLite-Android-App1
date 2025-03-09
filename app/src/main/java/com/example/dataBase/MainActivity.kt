package com.example.dataBase

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun Enregistrer(view: View) {
        val nom: TextView = findViewById(R.id.chName)
        val ls: ListView = findViewById(R.id.maliste)
        val db = DBconnexion(this)
        db.insertNewAdmin(nom.text.toString())
        val arrayListe: ArrayList<String> = db.getAllRecord()
        val myAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayListe)
        ls.adapter = myAdapter
        nom.text = ""
    }

    fun Update(view: View) {
        val chNom: TextView = findViewById(R.id.chName)
        val chID: EditText = findViewById(R.id.chID)
        val ls: ListView = findViewById(R.id.maliste)
        val db = DBconnexion(this)
        db.updateRow(chNom.text.toString(), chID.text.toString().toInt())
        val arrayListe: ArrayList<String> = db.getAllRecord()
        val myAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayListe)
        ls.adapter = myAdapter
        chNom.text = ""
        chID.text.clear()
    }

    fun Supprimer(view: View) {
        val chId: TextView = findViewById(R.id.chID)
        val ls: ListView = findViewById(R.id.maliste)
        val db = DBconnexion(this)
        db.deleteRow(chId.text.toString().toInt())
        val arrayL: ArrayList<String> = db.getAllRecord()
        val myAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayL)
        ls.adapter = myAdapter
        chId.text = ""
    }
}
