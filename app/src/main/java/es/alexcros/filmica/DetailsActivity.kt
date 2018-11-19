package es.alexcros.filmica

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

//        val film = FilmsRepo.findFilmById(id)
//
//        intent.putExtra("id", id)
//        startActivity(intent)

        if (savedInstanceState == null) {
            val id = intent.getStringExtra("id")
            val args = Bundle()
            args.putString("id", id)

            val detailsFragment = DetailsFragment()
            detailsFragment.arguments = args

            supportFragmentManager.beginTransaction()
                    .add(R.id.container_details, detailsFragment)
                    .commit()
        }
    }
}
