package com.fitness.app.modules.profile.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivityProfileBinding
import com.fitness.app.modules.profile.`data`.viewmodel.ProfileVM
import com.fitness.app.modules.responses.ProfileResponse
import com.fitness.app.modules.responses.UserDetailResponses
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import kotlin.String
import kotlin.Unit

class ProfileActivity : BaseActivity<ActivityProfileBinding>(R.layout.activity_profile) {
  private val viewModel: ProfileVM by viewModels<ProfileVM>()


  private lateinit var sessionManager: SessionManager

  var name:String=""
  var mobileNumner:String=""
  var email:String=""

  private val pickImage = 100
  private var imageUri: Uri? = null
  private lateinit var nimage: ImageView
  private lateinit var updatedimagefile: File
  private var profilePictureFile: File? = null
  private var profilePictureUri: String? = null

  private var profile_picture:String=""


  var multipartImage: MultipartBody.Part? = null
  override fun onInitialized(): Unit {
    sessionManager= SessionManager(this)
    viewModel.navArguments = intent.extras?.getBundle("bundle")

//    binding.etAshishB.setText(sessionManager.fetchName())
//
//    binding.etDDMMYYYY.setText(sessionManager.featchDOB())

    nimage=binding.imageEllipseFifteen
//
//    val image=sessionManager.fetchProfile()
//    val file= ApiManager.getImageUrl(image!!)
//    Glide.with(this)
//      .load(file)
//      .apply(RequestOptions.bitmapTransform(CircleCrop()))
//      .into(binding.imageEllipseFifteen)


    getUserDetails()

    binding.imageEdit.setOnClickListener {
      val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
      startActivityForResult(gallery, pickImage)
    }


    binding.btnUpdate.setOnClickListener {
      name = binding.etAshishB.text.toString()
      mobileNumner = binding.etMobileNo.text.toString()
      email = binding.etMobileNo1.text.toString()

      if (name.isEmpty() || mobileNumner.isEmpty() || email.isEmpty()) {
        Toast.makeText(this, "Please enter anything to update the profile!!", Toast.LENGTH_SHORT).show()
      } else {
        updateData()
        binding.progressBar.visibility = View.VISIBLE
      }
    }


    binding.profileVM = viewModel
    window.statusBarColor= ContextCompat.getColor(this,R.color.white)

  }


  fun getUserDetails(){
    val serviceGenerator= ApiManager.apiInterface
    val accessToken=sessionManager.fetchAuthToken()
    val authorization="Token $accessToken"
    val call=serviceGenerator.userDetails(authorization)

    call.enqueue(object : retrofit2.Callback<UserDetailResponses>{
      override fun onResponse(
        call: Call<UserDetailResponses>,
        response: Response<UserDetailResponses>
      ) {
        val userDetails=response.body()!!

        binding.etAshishB.setText(userDetails.data!!.name)
        binding.etDDMMYYYY.setText(userDetails.data!!.dateOfBirth)
        binding.etMobileNo.setText(userDetails.data!!.mobileNumber)
        binding.etMobileNo1.setText(userDetails.data!!.email)
        binding.etState.setText(userDetails.data!!.state)
        binding.etCity.setText(userDetails.data!!.city)
        binding.etZIPCode.setText(userDetails.data!!.pinCode)

        profilePictureUri=userDetails.data!!.profile!!
        val file=ApiManager.getImageUrl(profilePictureUri!!)

        Glide.with(this@ProfileActivity)
          .load(file)
          .apply(RequestOptions.bitmapTransform(CircleCrop()))
          .into(binding.imageEllipseFifteen)


        profilePictureFile = getFile(this@ProfileActivity, profilePictureUri!!.toUri())
      }

      override fun onFailure(call: Call<UserDetailResponses>, t: Throwable) {
        t.printStackTrace()
        Log.e("error", t.message.toString())
      }
    })
  }

