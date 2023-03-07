package com.example.hw3_rateyourfavoriteanimal

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson

class AnimalChooserActivity : AppCompatActivity() {

    val listAnimalName = listOf("Dog_Rating","Cat_Rating", "Bear_Rating","Rabbit_Rating")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onResume() {
        super.onResume()
        LoadData()
    }

    fun onImageButtonClick(view: View) {
        val btnIntent =  Intent(this,AnimalRatingActivity:: class.java)
        val imageIndex = when(view.id){
            R.id.dog_image -> 0
            R.id.cat_image -> 1
            R.id.bear_image -> 2
            else -> 3
        }
        btnIntent.putExtra("animalId",imageIndex)
        val imageRate = when(view.id){
            R.id.dog_image -> findViewById<TextView>(R.id.tv_dog).text
            R.id.cat_image -> findViewById<TextView>(R.id.tv_cat).text
            R.id.bear_image -> findViewById<TextView>(R.id.tv_bear).text
            else -> findViewById<TextView>(R.id.tv_rabbit).text
        }
        btnIntent.putExtra("ImageRate",imageIndex)
        startActivity(btnIntent)
    }

    fun LoadData(){
        val AnimalSharedPreferences  = getSharedPreferences("AnimalSharedPreferences",MODE_PRIVATE)
        val textViewList = listOf<TextView>(findViewById<TextView>(R.id.tv_dog),findViewById<TextView>(R.id.tv_cat),findViewById<TextView>(R.id.tv_bear),findViewById<TextView>(R.id.tv_rabbit))
        for((index, element) in textViewList.withIndex()){
            var x = AnimalSharedPreferences.getString(listAnimalName[index],"0.0")
            element.text = "Your Rating: $x"
        }
    }


    fun clearAllRatings(view:View) {
        val AnimalSharedPreferences = getSharedPreferences("AnimalSharedPreferences", MODE_PRIVATE)
        val editor = AnimalSharedPreferences.edit()
        for (animal in listAnimalName) {
            editor.remove(animal)
        }
        editor.apply()
        LoadData()
        Toast.makeText(this, "All ratings have been reset", Toast.LENGTH_SHORT).show()
    }





}