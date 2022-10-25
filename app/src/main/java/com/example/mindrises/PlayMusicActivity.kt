package com.example.mindrises

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_play_music.*

class PlayMusicActivity : AppCompatActivity() {

    var buttonMusic=HashMap<Button,MediaPlayer>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_music)
        buttonMusic.put(note1,MediaPlayer.create(this,R.raw.note1))
        buttonMusic.put(note2,MediaPlayer.create(this,R.raw.note2))
        buttonMusic.put(note3,MediaPlayer.create(this,R.raw.note3))
        buttonMusic.put(note4,MediaPlayer.create(this,R.raw.note4))
        val intent=getIntent()
    }

    fun playMusic(view: View) {
        val b=view as Button
        val playback=buttonMusic.get(b)
        playback?.start()
    }
}