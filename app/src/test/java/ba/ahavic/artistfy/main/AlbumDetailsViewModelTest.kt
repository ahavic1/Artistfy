package ba.ahavic.artistfy.main

import android.os.Bundle
import androidx.lifecycle.Observer
import ba.ahavic.artistfy.base.BaseViewModelTest
import ba.ahavic.artistfy.base.TestUtil
import ba.ahavic.artistfy.data.album.AlbumRepository
import ba.ahavic.artistfy.ui.data.Album
import ba.ahavic.artistfy.ui.main.albumdetails.AlbumDetailsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class AlbumDetailsViewModelTest : BaseViewModelTest() {

    @Mock
    lateinit var albumObserver: Observer<Album>

    @Mock
    private lateinit var albumRepository: AlbumRepository

    @Mock lateinit var arguments: Bundle

    @InjectMocks
    private lateinit var viewModel: AlbumDetailsViewModel


    @Before
    override fun setup() {
        super.setup()
        viewModel.isLoading.observeForever(loadingObserver)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `test missing arguments`()  {
        viewModel.album.observeForever(albumObserver)
    }

    @Test
    fun `test correct arguments`() = runBlockingTest {
        val album = TestUtil.createAlbum()
        `when`(arguments.get("album")).thenReturn(album)
        `when`(arguments.containsKey("album")).thenReturn(true)
        `when`(albumRepository.getAlbumInfo(album)).thenReturn(album)

        viewModel.arguments = arguments
        viewModel.album.observeForever(albumObserver)
        verify(albumObserver, times(1)).onChanged(album)
        verify(loadingObserver, times(2)).onChanged(false)
    }

    @Test
    fun `test save album database exception`() {
        val album = TestUtil.createAlbum()
    }
}