package ba.ahavic.artistfy.ui.base.di.module

import ba.ahavic.artistfy.ui.base.di.FragmentScope
import ba.ahavic.artistfy.ui.main.albums.AlbumsFragment
import ba.ahavic.artistfy.ui.main.albums.MainFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuilder {

    @FragmentScope
    @ContributesAndroidInjector(modules = [MainFragmentModule::class])
    abstract fun provideMainFragment(): AlbumsFragment
}
