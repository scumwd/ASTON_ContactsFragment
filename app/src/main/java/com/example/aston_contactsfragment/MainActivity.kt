package com.example.aston_contactsfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.aston_contactsfragment.fragments.UsersListFragment
import com.example.aston_contactsfragment.models.User

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, UsersListFragment())
                .commit()
        }

        supportFragmentManager.setFragmentResultListener("editKey", this) { _, bundle ->
            val updatedUser = bundle.getSerializable("updatedUser") as? User
            if (updatedUser != null) {
                supportFragmentManager.addOnBackStackChangedListener {
                    val fragment = supportFragmentManager.findFragmentById(R.id.container)
                    if (fragment is UsersListFragment) {
                        fragment.adapter.updateUser(updatedUser)
                    }
                }
            }

        }

    }

    fun navigateToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }
}