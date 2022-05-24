package example.fiona.news.di.component

import dagger.Component
import example.fiona.news.di.module.NewsRepositoryModule
import example.fiona.news.ui.viewmodel.MainViewModel
import javax.inject.Singleton


@Singleton
@Component(modules = [NewsRepositoryModule::class])
interface NewsRepositoryComponent {
    fun inject(newsViewModel: MainViewModel)
}
