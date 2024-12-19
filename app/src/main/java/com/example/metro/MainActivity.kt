package com.example.metro

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.example.metro.Model.Metro
import com.example.metro.ui.theme.MetroTheme

class MainActivity : ComponentActivity() {
    lateinit var startStations: AutoCompleteTextView
    lateinit var endStations: AutoCompleteTextView
    lateinit var directions: TextView
    lateinit var detBtn: Button
    lateinit var route: TextView
    lateinit var time: TextView
    lateinit var start: String
    lateinit var end: String
    val metro = Metro()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        time = findViewById<TextView>(R.id.time)
        directions = findViewById<TextView>(R.id.directions)
        route = findViewById<TextView>(R.id.route)
        startStations = findViewById<AutoCompleteTextView>(R.id.start_spinner)
        endStations = findViewById<AutoCompleteTextView>(R.id.end_spinner)
        detBtn = findViewById<Button>(R.id.calc)
        detBtn.isEnabled = false
        detBtn.background = ContextCompat.getDrawable(this, R.drawable.unenable_btn_corner)
        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, (metro.line1 + metro.line2 + metro.line3))
        startStations.setAdapter(adapter)
        startStations.setOnItemClickListener { parent, view, position, id ->
            start = parent.getItemAtPosition(position).toString()

        }

        adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, (metro.line1 + metro.line2 + metro.line3))
        endStations.setAdapter(adapter)
        endStations.setOnItemClickListener { parent, view, position, id ->
            end = parent.getItemAtPosition(position).toString()
            if (start.equals(end)){
                Toast.makeText(this, "Please Choose validate inputs", Toast.LENGTH_SHORT).show()
            }else{
            detBtn.background = ContextCompat.getDrawable(this, R.drawable.button_corner)
            detBtn.isEnabled = true
            }
        }

//        val start = "Helwan"
//        val end = "New Cairo"
//
//
//        if (path != null) {
//            println("Shortest path: $path")
//            println("Ticket price: ${metro.ticketprice(path.size)}")
//            println("Trip time: ${metro.tripTime(path.size)}")
//        } else {
//            println("No path found between $start and $end")
//        }
    }

    fun showDetails(view: View){
        val path = metro.findPathBFS(listOf(metro.line1 + metro.line2 + metro.line3), start, end)
        time.text = metro.tripTime(path.size)
        directions.text = metro.ticketprice(path.size )
        route.text = path.toString()
    }
}

