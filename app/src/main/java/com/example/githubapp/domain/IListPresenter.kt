package com.example.githubapp.domain

interface IListPresenter<V : IItemView> {

    //По аналогии с интерфейсом IItemView мы создали интерфейс IListPresenter, куда вынесли общие для всех списков вещи:
    //слушатель клика;
    //функция получения количества элементов;
    //функция наполнения View.

    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int

}
interface IUserListPresenter : IListPresenter<UserItemView>