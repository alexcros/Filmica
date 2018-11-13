package es.alexcros.filmica

/**
 * Created by alexandre on 13/11/18.
 *
 * Design Pattern Repository
 */

// Kotlin Singleton: always in memory heap <cache>

object FilmsRepo {
    val films: MutableList<Film> = mutableListOf()
        get() {
            // films instance "field" (avoid getter recall)
            if (field.isEmpty()) {
                field.addAll(dummyFilms())
            }

            return field
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
}

