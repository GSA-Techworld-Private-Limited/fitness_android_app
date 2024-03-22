package com.fitness.app.modules.formone.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivityFormOneBinding
import com.fitness.app.modules.formone.`data`.viewmodel.FormOneVM
import com.fitness.app.modules.homecontainer.ui.HomeContainerActivity
import com.fitness.app.modules.login.Login
import com.fitness.app.modules.responses.SignUpResponse
import com.fitness.app.modules.services.ApiInterface
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import kotlin.String
import kotlin.Unit

class FormOneActivity : BaseActivity<ActivityFormOneBinding>(R.layout.activity_form_one) {
  private val viewModel: FormOneVM by viewModels<FormOneVM>()


  private lateinit var apiService: ApiInterface
  private lateinit var sessionManager: SessionManager
  private val pickImage = 100
  private var imageUri: Uri? = null

  lateinit var profileImageView: ImageView
  private lateinit var file: File


  var name:String=""
  var mobileNumber:String=""
  var email:String=""
  var dob:String=""
  var state:String=""
  var city:String=""
  var zipcode:String=""
  var usertye:String=""

  var multipartImage: MultipartBody.Part? = null

  private lateinit var spinnerUserType: Spinner
  private val userTypes = arrayOf("Athlete", "Trainer", "User")

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.formOneVM = viewModel

    sessionManager= SessionManager(this)
    apiService=ApiManager.apiInterface

    spinnerUserType = binding.userType1

    spinnerUserType.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, userTypes)

    usertye=spinnerUserType.selectedItem.toString()

    profileImageView=binding.profilePic

    window.statusBarColor= ContextCompat.getColor(this,R.color.white)
  }

  override fun setUpClicks(): Unit {
    binding.btnContinue.setOnClickListener {
      name=binding.etName.text.toString().trim()
      mobileNumber=binding.etMobileNo1.text.toString().trim()
      email=binding.etEmailID.text.toString().trim()
      dob=binding.etDDMMYYYY.text.toString().trim()
      state=binding.etState.text.toString().trim()
      city=binding.etCity.text.toString().trim()
      zipcode=binding.etZIPCode.text.toString().trim()

      if (TextUtils.isEmpty(name) || TextUtils.isEmpty(mobileNumber) || TextUtils.isEmpty(city) || TextUtils.isEmpty(state) || TextUtils.isEmpty(email) || TextUtils.isEmpty(usertye) || TextUtils.isEmpty(dob)) {
        // Display an error message
        Toast.makeText(this, "Please fill in all the required fields!!", Toast.LENGTH_SHORT).show()
      } else {
        signUp() // Call signUp function if all fields are filled
        binding.progressbar.visibility= View.VISIBLE
      }

    }

    binding.ivEdit.setOnClickListener{
      val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
      startActivityForResult(gallery, pickImage)
    }
  }


  private  fun signUp(
  ) {
    val map: MutableMap<String, RequestBody> = mutableMapOf()
    val name = createPartFromString(name)
    val mobileNumber=createPartFromString(mobileNumber)
    val email = createPartFromString(email)
    val dob=createPartFromString(dob)
    val state=createPartFromString(state)
    val city=createPartFromString(city)
    val pincode=createPartFromString(zipcode)
    val usertype=createPartFromString(usertye)

    map.put("name", name)
    map.put("mobile_number", mobileNumber)
    map.put("date_of_birth",dob)
    map.put("state",state)
    map.put("email", email)
    map.put("city", city)
    map.put("pin_code", pincode)
    map.put("user_type",usertype)



    // Parsing any Media type file
    //file= imageUri.path?.let { File(it) }!!
    //val file = File(profileImage)
    val requestFile: RequestBody = RequestBody.create(
      "image/jpg".toMediaType(),
      file
    )

    multipartImage =
      MultipartBody.Part.createFormData("profile", file.getName(), requestFile)

    apiService=ApiManager.apiInterface

    //val signUpResponse=SignUpResponse(name,phoneNumber,placeOfBirth,dateOfBirth,timeOfBirth,email,gender,fileBody)
    val call =  apiService.signUp(map, multipartImage!!)
    call.enqueue(object : Callback<SignUpResponse> {
      override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
        if (response.isSuccessful) {
          binding.progressbar.visibility= View.GONE
          val responseBody = response.body()
          if (responseBody != null) {
            Toast.makeText(this@FormOneActivity, "Registration successful,Please Login!!", Toast.LENGTH_SHORT).show()
            //Log.d("response_message",responseBody.)
            Log.d("response_data",responseBody.toString())
            val i=Intent(this@FormOneActivity,Login::class.java)
            startActivity(i)
            finishAffinity()
          } else {
            Toast.makeText(this@FormOneActivity, "Registration failed!! Mobile Number Already Registered", Toast.LENGTH_SHORT).show()
            Log.d(responseBody,"This fails in signup response")
            binding.progressbar.visibility= View.GONE
          }
        }
        else {
          Toast.makeText(this@FormOneActivity, "Registration Failed Mobile Number Already Registered", Toast.LENGTH_SHORT).show()
          Log.d(response.message(),"This fails in registration response")
          binding.progressbar.visibility= View.GONE
        }
      }
      override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
        Toast.makeText(this@FormOneActivity, "Registration failed: ${t.message}", Toast.LENGTH_SHORT).show()
        Log.d(t.message,"This fails in signup response")
        binding.progressbar.visibility= View.GONE
      }
    })
  }



  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (resultCode == RESULT_OK && requestCode == pickImage) {

      imageUri = data?.data!!
      profileImageView.setImageURI(imageUri)
      val selectedFileURI: Uri = imageUri as Uri
      file = getFile(this, imageUri!!)
      //file = File(selectedFileURI.path.toString())
      Log.d("", "File : " + file.name)
      //uploadedFileName = file.toString()
      println("upload file name ${file.absoluteFile}")

      Log.d("my location","$file")

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
    val returnCursor = context.contentResolver.query(uri, null, null, null, null)!!
    val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
    returnCursor.moveToFirst()
    val name = returnCursor.getString(nameIndex)
    returnCursor.close()
    return name
  }

  fun getPath(uri: Uri?): String? {
    val projection = arrayOf(MediaStore.Images.Media.DATA)
    val cursor = contentResolver.query(uri!!, projection, null, null, null) ?: return null
    val column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
    cursor.moveToFirst()
    val s = cursor.getString(column_index)
    cursor.close()
    return s
  }
  // Extensions.kt
  fun createPartFromString(stringData: String): RequestBody {
    return stringData.toRequestBody("text/plain".toMediaTypeOrNull())
  }


















  companion object {
    const val TAG: String = "FORM_ONE_ACTIVITY"
    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, FormOneActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
