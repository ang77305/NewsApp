package example.fiona.news.di.module

import dagger.Module
import dagger.Provides
import example.fiona.news.data.api.ApiHelper
import javax.inject.Singleton

@Module
class ApiHelperModule {

    @Singleton
    @Provides
    fun providesApiHelper(): ApiHelper {
        return ApiHelper()
    }
}