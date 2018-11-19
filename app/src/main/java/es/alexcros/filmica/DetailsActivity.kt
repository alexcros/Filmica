package es.alexcros.filmica

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        if (savedInstanceState == null) {
            val detailsFragment = DetailsFragment()

            supportFragmentManager.beginTransaction()
                    .add(R.id.container_list, detailsFragment)
                    .commit()

        }

    }
}
