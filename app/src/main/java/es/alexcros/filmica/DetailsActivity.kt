package es.alexcros.filmica

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val id: String = intent.getStringExtra("id")
        val film = FilmsRepo.findFilmById(id)

        film?.let {
            with(film) {
                labelTitle.text = title
                labelOverView.text = overview
                labelGenre.text = genre
                labelRelease.text = release
            }
        }

        val button : Button = findViewById(R.id.button_add)

        button.setOnClickListener { view ->
            Toast.makeText(this@DetailsActivity, "Added to list", Toast.LENGTH_LONG).show()
        }
    }
}
