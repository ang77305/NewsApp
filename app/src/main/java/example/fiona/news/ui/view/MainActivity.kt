package example.fiona.news.ui.view

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import example.fiona.news.R
import example.fiona.news.data.model.NewsData
import example.fiona.news.databinding.ActivityMainBinding
import example.fiona.news.ui.adapter.NewsAdapter
import example.fiona.news.ui.viewmodel.MainViewModel
import example.fiona.news.utils.Status
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity :
    AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var databinding: ActivityMainBinding
    private lateinit var adapter: NewsAdapter
    private var isLoading = false

    private lateinit var adapterList: NewsData

    lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        databinding = setContentView(this, R.layout.activity_main)
        setupViewModel()

        setupObservers()

        databinding.newsViewModel = mainViewModel
        databinding.lifecycleOwner = this

    }


    private fun updateList(newsData: NewsData) {

        adapter = NewsAdapter(this, newsData)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }


    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(this)[MainViewModel::class.java]
        mainViewModel.loadData()
    }


    private fun setupObservers() {
        mainViewModel.getDatas().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    isLoading = false
                    progressBar.visibility = View.GONE
                    it.data?.let { newsData -> updateList(newsData) }

                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    isLoading = false
                    progressBar.visibility = View.GONE
                    showSnack(content, it.message.toString())
                }
            }
        })


    }



    private fun showSnack(view: View, message: String) {
        val snackbar = Snackbar.make(
            view, message,
            Snackbar.LENGTH_INDEFINITE
        ).setAction("retry") {
            mainViewModel.loadData()
        }
        val snackbarView = snackbar.view
        val textView =
            snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.textSize = 14f
        snackbar.show()
    }




}