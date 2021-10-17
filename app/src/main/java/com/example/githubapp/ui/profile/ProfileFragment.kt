package com.example.githubapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.githubapp.App
import com.example.githubapp.R
import com.example.githubapp.databinding.FragmentProfileBinding
import com.example.githubapp.domain.GithubUser
import com.example.githubapp.ui.users.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

const val ARG_USER = "ARG_USER_LOGIN"

class ProfileFragment : MvpAppCompatFragment(), ProfileView, BackButtonListener {

    companion object {
        fun newInstance(login: String) =
            ProfileFragment().apply { arguments = bundleOf(ARG_USER to login) }
    }

    private val login: String? by lazy {
        arguments?.getString(ARG_USER, "login 1")
    }
    private val binding: FragmentProfileBinding by viewBinding(FragmentProfileBinding::bind)
    private val presenter: ProfilePresenter by moxyPresenter {
        ProfilePresenter(
            login,
            App.instance.usersRepo,
            App.instance.router
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }


    override fun backPressed() = presenter.backPressed()

    override fun setUser(user: GithubUser) {
        binding.UserLogin.text = user.login
    }



}