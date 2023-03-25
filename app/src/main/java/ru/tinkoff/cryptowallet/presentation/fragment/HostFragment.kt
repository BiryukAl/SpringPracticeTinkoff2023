package ru.tinkoff.cryptowallet.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.tinkoff.cryptowallet.R

class HostFragment: Fragment(R.layout.fragment_host) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.main_bottom_nav)
        val navController = (childFragmentManager.findFragmentById(R.id.host_fragment_container) as NavHostFragment)
            .navController

        bottomNavigationView.setupWithNavController(navController)
    }


    companion object{

        const val HOST_FRAGMENT_TAG = "HOST_FRAGMENT_TAG"

        fun getInstance() = HostFragment()
    }
}
