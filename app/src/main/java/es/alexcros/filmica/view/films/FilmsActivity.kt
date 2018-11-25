package es.alexcros.filmica.view.films

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import es.alexcros.filmica.R
import es.alexcros.filmica.data.Film
import es.alexcros.filmica.view.details.DetailsActivity
import es.alexcros.filmica.view.details.DetailsFragment
import es.alexcros.filmica.view.watchlist.WatchlistFragment
import kotlinx.android.synthetic.main.activity_films.*
import java.nio.file.WatchEvent

/**
 * Created by alexandre on 13/11/18.
 */
class FilmsActivity : AppCompatActivity(), FilmsFragment.OnItemClickListener {

    private lateinit var filmsFragment: FilmsFragment
    private lateinit var watchlistFragment: WatchlistFragment
    private lateinit var activeFragment: FilmsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_films)

        if (savedInstanceState == null) {
            val filmsFragment = FilmsFragment()
            val watchlistFragment = WatchlistFragment()

            supportFragmentManager.beginTransaction()
                    .add(R.id.container_list, filmsFragment)
                    .add(R.id.container_list, watchlistFragment)
                    .hide(watchlistFragment)
                    .commit()

            activeFragment = filmsFragment
        }

        navigation?.setOnNavigationItemSelectedListener { item ->
            val id = item.itemId

            when (id) {
                R.id.action_discover -> showMainFragment(filmsFragment)
                R.id.action_watchlist -> showMainFragment(watchlistFragment)
            }

            true
        }
    }

    fun showMainFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .hide(activeFragment)
                .show(fragment)
                .commit()

        activeFragment = fragment
    }

    override fun onItemClicked(film: Film) {
        showDetails(film.id)

    }

    fun showDetails(id: String) {
        if (isTablet())
            showDetailsFragment(id)
        else
            launchDetailsActivity(id)

    }

    private fun isTablet() = this.containerDetails != null

    private fun showDetailsFragment(id: String) {
        val detailsFragment = DetailsFragment.newInstance(id)

        supportFragmentManager.beginTransaction()
                .replace(R.id.containerDetails, detailsFragment)
                .commit()
    }

    private fun launchDetailsActivity(id: String) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

}