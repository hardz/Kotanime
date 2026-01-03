package com.freshlybakedapps.kotanime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.freshlybakedapps.kotanime.core.factory.AnimeViewModelFactory
import com.freshlybakedapps.kotanime.core.room.AnimeDatabase
import com.freshlybakedapps.kotanime.core.network.RetrofitClient
import com.freshlybakedapps.kotanime.data.repositories.AnimeRepositoryImpl
import com.freshlybakedapps.kotanime.data.local.LocalDataSource
import com.freshlybakedapps.kotanime.data.local.entity.AnimeEntity
import com.freshlybakedapps.kotanime.data.remote.RemoteDataSource
import com.freshlybakedapps.kotanime.data.remote.model.Data
import com.freshlybakedapps.kotanime.ui.AnimeApp
import com.freshlybakedapps.kotanime.ui.home.AnimeViewModel
import com.freshlybakedapps.kotanime.ui.theme.KotAnimeTheme

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: AnimeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = Room.databaseBuilder(
            applicationContext,
            klass = AnimeDatabase::class.java,
            name = AnimeDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration(true).build()

        val localDataSource = object : LocalDataSource {
            private val dao = db.animeDao()

            override fun getAllAnime() = dao.getAllAnime()
            override fun getAnimeById(id: Int) = dao.getAnimeById(id)
            override fun insertAllAnime(anime: List<AnimeEntity>) = dao.insertAllAnime(anime)
            override fun deleteAllAnime() = dao.deleteAllAnime()
        }

        val remoteDataSource = object : RemoteDataSource {
            private val api = RetrofitClient.service
            override suspend fun getAnimeById(id: Int): AnimeEntity? {
                TODO("Not yet implemented")
            }

            override suspend fun getAllAnime(): List<Data> {
                return api.getAnimeList().data ?: emptyList()
            }
        }

        val repository = AnimeRepositoryImpl(localDataSource, remoteDataSource)

        val factory = AnimeViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[AnimeViewModel::class.java]

        setContent {
            KotAnimeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AnimeApp(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
