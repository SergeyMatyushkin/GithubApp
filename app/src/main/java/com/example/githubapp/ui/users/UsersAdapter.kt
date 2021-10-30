package com.example.githubapp.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.data.GithubUser
import com.example.githubapp.data.domain.UserItemView
import com.example.githubapp.databinding.ItemUserBinding


import com.example.githubapp.data.domain.UserListPresenter

class UsersAdapter(private val presenter: UserListPresenter) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(private val vb: ItemUserBinding) : RecyclerView.ViewHolder(vb.root),
        UserItemView {
        override var pos = -1

        override fun setGitUser(gitHunUser: GithubUser) = with(vb) {
            loginTextView.text = gitHunUser.login.toString()
        }
    }

}