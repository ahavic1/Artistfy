package ba.ahavic.artistfy.ui.base.di.module

import ba.ahavic.artistfy.ui.base.di.FragmentScope
import ba.ahavic.artistfy.ui.main.artist.SearchArtistFragment
import ba.ahavic.artistfy.ui.main.artist.SearchArtistModule
import ba.ahavic.artistfy.ui.main.albums.AlbumDetailsFragment
import ba.ahavic.artistfy.ui.main.albums.AlbumDetailsModule
import ba.ahavic.artistfy.ui.main.albums.AlbumsFragment
import ba.ahavic.artistfy.ui.main.albums.AlbumsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuilder {

    @FragmentScope
    @ContributesAndroidInjector(modules = [AlbumsModule::class])
    abstract fun provideAlbumsFragment(): AlbumsFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [AlbumDetailsModule::class])
    abstract fun provideAlbumDetailsFragment(): AlbumDetailsFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [SearchArtistModule::class])
    abstract fun provideSearchArtistFragment(): SearchArtistFragment
}
