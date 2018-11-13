package es.alexcros.filmica

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.lang.reflect.Array.set

/**
 * Created by alexandre on 13/11/18.
 */
class FilmsAdapter: RecyclerView.Adapter<FilmsAdapter.FilmViewHolder>() {

    //val list: MutableList<Film> = mutableListOf()
    val list = mutableListOf<Film>()

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onCreateViewHolder(recyclerView: ViewGroup, type: Int): FilmViewHolder {
        // service from os -> xml to view (textview, viewContainer)
        val itemView = LayoutInflater.from(recyclerView.context).
                inflate(R.layout.item_film, recyclerView, false)

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
            itemView.findViewById<TextView>(R.id.label_title).text = value?.title
        }
    }

//    inner FilmViewHolder(view: View): RecyclerView.ViewHolder(view) {
//        var film: Film? = null
//        set(value) {
//            field = value
//
//            itemView.findViewById<TextView>(R.id.label_title).text = value?.title
//        }
//    }

}