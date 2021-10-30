package com.example.githubapp.data.domain

interface ListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface UserListPresenter : ListPresenter<UserItemView>