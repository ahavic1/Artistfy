package ba.ahavic.artistfy.ui.base.di.module

import ba.ahavic.artistfy.ui.base.di.FragmentScope
import ba.ahavic.artistfy.ui.main.MainFragment
import ba.ahavic.artistfy.ui.main.MainFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuilder {

    @FragmentScope
    @ContributesAndroidInjector(modules = [MainFragmentModule::class])
    abstract fun provideMainFragment(): MainFragment
}
