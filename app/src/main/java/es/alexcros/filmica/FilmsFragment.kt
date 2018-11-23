package es.alexcros.filmica

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_films.*

/**
 * Created by alexandre on 19/11/18.
 */

class FilmsFragment: Fragment() {

    lateinit var listener: OnItemClickListener

    val list: RecyclerView by lazy {
        val instance = view!!.findViewById<RecyclerView>(R.id.list_films)
        instance.addItemDecoration(ItemOffsetDecoration(R.dimen.offset_grid))

        // improve performance
        instance.setHasFixedSize(true)

        instance
    }

    val adapter: FilmsAdapter by lazy {
        val instance = FilmsAdapter { film ->
            this.listener.onItemClicked(film)
        }

        instance.setFilms(FilmsRepo.films)
        instance
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is OnItemClickListener) {
            listener = context
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_films, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.adapter = adapter
    }

    override fun onResume() {
        super.onResume()

        FilmsRepo.discoverFilms(context!!,
                { films ->
                    progress.visibility = View.INVISIBLE
                    layout_error.visibility = View.INVISIBLE
                    list.visibility = View.VISIBLE
                    adapter.setFilms(films)

                },{ error ->
                    progress.visibility = View.INVISIBLE
                    layout_error.visibility = View.VISIBLE
                    list.visibility = View.INVISIBLE

                    error.printStackTrace()
        })
    }

    interface OnItemClickListener {
        fun onItemClicked(film: Film)
    }

}