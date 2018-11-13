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
            // films instance field (avoid getter recall)
            if (field.isEmpty()) {
                field.addAll(dummyFilms())
            }

            return field
        }

    private fun dummyFilms() : MutableList<Film> {
        val films: MutableList<Film> = mutableListOf()

        for (i in 1..9) {
            films.add(
                    Film(
                            title = "Film ${i}",
                            genre = "Genre ${i}",
                            release =        "Release ${i}",
                            voteRating = i.toDouble(),
                            overview = "Overview ${i}"
                    )
            )

        }

        return films
    }
}

