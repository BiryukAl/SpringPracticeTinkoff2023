package ru.tinkoff.cryptowallet.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ru.tinkoff.cryptowallet.R
import ru.tinkoff.cryptowallet.presentation.fragment.HostFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val hostFragmentContainerId: Int = R.id.fragment_container_for_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(
            hostFragmentContainerId,
            HostFragment.getInstance(),
            HostFragment.HOST_FRAGMENT_TAG)
    }
}
