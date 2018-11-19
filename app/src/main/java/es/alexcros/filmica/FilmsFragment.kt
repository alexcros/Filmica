package es.alexcros.filmica

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by alexandre on 19/11/18.
 */
class FilmsFragment: Fragment() {

    val list: RecyclerView by lazy {
        val instance = view!!.findViewById<RecyclerView>(R.id.list_films)
        instance.layoutManager = LinearLayoutManager(this.context)

        instance
    }

    val adapter: FilmsAdapter by lazy {
        val instance = FilmsAdapter { Film ->
            this.showDetails(Film.id)
        }

        instance.setFilms(FilmsRepo.films)
        instance
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_films, container, false)

    }


    fun showDetails(filmId: String) {
        val intentToDetails = Intent(this.context, DetailsActivity::class.java)
        intentToDetails.putExtra("id", filmId)
        startActivity(intentToDetails)
    }


}