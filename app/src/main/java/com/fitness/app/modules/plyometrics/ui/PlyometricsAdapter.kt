package com.fitness.app.modules.plyometrics.ui

import PlyometricsVMNew
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.fitness.app.R
import com.fitness.app.databinding.RowPlyometricsBinding
import com.fitness.app.modules.plyometrics.`data`.model.PlyometricsRowModel
import com.fitness.app.modules.plyometrics.data.viewmodel.PlyometricsVM
import com.fitness.app.modules.responses.TrainingVideos
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import com.fitness.app.modules.videoplayeractivity.VideoPlayerActivity
import com.fitness.app.responses.BooleanRequest
import com.fitness.app.responses.PlanVideos
import com.fitness.app.responses.UpdateResponse
import com.fitness.app.responses.UserActivePlanVideoResponses
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
import retrofit2.Call
import retrofit2.Response
import kotlin.Int
import kotlin.collections.List



class PlyometricsAdapter(
  var list: ArrayList<PlanVideos>,
  private val sessionManager: SessionManager
) : RecyclerView.Adapter<PlyometricsAdapter.RowPlyometricsVH>() {

  lateinit var exoPlayer: SimpleExoPlayer

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowPlyometricsVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_plyometrics,parent,false)
    return RowPlyometricsVH(view)
  }

  override fun onBindViewHolder(holder: RowPlyometricsVH, position: Int) {
    holder.bindView(list[position])
  }

  override fun getItemCount(): Int {
  return list.size
  }
  public fun updateData(newData: ArrayList<PlanVideos>) {
    list = newData
    notifyDataSetChanged()
  }



  inner class RowPlyometricsVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {

    private val exoPlayerView: SimpleExoPlayerView = view.findViewById(R.id.playerview)
    val btnComplete:AppCompatButton=view.findViewById(R.id.btnComplete)
    val progressBar:ProgressBar=view.findViewById(R.id.progressBar)

    private val orientationIcon: ImageView = view.findViewById(R.id.orientationIcon)

    init {
      // Initialize ExoPlayer in the constructor
      val bandwidthMeter: BandwidthMeter = DefaultBandwidthMeter()
      val trackSelector: TrackSelector =
        DefaultTrackSelector(AdaptiveTrackSelection.Factory(bandwidthMeter))
      exoPlayer = ExoPlayerFactory.newSimpleInstance(itemView.context, trackSelector)
    }


    fun bindView(postModel: PlanVideos) {

      if (postModel.video.isNullOrBlank()) {
        // Handle the case when uploadVideo is null or empty
        // For example, show an empty state or perform any other action
        exoPlayerView.visibility = View.GONE
      } else {
        val videoUri = postModel.video
        val file=ApiManager.getVideoUrl(videoUri!!)
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
          val videoURI: Uri = Uri.parse(file)

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
      }


      orientationIcon.setOnClickListener {
        val file=ApiManager.getVideoUrl(postModel.video!!)
        val intent = Intent(itemView.context, VideoPlayerActivity::class.java)
        intent.putExtra("videoUrl", file)
        itemView.context.startActivity(intent)
      }




     // listener.onVideoComplete(postModel.id!!)


      val isComplete = postModel.isCompleted

      if (isComplete == true) {
        btnComplete.text = if (isComplete) "Completed" else "Complete"
        Toast.makeText(itemView.context, "Completed", Toast.LENGTH_SHORT).show()
      } else {
        btnComplete.setOnClickListener {
          val id = postModel.id

          // Toggle the value of isComplete
          val updatedIsComplete = !isComplete!!

          // Call patchUserActiveVideos with updated value of isComplete
          patchUserActiveVideos(id!!, updatedIsComplete)

          progressBar.visibility = View.VISIBLE
        }
      }





    }


    fun patchUserActiveVideos(id:Int,isCompleted:Boolean){
      val serviceGenerator= ApiManager.apiInterface
      val accessToken=sessionManager.fetchAuthToken()
      val authorization="Token $accessToken"
      val request = BooleanRequest(isCompleted)
      val call=serviceGenerator.updateactiveplansvideos(authorization,id,request)

      call.enqueue(object : retrofit2.Callback<UpdateResponse>{
        override fun onResponse(
          call: Call<UpdateResponse>,
          response: Response<UpdateResponse>
        ) {
          progressBar.visibility=View.GONE
          if(response.isSuccessful){
            btnComplete.text = if (isCompleted) "Completed" else "Complete"
            Toast.makeText(itemView.context,"Completed", Toast.LENGTH_SHORT).show()
          }
        }

        override fun onFailure(call: Call<UpdateResponse>, t: Throwable) {
          t.printStackTrace()
          Log.e("error", t.message.toString())
          progressBar.visibility=View.GONE

        }
      })
    }
    init {
      exoPlayerView.setOnClickListener {
        // Start playing the video when the ExoPlayer view is clicked
        exoPlayer.playWhenReady = true
      }
    }

    private fun isNetworkAvailable(context: Context): Boolean {
      val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
      val networkInfo = connectivityManager.activeNetworkInfo
      return networkInfo != null && networkInfo.isConnected
    }



  }
}
