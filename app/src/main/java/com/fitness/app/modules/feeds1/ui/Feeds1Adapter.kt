package com.fitness.app.modules.feeds1.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fitness.app.R
import com.fitness.app.modules.responses.TrainingVideos
import com.fitness.app.modules.videoplayeractivity.VideoPlayerActivity
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.extractor.ExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelector
import com.google.android.exoplayer2.ui.SimpleExoPlayerView
import com.google.android.exoplayer2.upstream.BandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory


class Feeds1Adapter(
    var list: List<TrainingVideos>
) : RecyclerView.Adapter<Feeds1Adapter.VideoViewHolder>() {

    private var exoPlayer: SimpleExoPlayer? = null
    private var currentPlayerView: SimpleExoPlayerView? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_feeds_training, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bindView(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateData(newData: ArrayList<TrainingVideos>) {
        list = newData
        notifyDataSetChanged()
    }

    override fun onViewRecycled(holder: VideoViewHolder) {
        super.onViewRecycled(holder)
        holder.releasePlayer()
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        pausePlayer()
    }

    inner class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val exoPlayerView: SimpleExoPlayerView = view.findViewById(R.id.playerview)
        private val videoName: TextView = view.findViewById(R.id.videoName)
        private val orientationIcon: ImageView = view.findViewById(R.id.orientationIcon)

        init {
            val bandwidthMeter: BandwidthMeter = DefaultBandwidthMeter()
            val trackSelector: TrackSelector =
                DefaultTrackSelector(AdaptiveTrackSelection.Factory(bandwidthMeter))
            exoPlayer = ExoPlayerFactory.newSimpleInstance(itemView.context, trackSelector)
        }

        fun bindView(postModel: TrainingVideos) {
            videoName.text = postModel.feedVideoName

            if (postModel.uploadVideo.isNullOrBlank()) {
                exoPlayerView.visibility = View.GONE
            } else {
                val videoUri = postModel.uploadVideo!!
                val context = itemView.context

                if (isNetworkAvailable(context)) {
                    val bandwidthMeter: BandwidthMeter = DefaultBandwidthMeter()
                    val trackSelector: TrackSelector =
                        DefaultTrackSelector(AdaptiveTrackSelection.Factory(bandwidthMeter))
                    exoPlayer = ExoPlayerFactory.newSimpleInstance(itemView.context, trackSelector)

                    val videoURI: Uri = Uri.parse(videoUri)
                    val dataSourceFactory = DefaultHttpDataSourceFactory(
                        "Exoplayer_video", null,
                        DefaultHttpDataSource.DEFAULT_CONNECT_TIMEOUT_MILLIS,
                        DefaultHttpDataSource.DEFAULT_READ_TIMEOUT_MILLIS, true
                    )
                    val extractorsFactory: ExtractorsFactory = DefaultExtractorsFactory()
                    val mediaSource: MediaSource = ExtractorMediaSource(
                        videoURI,
                        dataSourceFactory,
                        extractorsFactory,
                        null,
                        null
                    )

                    exoPlayerView.player = exoPlayer
                    exoPlayer!!.prepare(mediaSource)
                    exoPlayer!!.playWhenReady = false

                    currentPlayerView = exoPlayerView
                }

                orientationIcon.setOnClickListener {
                    exoPlayerView.player.playWhenReady = false
                    val intent = Intent(itemView.context, VideoPlayerActivity::class.java)
                    intent.putExtra("videoUrl", postModel.uploadVideo)
                    itemView.context.startActivity(intent)
                }
            }
        }

        fun releasePlayer() {
            exoPlayerView.player?.release()
            exoPlayerView.player = null
        }

        private fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    }

    fun pausePlayer() {
        currentPlayerView?.player?.playWhenReady = false
        currentPlayerView?.player?.stop()
        currentPlayerView?.player?.release()
        currentPlayerView = null
    }
}
