package com.example.githubapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.githubapp.App
import com.example.githubapp.R
import com.example.githubapp.databinding.FragmentProfileBinding
import com.example.githubapp.domain.GithubUser
import com.example.githubapp.ui.users.BackButtonListener
import com.example.githubapp.ui.utils.app
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
            requireActivity().app
        )
    }
    private var adapter: ProfileAdapter? = null
    private var countLike:Int =0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.repositoriesRv.layoutManager = LinearLayoutManager(context)
        adapter = ProfileAdapter(presenter)
        binding.repositoriesRv.adapter = adapter
        binding.likeButton.setOnClickListener {

        }
    }

    override fun backPressed() = presenter.backPressed()

    override fun setUser(user: GithubUser) {
        binding.tvLogin.text = user.login
        binding.likeButton.isEnabled = user.like
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun setCountLike() {
        countLike = presenter.setLikeCount(countLike)
        binding.counterTextView.text = countLike.toString()
    }

}