package com.fitness.app.modules.videoplayeractivity

import android.content.pm.ActivityInfo
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.fitness.app.R
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.SimpleExoPlayerView
import com.google.android.exoplayer2.upstream.BandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

class VideoPlayerActivity : AppCompatActivity() {
    private var exoPlayer: SimpleExoPlayer? = null
    private lateinit var playerView: SimpleExoPlayerView
    private var videoUrl: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)


        // Lock orientation to landscape
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        // Retrieve video URL from intent
        videoUrl = intent.getStringExtra("videoUrl")
        Log.d("videoURL",videoUrl.toString())

        playerView = findViewById(R.id.playerView)


    }


    override fun onStart() {
        super.onStart()
        initializePlayer()
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }


    private fun initializePlayer() {
        CoroutineScope(Dispatchers.IO).launch {
            val resolvedUrl = resolveRedirectUrl(videoUrl!!)
            withContext(Dispatchers.Main) {
                if (exoPlayer == null) {
                    val bandwidthMeter: BandwidthMeter = DefaultBandwidthMeter()
                    val trackSelector = DefaultTrackSelector(AdaptiveTrackSelection.Factory(bandwidthMeter))
                    val loadControl = DefaultLoadControl()
                    exoPlayer = ExoPlayerFactory.newSimpleInstance(this@VideoPlayerActivity, trackSelector, loadControl)
                    playerView.player = exoPlayer

                    val dataSourceFactory = DefaultDataSourceFactory(
                        this@VideoPlayerActivity,
                        "ExoPlayer"
                    )
                    val extractorsFactory = DefaultExtractorsFactory()
                    val videoUri = Uri.parse(resolvedUrl)
                    val mediaSource = ExtractorMediaSource(videoUri, dataSourceFactory, extractorsFactory, null, null)
                    exoPlayer?.prepare(mediaSource)
                    exoPlayer?.playWhenReady = false
                }
            }
        }
    }



    private fun releasePlayer() {
        exoPlayer?.release()
        exoPlayer = null
    }

    private fun resolveRedirectUrl(url: String): String {
        var resolvedUrl = url
        try {
            val connection = URL(url).openConnection() as HttpURLConnection
            connection.instanceFollowRedirects = false
            val responseCode = connection.responseCode
            if (responseCode in 300..399) {
                resolvedUrl = connection.getHeaderField("Location")
            }
            connection.disconnect()
        } catch (e: Exception) {
            Log.e("VideoPlayerActivity", "Error resolving redirect URL", e)
        }
        return resolvedUrl
    }
}