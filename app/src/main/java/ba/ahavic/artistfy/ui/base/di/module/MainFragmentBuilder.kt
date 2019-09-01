package ba.ahavic.artistfy.ui.base.di.module

import ba.ahavic.artistfy.ui.base.di.FragmentScope
import ba.ahavic.artistfy.ui.main.searchartist.SearchArtistFragment
import ba.ahavic.artistfy.ui.main.searchartist.SearchArtistModule
import ba.ahavic.artistfy.ui.main.albumdetails.AlbumDetailsFragment
import ba.ahavic.artistfy.ui.main.albumdetails.AlbumDetailsModule
import ba.ahavic.artistfy.ui.main.home.MyAlbumsFragment
import ba.ahavic.artistfy.ui.main.home.AlbumsModule
import ba.ahavic.artistfy.ui.main.topalbums.TopAlbumsFragment
import ba.ahavic.artistfy.ui.main.topalbums.TopAlbumsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuilder {

    @FragmentScope
    @ContributesAndroidInjector(modules = [AlbumsModule::class])
    abstract fun provideAlbumsFragment(): MyAlbumsFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [AlbumDetailsModule::class])
    abstract fun provideAlbumDetailsFragment(): AlbumDetailsFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [SearchArtistModule::class])
    abstract fun provideSearchArtistFragment(): SearchArtistFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [TopAlbumsModule::class])
    abstract fun provideTopAlbumsFragment(): TopAlbumsFragment
}
