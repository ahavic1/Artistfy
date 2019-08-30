package ba.ahavic.artistfy.ui.base.di

import ba.ahavic.artistfy.App
import ba.ahavic.artistfy.data.album.AlbumDataModule
import ba.ahavic.artistfy.data.artist.ArtistDataModule
import ba.ahavic.artistfy.data.base.di.CacheModule
import ba.ahavic.artistfy.data.base.di.NetworkModule
import ba.ahavic.artistfy.data.base.network.AuthInterceptorModule
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

    // Network
    NetworkModule::class,
    AuthInterceptorModule::class,

    // Data
    CacheModule::class,
    AlbumDataModule::class,
    ArtistDataModule::class
])
@Singleton
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}
