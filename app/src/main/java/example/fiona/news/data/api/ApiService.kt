package example.fiona.news.data.api

import io.reactivex.Single
import example.fiona.news.BuildConfig
import example.fiona.news.data.model.NewsData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("interview/interview_get_vector.json")
    fun getData(): Single<NewsData>

}