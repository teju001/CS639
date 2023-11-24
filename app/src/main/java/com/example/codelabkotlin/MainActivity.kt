package com.example.codelabkotlin
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.codelabkotlin.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // UI components
        val notificationButton = findViewById<Button>(R.id.notificationButton)
        val ticketPriceButton = findViewById<Button>(R.id.ticketPriceButton)
        val temperatureButton = findViewById<Button>(R.id.temperatureButton)
        val songButton = findViewById<Button>(R.id.songButton)
        val profileButton = findViewById<Button>(R.id.profileButton)
        val outputTextView = findViewById<TextView>(R.id.outputTextView)

        // Inputs for calculations
        val ageInput = findViewById<EditText>(R.id.ageInput)
        val isMondayCheckbox = findViewById<CheckBox>(R.id.isMondayCheckbox)
        val temperatureInput = findViewById<EditText>(R.id.temperatureInput)

        // Set click listeners for buttons
        notificationButton.setOnClickListener { outputTextView.text = getNotificationSummary(120) }
        ticketPriceButton.setOnClickListener {
            val age = ageInput.text.toString().toIntOrNull()
            val isMonday = isMondayCheckbox.isChecked
            if (age != null) {
                outputTextView.text = "The movie ticket price is $${getTicketPrice(age, isMonday)}"
            } else {
                outputTextView.text = "Please enter a valid age."
            }
        }
        temperatureButton.setOnClickListener {
            val temp = temperatureInput.text.toString().toDoubleOrNull()
            if (temp != null) {
                outputTextView.text = convertTemperature(temp, "Celsius", "Fahrenheit")
            } else {
                outputTextView.text = "Please enter a valid temperature."
            }
        }
        songButton.setOnClickListener { outputTextView.text = getSongDescription("Shape of You", "Ed Sheeran", 2017, 1500000) }
        profileButton.setOnClickListener { outputTextView.text = getProfile("varun teja", 25, "anime", null) }
    }

    private fun getNotificationSummary(count: Int): String {
        return if (count < 100) "You have $count notifications."
        else "Your phone is blowing up! You have 99+ notifications."
    }

    private fun getTicketPrice(age: Int, isMonday: Boolean): Int {
        return when (age) {
            in 0..12 -> 15
            in 13..60 -> if (isMonday) 25 else 30
            in 61..100 -> 20
            else -> -1
        }
    }

    private fun convertTemperature(temp: Double, initialUnit: String, finalUnit: String): String {
        val result = when (initialUnit) {
            "Celsius" -> 9.0 / 5.0 * temp + 32
            "Fahrenheit" -> (temp - 32) * 5.0 / 9.0
            "Kelvin" -> temp - 273.15
            else -> 0.0
        }
        return "$temp degrees $initialUnit is ${String.format("%.2f", result)} degrees $finalUnit."
    }

    private fun getSongDescription(title: String, artist: String, year: Int, playCount: Int): String {
        val isPopular = playCount >= 1000
        return "$title, performed by $artist, was released in $year. Popular: $isPopular"
    }

    private fun getProfile(name: String, age: Int, hobby: String?, referrer: Person?): String {
        return "Name: $name\nAge: $age\nHobby: ${hobby ?: "Not specified"}\nReferrer: ${referrer?.name ?: "None"}"
    }

    data class Person(val name: String, val age: Int, val hobby: String?, val referrer: Person?)
}
