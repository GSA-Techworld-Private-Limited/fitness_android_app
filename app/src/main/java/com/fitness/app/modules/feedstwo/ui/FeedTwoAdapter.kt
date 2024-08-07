package com.fitness.app.modules.feedstwo.ui

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
import com.fitness.app.modules.responses.TestimonalVideos
import com.fitness.app.modules.responses.TrainingVideos
import com.fitness.app.modules.services.ApiManager
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
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory

class FeedTwoAdapter(
    var list: List<TestimonalVideos>
) : RecyclerView.Adapter<FeedTwoAdapter.VideoViewHolder>() {


    lateinit var exoPlayer: SimpleExoPlayer

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

    override fun onViewRecycled(holder: VideoViewHolder) {
        super.onViewRecycled(holder)
        holder.pausePlayer()

    }


    inner class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //val exoplayerView: PlayerView = view.findViewById(R.id.playerview)!!
        val videoName: TextView = view.findViewById(R.id.videoName)
        private val exoPlayerView: SimpleExoPlayerView = view.findViewById(R.id.playerview)
        private val orientationIcon: ImageView =view.findViewById(R.id.orientationIcon)

        init {
            // Initialize ExoPlayer in the constructor
            val bandwidthMeter: BandwidthMeter = DefaultBandwidthMeter()
            val trackSelector: TrackSelector =
                DefaultTrackSelector(AdaptiveTrackSelection.Factory(bandwidthMeter))
            exoPlayer = ExoPlayerFactory.newSimpleInstance(itemView.context, trackSelector)
        }
        fun bindView(postModel: TestimonalVideos) {
            videoName.text = postModel.testimonalVideoName

            if (postModel.testimonalVideo.isNullOrBlank()) {
                // Handle the case when uploadVideo is null or empty
                // For example, show an empty state or perform any other action
                exoPlayerView.visibility = View.GONE
            } else {
                val videoFromServer=postModel.testimonalVideo
                val videoUri = ApiManager.getVideoUrl(videoFromServer!!)
                // bandwidthmeter is used for
                // getting default bandwidth
                val context = itemView.context

                if (isNetworkAvailable(context)) {
                    val bandwidthMeter: BandwidthMeter = DefaultBandwidthMeter()

                    // track selector is used to navigate between
                    // video using a default seekbar.
                    val trackSelector: TrackSelector =
                        DefaultTrackSelector(AdaptiveTrackSelection.Factory(bandwidthMeter))

                    // we are adding our track selector to exoplayer.
                    exoPlayer = ExoPlayerFactory.newSimpleInstance(itemView.context, trackSelector)

                    // we are parsing a video url
                    // and parsing its video uri.
                    val videoURI: Uri = Uri.parse(videoUri)

                    val dataSourceFactory = DefaultHttpDataSourceFactory(
                        "Exoplayer_video", null,
                        DefaultHttpDataSource.DEFAULT_CONNECT_TIMEOUT_MILLIS,
                        DefaultHttpDataSource.DEFAULT_READ_TIMEOUT_MILLIS, true
                    )


                    // we are creating a variable for extractor factory
                    // and setting it to default extractor factory.
                    val extractorsFactory: ExtractorsFactory = DefaultExtractorsFactory();

                    // we are creating a media source with above variables
                    // and passing our event handler as null,
                    val mediaSourse: MediaSource =
                        ExtractorMediaSource(
                            videoURI,
                            dataSourceFactory,
                            extractorsFactory,
                            null,
                            null
                        )

                    // inside our exoplayer view
                    // we are setting our player
                    exoPlayerView.player = exoPlayer

                    // we are preparing our exoplayer
                    // with media source.
                    exoPlayer.prepare(mediaSourse)

                    // we are setting our exoplayer
                    // when it is ready.
                    exoPlayer.playWhenReady = false
                }

                orientationIcon.setOnClickListener {
                    val i= Intent(itemView.context, VideoPlayerActivity::class.java)
                    i.putExtra("videoUrl",videoUri)
                    itemView.context.startActivity(i)
                }
            }
        }


        init {
            exoPlayerView.setOnClickListener {
                // Start playing the video when the ExoPlayer view is clicked
                exoPlayer.playWhenReady = true
            }
        }

        fun pausePlayer() {
            exoPlayerView.player.playWhenReady=false
        }


        private fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }


    }


}
