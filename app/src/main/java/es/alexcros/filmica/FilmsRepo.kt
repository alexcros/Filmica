package es.alexcros.filmica

import android.content.Context
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import es.alexcros.filmica.Film.Companion.parseFilms

/**
 * Created by alexandre on 13/11/18.
 *
 * Design Pattern Repository, logical server calls
 */

// Kotlin Singleton: always in memory heap <cache>

object FilmsRepo {
    val films: MutableList<Film> = mutableListOf()
//        get() {
//            // films instance "field" (avoid getter recall)
//            if (field.isEmpty()) {
//                field.addAll(dummyFilms())
//            }
//
//            return field
//        }

    fun findFilmById(id: String): Film? {
        return films.find { film -> film.id == id }
    }

    private fun dummyFilms(): List<Film> {
        return (0..9).map {
            Film(
                    title = "Film ${it}",
                    genre = "Genre ${it}",
                    release = "200$it-0$it-0${it}",
                    voteRating = it.toDouble(),
                    overview = "Overview $it"
            )
        }
    }

    fun discoverFilms(context: Context,
                      callbackSuccess: ((MutableList<Film>) -> Unit),
                      callbackError: ((VolleyError) -> Unit)) {

        if (this.films.isEmpty()) {
            //requestDiscoverFilms(callbackSuccess, context)
            requestDiscoverFilms(callbackSuccess,callbackError,context)
        } else {
            callbackSuccess.invoke(this.films)
        }

    }

    private fun requestDiscoverFilms(
            callbackSuccess: (MutableList<Film>) -> Unit,
            callbackError: (VolleyError) -> Unit,
            context: Context
    ) {
        val url = ApiRoutes.discoverUrl()
        val request = JsonObjectRequest(Request.Method.GET, url, null,
                { response ->
                    val newFilms = Film.parseFilms(response)
                    this.films.addAll(newFilms)
                    callbackSuccess.invoke(this.films)
                },
                { error ->
                    callbackError.invoke(error)
                })

        Volley.newRequestQueue(context)
                .add(request)
    }


}

