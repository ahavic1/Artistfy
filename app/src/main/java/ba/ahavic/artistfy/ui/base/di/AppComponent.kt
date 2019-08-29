package ba.ahavic.artistfy.ui.base.di

import ba.ahavic.artistfy.App
import ba.ahavic.artistfy.data.album.AlbumDataModule
import ba.ahavic.artistfy.data.base.di.CacheModule
import ba.ahavic.artistfy.data.base.di.NetworkModule
import ba.ahavic.artistfy.ui.base.di.module.ActivityBuilder
import ba.ahavic.artistfy.ui.base.di.module.AppConfigModule
import ba.ahavic.artistfy.ui.base.di.module.AppModule
import ba.ahavic.artistfy.ui.base.viewmodel.ViewModelFactoryModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [
    // Android
    AndroidSupportInjectionModule::class,

    // Application
    AppModule::class,
    ActivityBuilder::class,
    ViewModelFactoryModule::class,
    AppConfigModule::class,

    // Data
    CacheModule::class,
    NetworkModule::class,
    AlbumDataModule::class
])
@Singleton
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}
