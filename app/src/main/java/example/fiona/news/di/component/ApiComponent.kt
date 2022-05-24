package example.fiona.news.di.component

import dagger.Component
import example.fiona.news.data.api.ApiHelper
import example.fiona.news.di.module.ApiModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(apiHelper: ApiHelper)
}
