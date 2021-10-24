package com.example.githubapp.domain

interface IItemView {
    var pos: Int
}
interface UserItemView: IItemView {
    fun setLogin(text: String)
}