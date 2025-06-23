package com.example.kotlinmusicplayer

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinmusicplayer.models.SongAdapter
import com.example.kotlinmusicplayer.models.SongResponse
import com.example.kotlinmusicplayer.retro.ClientRetro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: SongAdapter
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val input = findViewById<EditText>(R.id.searchInput)
        val button1 = findViewById<Button>(R.id.searchButton)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        adapter = SongAdapter(emptyList()) { previewUrl ->
            playMusic(previewUrl)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        button1.setOnClickListener {
            val term = input.text.toString()
            if (term.isNotEmpty()) {
                searchSongs(term)
            }
        }
    }

    private fun playMusic(url: String) {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer().apply {
            setDataSource(url)
            prepareAsync()
            setOnPreparedListener {
                it.start()
            }
            setOnCompletionListener {
                it.release()
            }
        }
    }

    private fun searchSongs(term: String) {
        val call = ClientRetro.api.searchSongs(term)
        call.enqueue(object : Callback<SongResponse> {
            override fun onResponse(call: Call<SongResponse>, response: Response<SongResponse>) {
                val songs = response.body()?.results ?: emptyList()
                adapter.updateList(songs)
            }

            override fun onFailure(call: Call<SongResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}
