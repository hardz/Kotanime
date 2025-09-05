package com.example.seekhotest

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
import com.example.seekhotest.core.factory.AnimeViewModelFactory
import com.example.seekhotest.core.room.AnimeDatabase
import com.example.seekhotest.core.network.RetrofitClient
import com.example.seekhotest.data.repositories.AnimeRepositoryImpl
import com.example.seekhotest.data.local.LocalDataSource
import com.example.seekhotest.data.local.entity.AnimeEntity
import com.example.seekhotest.data.remote.RemoteDataSource
import com.example.seekhotest.data.remote.model.Data
import com.example.seekhotest.ui.AnimeApp
import com.example.seekhotest.ui.home.AnimeViewModel
import com.example.seekhotest.ui.theme.SeekhoTestTheme

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
            SeekhoTestTheme {
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
