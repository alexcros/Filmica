package es.alexcros.filmica

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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