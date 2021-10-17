package com.example.githubapp.ui.main


import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubapp.AndroidScreens
import com.example.githubapp.App
import com.example.githubapp.R
import com.example.githubapp.databinding.ActivityMainBinding
import com.example.githubapp.domain.GithubUsersRepo
import com.example.githubapp.impl.MainView
import com.example.githubapp.ui.users.BackButtonListener
import com.example.githubapp.ui.users.UsersRVAdapter
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private val navigator = AppNavigator(this, R.id.container)

    private val presenter by moxyPresenter { MainPresenter(App.instance.router, AndroidScreens()) }
    private var vb: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
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