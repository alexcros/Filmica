package es.alexcros.filmica.view.films

import android.support.v4.content.ContextCompat
import android.support.v7.graphics.Palette
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import es.alexcros.filmica.R
import es.alexcros.filmica.data.Film
import es.alexcros.filmica.view.utils.SimpleTarget
import kotlinx.android.synthetic.main.item_film.view.*

/**
 * Created by alexandre on 13/11/18.
 */
class FilmsAdapter(var itemClickListener: ((Film) -> Unit)? = null) :
        RecyclerView.Adapter<FilmsAdapter.FilmViewHolder>() {

    //val list: MutableList<Film> = mutableListOf()
    val list = mutableListOf<Film>()

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onCreateViewHolder(recyclerView: ViewGroup, type: Int): FilmViewHolder {
        // service from os -> xml to view (textview, viewContainer)
        val itemView = LayoutInflater.from(recyclerView.context).inflate(R.layout.item_film, recyclerView, false)

        return FilmViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val film: Film = list[position]
        holder.film = film
    }

    fun setFilms(films: MutableList<Film>) {
        list.clear()
        list.addAll(films)
        notifyDataSetChanged()
    }

    inner class FilmViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var film: Film? = null
            set(value) {
                field = value

                value?.let {
                    with(itemView) {
                        labelTitle.text = value?.title
                        titleGenre.text = value?.genre
                        labelVotes.text = value?.voteRating.toString()

                        val target = SimpleTarget(
                                successCallback = { bitmap, from ->
                                    imgPoster.setImageBitmap(bitmap)
                                    Palette.from(bitmap).generate { palette ->
                                        val defaultColor = ContextCompat.getColor(itemView.context, R.color.colorPrimary)
                                        val swatch = palette?.vibrantSwatch ?: palette?.dominantSwatch
                                        val color = swatch?.rgb ?: defaultColor

                                        container.setBackgroundColor(color)
                                        containerData.setBackgroundColor(color)

                                    }
                                }
                        )

                        imgPoster.tag = target

                        Picasso.get()
                                .load(value.getPosterURL())
                                .placeholder(R.drawable.placeholder)
                                .error(R.drawable.placeholder)
                                .into(target)


                    }
                }
            }

        init {
            this.itemView.setOnClickListener {
                film?.let {
                    itemClickListener?.invoke(this.film as Film)
                }

            }
        }

    }


}