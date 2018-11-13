package es.alexcros.filmica

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val button : Button = findViewById(R.id.button_add)

        button.setOnClickListener { view ->
            Toast.makeText(this@DetailsActivity, "Added to list", Toast.LENGTH_LONG).show()
        }
    }
}
