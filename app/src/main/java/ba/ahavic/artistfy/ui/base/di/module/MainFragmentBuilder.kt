package ba.ahavic.artistfy.ui.base.di.module

import ba.ahavic.artistfy.ui.base.di.FragmentScope
import ba.ahavic.artistfy.ui.main.albums.AlbumDetailsFragment
import ba.ahavic.artistfy.ui.main.albums.AlbumDetailsFragmentModule
import ba.ahavic.artistfy.ui.main.albums.AlbumsFragment
import ba.ahavic.artistfy.ui.main.albums.AlbumsFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuilder {

    @FragmentScope
    @ContributesAndroidInjector(modules = [AlbumsFragmentModule::class])
    abstract fun provideAlbumsFragment(): AlbumsFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [AlbumDetailsFragmentModule::class])
    abstract fun provideAlbumDetailsFragment(): AlbumDetailsFragment
}
