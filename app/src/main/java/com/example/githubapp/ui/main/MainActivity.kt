package com.example.githubapp.ui.main


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import com.example.githubapp.AndroidScreens
import com.example.githubapp.App
import com.example.githubapp.R

import com.example.githubapp.databinding.ActivityScrollingBinding

import com.example.githubapp.impl.MainView
import com.example.githubapp.ui.users.BackButtonListener
import com.example.githubapp.ui.utils.app

import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.snackbar.Snackbar


import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView  {

    private lateinit var binding: ActivityScrollingBinding
    private val navigator = AppNavigator(this, R.id.container)

    private val presenter by moxyPresenter { MainPresenter(app.router, AndroidScreens()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {

        binding.toolbarLayout.title = title
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        app.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        app.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if(it is BackButtonListener && it.backPressed()){
                return
            }
        }
        presenter.backClicked()
    }
}
