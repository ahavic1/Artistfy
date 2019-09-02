package ba.ahavic.artistfy.main

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import ba.ahavic.artistfy.base.BaseViewModelTest
import ba.ahavic.artistfy.base.TestUtil
import ba.ahavic.artistfy.data.album.AlbumRepository
import ba.ahavic.artistfy.ui.base.viewmodel.NavigationAction
import ba.ahavic.artistfy.ui.data.Album
import ba.ahavic.artistfy.ui.main.home.MyAlbumsFragmentDirections
import ba.ahavic.artistfy.ui.main.home.MyAlbumsViewModel
import ba.ahavic.artistfy.ui.main.home.NoAlbumsError
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class MyAlbumsViewModelTest : BaseViewModelTest() {

    @InjectMocks
    private lateinit var viewModel: MyAlbumsViewModel

    @Mock
    private lateinit var albumsRepository: AlbumRepository

    // Mock Observers
    @Mock
    lateinit var albumsObserver: Observer<List<Album>>

    @Mock
    lateinit var errorLayoutVisibilityObserver: Observer<Boolean>

    @Before
    override fun setup() {
        super.setup()
        viewModel.albums.observeForever(albumsObserver)
        viewModel.error.observeForever(errorObserver)
        viewModel.errorLayoutVisibility.observeForever(errorLayoutVisibilityObserver)
        viewModel.isLoading.observeForever(loadingObserver)
    }

    @Test
    fun `test no albums error`() = runBlockingTest {
        `when`(albumsRepository.getAlbums()).thenReturn(listOf())
        val lifecycle = LifecycleRegistry(mock(LifecycleOwner::class.java))
        lifecycle.addObserver(viewModel)
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        verify(errorObserver, times(1)).onChanged(NoAlbumsError)
        verify(errorLayoutVisibilityObserver, times(1)).onChanged(true)
        verify(loadingObserver, times(1)).onChanged(false)
        verify(loadingObserver, times(1)).onChanged(true)
    }

    @Test
    fun `test get albums`() = runBlockingTest {
        val albums = TestUtil.createAlbums()
        `when`(albumsRepository.getAlbums()).thenReturn(albums)
        val lifecycle = LifecycleRegistry(mock(LifecycleOwner::class.java))
        lifecycle.addObserver(viewModel)
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        verify(albumsObserver, times(1)).onChanged(albums)
        verify(loadingObserver, times(1)).onChanged(false)
        verify(loadingObserver, times(1)).onChanged(true)
    }

    @Test
    fun `test navigation to album details`() {
        val album = TestUtil.createAlbum()
        viewModel.navigationAction.observeForever(navigationObserver)
        viewModel.actionAlbumSelected(album)
        verify(navigationObserver, times(1)).onChanged(
            NavigationAction.To(MyAlbumsFragmentDirections.actionAlbumsToAlbumDetails(album))
        )
    }

    @Test
    fun `test navigation to search artists`() {
        viewModel.navigationAction.observeForever(navigationObserver)
        viewModel.goToSearchArtists()
        verify(navigationObserver, times(1)).onChanged(
            NavigationAction.To(MyAlbumsFragmentDirections.actionAlbumsToSearchArtist())
        )
    }
}
