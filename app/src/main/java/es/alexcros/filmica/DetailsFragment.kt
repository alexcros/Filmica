package es.alexcros.filmica

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_details.*

/**
 * Created by alexandre on 19/11/18.
 */
class DetailsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val film = Film()

        film?.let {
            with(film) {
                labelTitle.text = title
                labelOverView.text = overview
                labelGenre.text = genre
                labelRelease.text = release
            }
        }

        btnAdd.setOnClickListener {
            Toast.makeText(context, "Added to list", Toast.LENGTH_LONG).show()
        }
    }
}
