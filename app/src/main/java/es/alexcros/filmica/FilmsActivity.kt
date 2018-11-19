package es.alexcros.filmica

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
/**
 * Created by alexandre on 13/11/18.
 */
class FilmsActivity: AppCompatActivity(), FilmsFragment.OnItemClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_films)

        if (savedInstanceState == null) {
            val filmsFragment = FilmsFragment()

            supportFragmentManager.beginTransaction()
                    .add(R.id.container_list, filmsFragment)
                    .commit()

        }

    }

    override fun onItemClicked(film: Film) {
        showDetails(film.id)

    }

    fun showDetails(id: String) {


    }




}