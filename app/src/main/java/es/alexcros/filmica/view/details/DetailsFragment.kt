package es.alexcros.filmica.view.details

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.graphics.Palette
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.squareup.picasso.Picasso
import es.alexcros.filmica.R
import es.alexcros.filmica.data.Film
import es.alexcros.filmica.data.FilmsRepo
import es.alexcros.filmica.view.utils.SimpleTarget
import kotlinx.android.synthetic.main.fragment_details.* // avoid findViewById repeat

/**
 * Created by alexandre on 19/11/18.
 */
class DetailsFragment : Fragment() {

    companion object {
        fun newInstance(id: String): DetailsFragment {
            val instance = DetailsFragment()
            val args = Bundle()
            args.putString("id", id)
            instance.arguments = args

            return instance
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id: String = arguments?.getString("id") ?: ""
        val film = FilmsRepo.findFilmById(id)

        film?.let {
            with(film) {
                labelTitle.text = title
                labelOverview.text = overview
                labelGenre.text = genre
                labelRelease.text = release
                labelVotes.text = voteRating.toString()

                loadImage(film)

                Picasso.get()
                        .load(getPosterURL())
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.placeholder)
                        .into(imgPoster)
            }
        }

        btnAdd.setOnClickListener {
            Toast.makeText(context, "Added to list", Toast.LENGTH_SHORT).show()
        }
    }
    private fun loadImage(film: Film) {
        val target = SimpleTarget(
                successCallback = { bitmap, from ->
                    imgPoster.setImageBitmap(bitmap)
                    setColorFrom(bitmap)
                }
        )

        imgPoster.tag = target

        Picasso.get()
                .load(film?.getPosterURL())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(target)
    }

    private fun setColorFrom(bitmap: Bitmap) {
        Palette.from(bitmap).generate { palette ->
            val defaultColor = ContextCompat.getColor(context!!, R.color.colorPrimary)
            val swatch = palette?.vibrantSwatch ?: palette?.dominantSwatch
            val color = swatch?.rgb ?: defaultColor
            val overlayColor = Color.argb(
                    (Color.alpha(color) * 0.5).toInt(),
                    Color.red(color),
                    Color.green(color),
                    Color.blue(color)
            )

            overlay.setBackgroundColor(overlayColor)
            btnAdd.backgroundTintList = ColorStateList.valueOf(color)
        }
    }
}