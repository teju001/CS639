package com.example.codelabkotlin
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.codelabkotlin.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // UI Components
        val notificationButton = findViewById<Button>(R.id.notificationButton)
        val ticketPriceButton = findViewById<Button>(R.id.ticketPriceButton)
        val temperatureButton = findViewById<Button>(R.id.temperatureButton)
        val songButton = findViewById<Button>(R.id.songButton)
        val profileButton = findViewById<Button>(R.id.profileButton)
        val outputTextView = findViewById<TextView>(R.id.outputTextView)

        // Event Listeners
        notificationButton.setOnClickListener { outputTextView.text = getNotificationSummary(120) }
        ticketPriceButton.setOnClickListener { outputTextView.text = getTicketPrice(28, true).toString() }
        temperatureButton.setOnClickListener { outputTextView.text = convertTemperature(27.0, "Celsius", "Fahrenheit") }
        songButton.setOnClickListener { outputTextView.text = getSongDescription("Shape of You", "Ed Sheeran", 2017, 1500000) }
        profileButton.setOnClickListener { outputTextView.text = getProfile("varun teja", 23, "watching anime", null) }
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