  private fun updateData(){
    val map: MutableMap<String, RequestBody> = mutableMapOf()
    val name=createPartFromString(name)
    val mobileNumner=createPartFromString(mobileNumner)
    val email=createPartFromString(email)


   // updatedimagefile=getFile(this,imageUri!!)

    map.put("name",name)
    map.put("phone_number",mobileNumner)
    map.put("email",email)



    if (imageUri != null) {
      updatedimagefile = getFile(this, imageUri!!)
    } else if (profilePictureUri != null) {
      updatedimagefile = profilePictureFile!!
    }

    val fileToSend = updatedimagefile
    val requestFile = fileToSend.let { RequestBody.create("image/jpg".toMediaType(), it) }
    multipartImage =
      requestFile?.let { MultipartBody.Part.createFormData("profile", fileToSend.name, it) }


    val serviceGenerator = ApiManager.apiInterface
    val accessToken = sessionManager.fetchAuthToken()
    val authorization = "Token $accessToken"
    val call = serviceGenerator.updateProfile(authorization,map,multipartImage!!)
    call.enqueue(object : retrofit2.Callback<ProfileResponse> {

      override fun onResponse(
        call: Call<ProfileResponse>,
        response: Response<ProfileResponse>,
      ) {
       binding.progressBar.visibility= View.GONE
        if (response.isSuccessful) {
          val profileResponse = response.body()
//                   if(profileResponse!=null){
//                       val name=findViewById<EditText>(R.id.etGroupTwentyEight11)
//                       val email=findViewById<EditText>(R.id.etGroupTwentyEight111)
//
//                       name.setText(profileResponse.username)
//                       email.setText(profileResponse.email)
//                   }

          Toast.makeText(
            this@ProfileActivity,
            "Profile updated successfully",
            Toast.LENGTH_SHORT
          ).show()

          profileUpdateSuccess()
        }else {
          // Handle API error
          Toast.makeText(
            this@ProfileActivity,
            "Failed to update profile",
            Toast.LENGTH_SHORT
          ).show()
        }
      }

      override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
        // Handle network failures or other errors
        Toast.makeText(this@ProfileActivity, "Please Update Profile Photo!!", Toast.LENGTH_SHORT).show()
        binding.progressBar.visibility= View.GONE
      }
    })


  }

  fun profileUpdateSuccess(){
    super.onBackPressed()
  }
  override fun setUpClicks(): Unit {
    binding.btnArrowright.setOnClickListener {
      this.finish()
    }
  }





  private fun createPartFromString(text: String): RequestBody {
    return RequestBody.create("text/plain".toMediaTypeOrNull(), text)
  }


  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (resultCode == RESULT_OK && requestCode == pickImage) {
      imageUri = data?.data!!
      nimage.setImageURI(imageUri)
      val selectedFileURI: Uri =imageUri!!
      updatedimagefile = getFile(this, selectedFileURI)
      //file = File(selectedFileURI.path.toString())
      Log.d("", "File : " + updatedimagefile.name)
      //uploadedFileName = file.toString()
      println("upload file name ${updatedimagefile.absoluteFile}")

      Log.d("my location","$updatedimagefile")

    }else{
      updatedimagefile=getFile(this,profile_picture.toUri())
//            updatedimagefile=getFile(this,imageUri)
//            Picasso.get().load(updatedimagefile).into(nimage)
    }
  }

  @Throws(IOException::class)
  fun getFile(context: Context, uri: Uri): File {
    val destinationFilename =
      File(context.filesDir.path + File.separatorChar + queryName(context, uri))
    try {
      context.contentResolver.openInputStream(uri).use { ins ->
        createFileFromStream(
          ins!!,
          destinationFilename
        )
      }
    } catch (ex: Exception) {
      Log.e("Save File", ex.message!!)
      ex.printStackTrace()
    }
    return destinationFilename
  }


  fun createFileFromStream(ins: InputStream, destination: File?) {
    try {
      FileOutputStream(destination).use { os ->
        val buffer = ByteArray(4096)
        var length: Int
        while (ins.read(buffer).also { length = it } > 0) {
          os.write(buffer, 0, length)
        }
        os.flush()
      }
    } catch (ex: Exception) {
      Log.e("Save File", ex.message!!)
      ex.printStackTrace()
    }
  }


  private fun queryName(context: Context, uri: Uri): String {
    var name = ""

    val returnCursor = context.contentResolver.query(uri, null, null, null, null)

    returnCursor?.use { cursor ->
      if (cursor.moveToFirst()) {
        val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        name = cursor.getString(nameIndex)
      }
    }

    return name
//        val returnCursor = context.contentResolver.query(uri, null, null, null, null)!!
//        val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
//        returnCursor.moveToFirst()
//        val name = returnCursor.getString(nameIndex)
//        returnCursor.close()
//        return name
  }

  companion object {
    const val TAG: String = "PROFILE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, ProfileActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
