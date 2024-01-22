package com.fitness.app.modules.feedstwo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fitness.app.R
import com.fitness.app.modules.responses.TestimonalVideos
import com.fitness.app.modules.responses.TrainingVideos
import com.fitness.app.modules.services.ApiManager
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory

class FeedTwoAdapter(
    var list: List<TestimonalVideos>
) : RecyclerView.Adapter<FeedTwoAdapter.VideoViewHolder>() {

    lateinit var exoPlayer: SimpleExoPlayer
    lateinit var exoplayerView: PlayerView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_testimnals_videos, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        return holder.bindView(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateData(newData: List<TestimonalVideos>) {
        list = newData
        notifyDataSetChanged()
    }

    inner class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val exoplayerView: PlayerView = view.findViewById(R.id.playerview)!!
        val videoName: TextView = view.findViewById(R.id.videoName)

        fun bindView(postModel: TestimonalVideos) {
            videoName.text = postModel.testimonalVideoName

            if (postModel.testimonalVideo.isNullOrBlank()) {
                // Handle the case when uploadVideo is null or empty
                // For example, show an empty state or perform any other action
                exoplayerView.visibility = View.GONE
            } else {
                val videoUri = ApiManager.getVideoUrl(postModel.testimonalVideo!!)
                // val bandwidthMeter: BandwidthMeter = DefaultBandwidthMeter()
                val trackSelector: TrackSelector = DefaultTrackSelector(itemView.context, AdaptiveTrackSelection.Factory())
                exoPlayer = SimpleExoPlayer.Builder(itemView.context).setTrackSelector(trackSelector).build()

                val mediaItem = MediaItem.fromUri(videoUri)

                val dataSourceFactory: DefaultDataSourceFactory =
                    DefaultDataSourceFactory(itemView.context, "Exoplayer_Video")

                val mediaSource: MediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(mediaItem)

                exoplayerView.player = exoPlayer

                exoPlayer.setMediaSource(mediaSource)
                exoPlayer.prepare()
                exoPlayer.playWhenReady = true
            }
        }




    }


}
