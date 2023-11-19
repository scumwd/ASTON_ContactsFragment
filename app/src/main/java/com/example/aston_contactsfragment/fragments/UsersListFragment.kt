package com.example.aston_contactsfragment.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aston_contactsfragment.MainActivity
import com.example.aston_contactsfragment.R
import com.example.aston_contactsfragment.fragments.adapter.UsersAdapter
import com.example.aston_contactsfragment.models.User

class UsersListFragment : Fragment() {

    lateinit var adapter: UsersAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_users_list, container, false)
        recyclerView = view.findViewById(R.id.rvUsers)
        adapter = UsersAdapter(getDummyUsers()) { user ->
            val editFragment = EditUserFragment.newInstance(user)
            (activity as MainActivity).navigateToFragment(editFragment)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        return view
    }

    private fun getDummyUsers(): MutableList<User> {
        return mutableListOf(
            User(
                1,
                "Иван",
                "Иванов",
                "+1234321356",
                "https://loremflickr.com/cache/resized/65535_52682151570_91793b7a9a_q_100_100_nofilter.jpg"
            ),
            User(
                2,
                "Петр",
                "Петров",
                "+9876321354",
                "https://loremflickr.com/cache/resized/65535_52849101574_4b6d732dc2_q_100_100_nofilter.jpg"
            ),
            User(
                3,
                "Анна",
                "Сидорова",
                "+111222",
                "https://loremflickr.com/cache/resized/65535_53061525503_2651017e33_q_100_100_nofilter.jpg"
            ),
            User(
                4,
                "Елена",
                "Козлова",
                "+333444",
                "https://loremflickr.com/cache/resized/65535_52669848751_0a9e04a736_q_100_100_nofilter.jpg"
            )
        )
    }
}
