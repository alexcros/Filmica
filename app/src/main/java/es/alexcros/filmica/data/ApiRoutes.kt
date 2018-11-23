package es.alexcros.filmica.data

import android.net.Uri
import es.alexcros.filmica.BuildConfig

/**
 * Created by alexandre on 20/11/18.
 */
object ApiRoutes {

    fun discoverUrl(
            language: String = "en-US",
            sort: String = "popularity.desc",
            page: Int = 1
    ): String {
        // design pattern factory
        return  getUriBuilder()
                .appendPath("discover")
                .appendPath("movie")
                .appendQueryParameter("language", language)
                .appendQueryParameter("sort_by", sort)
                .appendQueryParameter("include_adult", "false")
                .appendQueryParameter("include_video", "false")
                .build()
                .toString()
    }

    private fun getUriBuilder() =
            Uri.Builder()
                    .scheme("https")
                    .authority("api.themoviedb.org")
                    .appendPath("3")
                    .appendQueryParameter("api_key", BuildConfig.MovieDBApiKey)
}