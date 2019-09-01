package ba.ahavic.artistfy.data.base.network

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.core.content.ContextCompat
import ba.ahavic.artistfy.ui.base.AppError
import ba.ahavic.artistfy.ui.base.AppException
import ba.ahavic.artistfy.ui.base.ReasonOfError
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageDownloader @Inject constructor(private val context: Context) {
    fun downloadImage(imageUrl: String): String? {
        val writeExternalStoragePermission =
            ContextCompat.checkSelfPermission(context, WRITE_EXTERNAL_STORAGE)
        if (writeExternalStoragePermission != PackageManager.PERMISSION_GRANTED) {
            return imageUrl
        } else {
            context.getExternalFilesDir(DIR_NAME)?.let { directory ->
                val fileName = imageUrl.substringAfterLast("/")

                if (!directory.exists()) {
                    directory.mkdirs()
                }

                val destinationAbsoluteFilePath = "$directory/$fileName"

                if (File(destinationAbsoluteFilePath).exists()) {
                    return destinationAbsoluteFilePath
                }

                val downloadManager =
                    context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                val request = DownloadManager.Request(Uri.parse(imageUrl))
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                    .setAllowedOverRoaming(false)
                    .setTitle(fileName)
                    .setMimeType("image/jpeg/png")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN)
                    .setDestinationInExternalFilesDir(context, DIR_NAME, fileName)

                downloadManager.enqueue(request)
                return destinationAbsoluteFilePath
            }
            return null
        }
    }

    companion object {
        private const val DIR_NAME = "albums_graphic/"
    }
}
