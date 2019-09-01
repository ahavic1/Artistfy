package ba.ahavic.artistfy.base

import ba.ahavic.artistfy.ui.data.Album
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader

object TestUtil {

    private const val mocksPath = "../app/src/test/java/ba/ahavic/artistfy/mocks/"
    private const val albumsMockFile = "albums.json"
    private const val albumMockFile = "album.json"

    fun createAlbums(): List<Album> = loadJson<List<Album>>(albumsMockFile)

    fun createAlbum(): Album = loadJson<Album>(albumMockFile)

    private inline fun <reified T> loadJson(file: String): T {
        val br = BufferedReader(InputStreamReader(FileInputStream(mocksPath + file)))
        val sb = StringBuilder()
        var line = br.readLine()
        while (line != null) {
            sb.append(line)
            line = br.readLine()
        }
        return Gson().fromJson(sb.toString(), T::class.java)
    }
}
