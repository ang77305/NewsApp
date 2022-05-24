package example.fiona.news.data.repository

import io.reactivex.Single
import example.fiona.news.data.api.ApiHelper
import example.fiona.news.data.model.NewsData
import example.fiona.news.di.component.ApiHelperComponent
import example.fiona.news.di.component.DaggerApiHelperComponent

import javax.inject.Inject


class NewsRepository  {

    @Inject
    lateinit var apiHelper: ApiHelper

    init {
        val apiHelperComponent: ApiHelperComponent = DaggerApiHelperComponent.create();
        apiHelperComponent.inject(this)
    }

     fun getData(): Single<NewsData> {

        return apiHelper.getData()

    }
}