package ba.ahavic.artistfy.ui.base.di.module

import ba.ahavic.artistfy.ui.base.di.ActivityScope
import ba.ahavic.artistfy.ui.main.MainActivity
import ba.ahavic.artistfy.ui.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainModule::class, MainFragmentBuilder::class])
    abstract fun provideMainActivity(): MainActivity
}
