package com.example.hw3_rateyourfavoriteanimal

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast

class AnimalRatingActivity : AppCompatActivity() {

    // Declare global variables for views and data
    // I am using lateinit to tell the ccompile that, I will initialize the variable later
    private lateinit var animalName: TextView
    private lateinit var imageView: ImageView
    private lateinit var ratingBar: RatingBar

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var listAnimalName: List<String>

    private var animalId = 0
    private var imageRate = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_rating)

        // Initialize the list of animal names and images
        listAnimalName = listOf("Dog_Rating", "Cat_Rating", "Bear_Rating", "Rabbit_Rating")
        val listAnimalRedrawable = listOf(R.drawable.dog, R.drawable.cat, R.drawable.bear, R.drawable.rabbit)

        // Get the animal ID and rating from the intent
        animalId = intent.getIntExtra("animalId", 0)
        imageRate = intent.getIntExtra("ImageRate", 0)

        ratingBar = findViewById(R.id.ratingBar)
        ratingBar.rating = imageRate.toFloat()

        imageView = findViewById(R.id.imageView)
        imageView.setImageResource(listAnimalRedrawable[animalId])

        animalName = findViewById(R.id.textView4)
        animalName.text = listAnimalName[animalId].replace("_", " ")

        // Initialize the shared preferences
        sharedPreferences = getSharedPreferences("AnimalSharedPreferences", MODE_PRIVATE)
    }

    fun RateMe(view: View) {
        val rates = ratingBar.rating.toString()
        val editor = sharedPreferences.edit()
        editor.putString(listAnimalName[animalId], rates)
        editor.apply() // Apply the changes.

//        Toast.makeText(this, "Rating Score: $rates", Toast.LENGTH_SHORT).show()

        finish()
    }
}
