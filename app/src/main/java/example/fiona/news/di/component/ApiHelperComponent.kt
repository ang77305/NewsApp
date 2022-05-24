package example.fiona.news.di.component

import dagger.Component
import example.fiona.news.data.repository.NewsRepository
import example.fiona.news.di.module.ApiHelperModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiHelperModule::class])
interface ApiHelperComponent {
    fun inject(mainRepository: NewsRepository)
}
