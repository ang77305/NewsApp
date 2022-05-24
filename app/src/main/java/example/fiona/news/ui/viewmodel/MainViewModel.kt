package example.fiona.news.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import example.fiona.news.data.model.NewsData
import example.fiona.news.data.repository.NewsRepository
import example.fiona.news.di.component.DaggerNewsRepositoryComponent
import example.fiona.news.di.component.NewsRepositoryComponent
import example.fiona.news.utils.Resource
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class MainViewModel : ViewModel() {


    @Inject
    lateinit var newsRepository: NewsRepository

    private val newsData = MutableLiveData<Resource<NewsData>>()

    private val compositeDisposable = CompositeDisposable()


    init {
        val newsRepoComponet = DaggerNewsRepositoryComponent.create()
        newsRepoComponet.inject(this)

    }

    fun loadData() {


        newsData.postValue(Resource.loading(null))

        compositeDisposable.add(
            newsRepository.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ newData ->

                    newsData.postValue(Resource.success(newData))

                }, { throwable ->
                    var message = ""
                    message =
                        if (throwable is UnknownHostException || throwable is ConnectException)
                            "Check your internet connection and try again!"
                        else
                            "Something went wrong. try again!"

                    newsData.postValue(
                        Resource.error(
                            message,
                            null
                        )
                    )
                })
        )
    }



    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }


    fun getDatas(): MutableLiveData<Resource<NewsData>> {
        return newsData
    }



}