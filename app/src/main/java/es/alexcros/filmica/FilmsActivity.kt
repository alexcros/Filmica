package es.alexcros.filmica

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by alexandre on 13/11/18.
 */
class FilmsActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_films)

        val list: RecyclerView = findViewById(R.id.list_films)
        list.layoutManager = LinearLayoutManager(this)
    }

    fun showDetails(clickedView: View) {
        val intentToDetails: Intent = Intent(this, DetailsActivity::class.java)
        startActivity(intentToDetails)
    }
}