package com.example.droidchat.core.file

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.example.droidchat.R
import java.io.File

class DroidChatFileProvider : FileProvider(R.xml.file_patchs) {

    companion object {
        fun getImageUri(context: Context): Uri {
            val tempFile = File.createTempFile("profile_image", ".jpg", context.cacheDir)

            val authority = context.packageName + ".fileprovider"

            return getUriForFile(
                context,
                authority,
                tempFile
            )
        }
    }
}