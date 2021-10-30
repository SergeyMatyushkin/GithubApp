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
import com.example.githubapp.data.GithubUser
import com.example.githubapp.databinding.FragmentProfileBinding
import com.example.githubapp.ui.users.BackButtonListener
import com.example.githubapp.ui.utils.app
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

const val GITHUB_USER = "GITHUB_USER"

class ProfileFragment : MvpAppCompatFragment(), ProfileView, BackButtonListener {

    companion object {
        fun newInstance(gitHubUser: GithubUser) =
            ProfileFragment().apply { arguments = bundleOf(GITHUB_USER to gitHubUser) }
    }

    private val gitHubUser: GithubUser? by lazy {
        arguments?.getParcelable(GITHUB_USER)
    }
    private val binding: FragmentProfileBinding by viewBinding(FragmentProfileBinding::bind)
    private val presenter: ProfilePresenter by moxyPresenter {
        ProfilePresenter(
            gitHubUser,
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
        binding.repositoriesRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = ProfileAdapter(presenter)
        binding.repositoriesRecyclerView.adapter = adapter
        binding.likeButton.setOnClickListener {

        }
    }

    override fun backPressed() = presenter.backPressed()

    override fun setUser(user: GithubUser) {
        binding.loginTextView.text = user.login.toString()
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