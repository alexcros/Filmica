package es.alexcros.filmica.data

import org.json.JSONArray
import org.json.JSONObject
import java.util.*

/**
 * Created by alexandre on 13/11/18.
 */

data class Film(
        val id: String = UUID.randomUUID().toString(),
        var title: String = "",
        var genre: String = "",
        var release: String = "",
        var voteRating: Double = 0.0,
        var overview: String = ""
) {
    companion object {

        fun parseFilms(response: JSONObject): MutableList<Film> {
            var filmsArray = response.getJSONArray("results")

            for (i in 0..(filmsArray.length() - 1)) {
                val film = parseFilm(filmsArray.getJSONObject(i))
                FilmsRepo.films.add(film)
            }

            return FilmsRepo.films
        }

        fun parseFilm(jsonFilm: JSONObject): Film {
            jsonFilm.getJSONArray("genre_ids")

            return Film(
                    id = jsonFilm.getInt("id").toString(),
                    title = jsonFilm.getString("title"),
                    overview = jsonFilm.getString("overview"),
                    voteRating = jsonFilm.getDouble("vote_average"),
                    release = jsonFilm.getString("release_date"),
                    genre = parseGenres(jsonFilm.getJSONArray("genre_ids"))
            )
        }

        private fun parseGenres(genresArray: JSONArray): String {
            val genres = mutableListOf<String>()

            for (i in 0..(genresArray.length() - 1)) {
                val genreId = genresArray.getInt(i)
                val genre = ApiConstants.genres[genreId] ?: ""
                genres.add(genre)
            }

            // acc genres: Action | Drama ...
            return genres.reduce { acc, genre -> "$acc | $genre" }
        }
    }
}

