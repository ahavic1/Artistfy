package ba.ahavic.artistfy.main

import androidx.lifecycle.Observer
import ba.ahavic.artistfy.base.BaseViewModelTest
import ba.ahavic.artistfy.base.TestUtil
import ba.ahavic.artistfy.data.artist.ArtistRepository
import ba.ahavic.artistfy.ui.base.AppError
import ba.ahavic.artistfy.ui.base.AppException
import ba.ahavic.artistfy.ui.base.ReasonOfError
import ba.ahavic.artistfy.ui.base.viewmodel.NavigationAction
import ba.ahavic.artistfy.ui.data.Artist
import ba.ahavic.artistfy.ui.main.searchartist.InvalidSearchQueryError
import ba.ahavic.artistfy.ui.main.searchartist.SearchArtistFragmentDirections
import ba.ahavic.artistfy.ui.main.searchartist.SearchArtistViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class SearchArtistViewModelTest : BaseViewModelTest() {

    @Mock
    private lateinit var artistRepository: ArtistRepository

    @Mock
    private lateinit var artistObserver: Observer<List<Artist>>

    @InjectMocks
    private lateinit var viewModel: SearchArtistViewModel


    @Before
    override fun setup() {
        super.setup()
        viewModel.isLoading.observeForever(loadingObserver)
    }

    @Test
    fun `test action search artists`() = runBlockingTest {
        val searchText = "Audioslave"
        val artists = TestUtil.createArtists()
        `when`(artistRepository.getArtists(searchText)).thenReturn(artists)

        viewModel.searchText.value = searchText
        viewModel.artists.observeForever(artistObserver)

        viewModel.actionSearchArtists()

        verify(artistObserver, times(1)).onChanged(artists)
        verify(loadingObserver, times(1)).onChanged(true)
        verify(loadingObserver, times(1)).onChanged(false)
    }

    @Test
    fun `test action search artists bad search query`() = runBlockingTest {
        val searchText = "-"
        val artists = TestUtil.createArtists()
        given(artistRepository.getArtists(searchText)).willAnswer {
            throw AppException(AppError(
                ReasonOfError.InvalidParameters
            )
            )
        }

        viewModel.searchText.value = searchText
        viewModel.artists.observeForever(artistObserver)
        viewModel.error.observeForever(errorObserver)

        viewModel.actionSearchArtists()

        verify(errorObserver, times(1)).onChanged(InvalidSearchQueryError)
        verify(artistObserver, times(0)).onChanged(null)
        verify(loadingObserver, times(1)).onChanged(true)
        verify(loadingObserver, times(1)).onChanged(false)
    }

    @Test
    fun `test navigation to top albums screen`() {
        val artist = TestUtil.createArtist()
        viewModel.navigationAction.observeForever(navigationObserver)

        viewModel.actionArtistSelected(artist)

        verify(navigationObserver,
            times(1)
        ).onChanged(NavigationAction.To(
            SearchArtistFragmentDirections.actionSearchArtistFragmentToTopAlbumsFragment(artist.name))
        )
    }
}