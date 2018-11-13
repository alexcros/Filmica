package es.alexcros.filmica

import java.util.*

/**
 * Created by alexandre on 13/11/18.
 */

data class Film(
        var id: String = UUID.randomUUID().toString(),
        var title: String,
        var genre: String,
        var release: String,
        var voteRating: Double,
        var overview: String
)

