
package com.soundboost.app

import android.media.audiofx.LoudnessEnhancer
import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var enhancer: LoudnessEnhancer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startService(Intent(this, SoundBoostService::class.java))

        val slider = findViewById<SeekBar>(R.id.volumeSlider)
        slider.max = 100
        slider.progress = 50

        enhancer = LoudnessEnhancer(0) // audio session 0 = global
        enhancer.enabled = true

        slider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                enhancer.setTargetGain(progress * 1000) // em mB (milibels)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }
}
