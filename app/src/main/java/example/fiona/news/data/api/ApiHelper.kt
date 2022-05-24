package example.fiona.news.data.api

import example.fiona.news.di.component.ApiComponent
import example.fiona.news.di.component.DaggerApiComponent
import javax.inject.Inject

class ApiHelper {

    @Inject
    lateinit var apiService: ApiService

    init {
        val apiComponent: ApiComponent = DaggerApiComponent.create()
        apiComponent.inject(this)
    }

    fun getData() = apiService.getData()

}