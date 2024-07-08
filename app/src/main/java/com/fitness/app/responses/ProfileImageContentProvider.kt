package com.fitness.app.responses

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.net.Uri
import java.io.File

class ProfileImageContentProvider : ContentProvider() {

    companion object {
        private const val AUTHORITY = "com.fitness.package.name.profileimageprovider"
        val CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/profileImage")
    }

    override fun onCreate(): Boolean {
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        throw UnsupportedOperationException("Not implemented")
    }

    override fun getType(uri: Uri): String? {
        throw UnsupportedOperationException("Not implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        throw UnsupportedOperationException("Not implemented")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        throw UnsupportedOperationException("Not implemented")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        throw UnsupportedOperationException("Not implemented")
    }

    override fun openFile(uri: Uri, mode: String): android.os.ParcelFileDescriptor? {
        val context: Context = requireNotNull(context)

        val profilePictureFile = File(context.filesDir, "profile.jpg")
        return if (profilePictureFile.exists()) {
            android.os.ParcelFileDescriptor.open(profilePictureFile, android.os.ParcelFileDescriptor.MODE_READ_ONLY)
        } else {
            null
        }
    }
}
