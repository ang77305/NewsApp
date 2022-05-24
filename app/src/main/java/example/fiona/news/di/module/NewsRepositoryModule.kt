package example.fiona.news.di.module

import dagger.Module
import dagger.Provides
import example.fiona.news.data.repository.NewsRepository
import javax.inject.Singleton

@Module
class NewsRepositoryModule {

    @Singleton
    @Provides
    fun providesNewsRepository(): NewsRepository {
        return NewsRepository()
    }
}