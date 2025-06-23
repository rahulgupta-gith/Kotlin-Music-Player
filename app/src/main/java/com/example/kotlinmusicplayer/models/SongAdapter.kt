package com.example.kotlinmusicplayer.models
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kotlinmusicplayer.R
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class SongAdapter(private var sList: List<Song>, private val onPlayClick: (String) -> Unit): RecyclerView.Adapter<SongAdapter.SongViewHolder>() {
    class SongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            val trackN : TextView = itemView.findViewById(R.id.trackName)
              val artistName: TextView = itemView.findViewById(R.id.artistName)
        val btnPlay: Button = itemView.findViewById(R.id.btnPlay)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):SongViewHolder{
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.song_item, parent, false)
        return SongViewHolder(view)
    }

    override fun onBindViewHolder(holder:SongViewHolder, position:Int){
        val song = sList[position]
        holder.trackN.text = song.trackName
        holder.artistName.text = song.artistName
        holder.btnPlay.setOnClickListener {
            onPlayClick(song.previewUrl)
        }
    }


    override fun getItemCount(): Int {
        return sList.size
    }

    fun updateList(newList: List<Song>) {
        sList = newList
        notifyDataSetChanged()
    }
}