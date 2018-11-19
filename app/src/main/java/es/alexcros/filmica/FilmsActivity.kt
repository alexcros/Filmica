package es.alexcros.filmica

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * Created by alexandre on 13/11/18.
 */
class FilmsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_films)

        val filmsFragment = FilmsFragment()

        supportFragmentManager.beginTransaction()
                .add(R.id.container_list, filmsFragment)
                .commit()

    }

}