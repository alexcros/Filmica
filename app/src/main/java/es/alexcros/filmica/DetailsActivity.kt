package es.alexcros.filmica

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button : Button = findViewById(R.id.button_add)

        button.setOnClickListener { view ->
            Toast.makeText(this@MainActivity, "Added to list", Toast.LENGTH_LONG).show()
        }
    }
}
