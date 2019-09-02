package ba.ahavic.artistfy.main

import android.os.Bundle
import androidx.lifecycle.Observer
import ba.ahavic.artistfy.base.BaseViewModelTest
import ba.ahavic.artistfy.base.TestUtil
import ba.ahavic.artistfy.data.album.AlbumRepository
import ba.ahavic.artistfy.ui.base.AppError
import ba.ahavic.artistfy.ui.base.AppException
import ba.ahavic.artistfy.ui.base.ReasonOfError
import ba.ahavic.artistfy.ui.data.Album
import ba.ahavic.artistfy.ui.main.albumdetails.AlbumDetailsViewModel
import ba.ahavic.artistfy.ui.main.albumdetails.AlbumSaveFailed
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class AlbumDetailsViewModelTest : BaseViewModelTest() {

    @Mock
    lateinit var albumObserver: Observer<Album>

    @Mock
    private lateinit var albumRepository: AlbumRepository

    @Mock
    lateinit var arguments: Bundle

    @InjectMocks
    private lateinit var viewModel: AlbumDetailsViewModel


    @Before
    override fun setup() {
        super.setup()
        viewModel.isLoading.observeForever(loadingObserver)
        viewModel.error.observeForever(errorObserver)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `test missing arguments`() {
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
        verify(loadingObserver, times(1)).onChanged(true)
        verify(loadingObserver, times(1)).onChanged(false)
    }

    @Test
    fun `test save to my albums database exception`() = runBlockingTest {
        val album = TestUtil.createAlbum()
        `when`(arguments.get("album")).thenReturn(album)
        `when`(arguments.containsKey("album")).thenReturn(true)
        `when`(albumRepository.getAlbumInfo(album)).thenReturn(album)
        given(albumRepository.saveAlbum(album)).willAnswer {
            throw AppException(AppError(ReasonOfError.DatabaseSaveFailed))
        }

        viewModel.arguments = arguments
        viewModel.album.observeForever(albumObserver)

        viewModel.actionSaveToMyAlbums()

        verify(errorObserver, times(1)).onChanged(AlbumSaveFailed)
        verify(loadingObserver, times(2)).onChanged(true)
        verify(loadingObserver, times(2)).onChanged(false)
    }

    @Test
    fun `test save to my albums`() = runBlockingTest {
        val album = TestUtil.createAlbum().copy(cached = false)
        `when`(arguments.get("album")).thenReturn(album)
        `when`(arguments.containsKey("album")).thenReturn(true)
        `when`(albumRepository.getAlbumInfo(album)).thenReturn(album)
        `when`(albumRepository.saveAlbum(album)).thenReturn(true)

        viewModel.arguments = arguments
        viewModel.album.observeForever(albumObserver)

        viewModel.actionSaveToMyAlbums()

        verify(albumObserver, times(1)).onChanged(album.copy(cached = true))
        verify(loadingObserver, times(2)).onChanged(true)
        verify(loadingObserver, times(2)).onChanged(false)
    }

    @Test
    fun `test remove from my albums`() = runBlockingTest {
        val album = TestUtil.createAlbum().copy(cached = true)
        `when`(arguments.get("album")).thenReturn(album)
        `when`(arguments.containsKey("album")).thenReturn(true)
        `when`(albumRepository.getAlbumInfo(album)).thenReturn(album)
        `when`(albumRepository.saveAlbum(album)).thenReturn(true)

        viewModel.arguments = arguments
        viewModel.album.observeForever(albumObserver)

        viewModel.actionRemoveFromMyAlbums()

        verify(albumObserver, times(1)).onChanged(album.copy(cached = false))
        verify(loadingObserver, times(2)).onChanged(true)
        verify(loadingObserver, times(2)).onChanged(false)
    }
}