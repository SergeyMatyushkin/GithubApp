package com.example.githubapp.data.Dependency

import android.content.Context
import androidx.room.Room
import com.example.githubapp.AndroidScreens
import com.example.githubapp.IScreens
import com.example.githubapp.data.datasource.GitHubApi
import com.example.githubapp.data.domain.EventBus
import com.example.githubapp.data.domain.NetworkStatusImpl
import com.example.githubapp.data.repositori.GithubUserRepoCombinedImpl
import com.example.githubapp.data.repositori.GithubUsersLocalRepoImpl
import com.example.githubapp.data.repositori.GithubUsersRepo
import com.example.githubapp.data.repositori.GithubUsersWebRepoImpl
import com.example.githubapp.data.room.GithubDatabase
import com.example.githubapp.ui.main.MainActivity
import com.example.githubapp.ui.main.MainPresenter
import com.example.githubapp.ui.other.SchedulerProvider
import com.example.githubapp.ui.profile.ProfilePresenter
import com.example.githubapp.ui.users.UsersPresenter

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

const val DB_NAME = "githubDB"
const val BASE_URL = "https://api.github.com"

@Module
class AppModule(val context:Context){

    @Singleton
    @Provides
    fun provideContext(): Context{
        return context
    }

    @Singleton
    @Provides
    fun provideCreateDB(context:Context):GithubDatabase{
        return Room.databaseBuilder(context, GithubDatabase::class.java, DB_NAME).build()
    }

    @Singleton
    @Provides
    fun provideEventBus(): EventBus{
        return EventBus
    }

}

@Module
class CiceroneModule{
    @Singleton
    @Provides
    fun provideCiceroneCreate():Cicerone<Router>{
        return Cicerone.create()
    }
    @Singleton
    @Provides
    fun provideNavigateHolder(cicerone:Cicerone<Router>): NavigatorHolder{
        return cicerone.getNavigatorHolder()
    }
    @Singleton
    @Provides
    fun provideRouter(cicerone:Cicerone<Router>):Router{
        return  cicerone.router
    }
    @Singleton
    @Provides
    fun screens(): IScreens = AndroidScreens()
}

@Module
class ApiModule{

    @Singleton
    @Provides
    fun provideMoshiBuild(): Moshi{
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Singleton
    @Provides
    fun provideStethoInterceptor(): OkHttpClient{
        return OkHttpClient.Builder().addNetworkInterceptor(StethoInterceptor()).build()
    }

    @Provides
    fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): GitHubApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(GitHubApi::class.java)
    }

}

@Module
class RepoModule{
    @Singleton
    @Provides
    fun provideRepo(db: GithubDatabase,githubApi: GitHubApi, context: Context):GithubUsersRepo{
        return GithubUserRepoCombinedImpl(
            GithubUsersLocalRepoImpl(db),
            GithubUsersWebRepoImpl(githubApi),
            NetworkStatusImpl(context),
            SchedulerProvider()
        )
    }

}

@Singleton
@Component(modules = [AppModule::class,CiceroneModule::class,ApiModule::class,RepoModule::class])
interface AppComponent{
    fun inject(activity:MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(userPresenter: UsersPresenter)
    fun inject(profilePresenter: ProfilePresenter)
}