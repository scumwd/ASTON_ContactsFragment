package com.example.aston_contactsfragment.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import com.example.aston_contactsfragment.R
import com.example.aston_contactsfragment.models.User

class EditUserFragment : Fragment() {

    private lateinit var user: User

    companion object {
        fun newInstance(user: User): EditUserFragment {
            val fragment = EditUserFragment()
            val args = Bundle()
            args.putSerializable("user", user)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user = it.getSerializable("user") as User
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_user, container, false)
        val firstName: EditText = view.findViewById(R.id.firstName)
        val lastName: EditText = view.findViewById(R.id.lastName)
        val phoneNumber: EditText = view.findViewById(R.id.phoneNumber)
        val photoContact: ImageView = view.findViewById(R.id.photoContact)
        val photoUrl: EditText = view.findViewById(R.id.photoUrl)

        firstName.setText(user.firstName)
        lastName.setText(user.lastName)
        phoneNumber.setText(user.phoneNumber)
        photoUrl.setText(user.photoUrl)

        Glide.with(view)
            .load(user.photoUrl)
            .error(R.drawable.error)
            .into(photoContact)

        view.findViewById<Button>(R.id.saveContact).setOnClickListener {
            if (firstName.text.isEmpty() ||
                lastName.text.isEmpty() ||
                phoneNumber.text.isEmpty() ||
                photoUrl.text.isEmpty()
            ) {
                Toast.makeText(context, "Empty field", Toast.LENGTH_SHORT).show()
            } else {
                val updateUser = User(
                    id = user.id,
                    firstName = firstName.text.toString(),
                    lastName = lastName.text.toString(),
                    phoneNumber = phoneNumber.text.toString(),
                    photoUrl = photoUrl.text.toString()
                )
                parentFragmentManager.setFragmentResult(
                    "editKey",
                    bundleOf("updatedUser" to updateUser)
                )
                parentFragmentManager.popBackStack()
            }

        }

        return view
    }
}
