package example.fiona.news.ui.adapter

import android.app.Activity
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.centerCrop
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import example.fiona.news.R
import example.fiona.news.data.model.NewsData
import java.text.SimpleDateFormat
import java.util.*


class NewsAdapter(
    val context: Activity,
    var newsData: NewsData
) :
    RecyclerView.Adapter<NewsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsViewHolder {


        return NewsViewHolder(context.layoutInflater.inflate(R.layout.item_news, parent, false))

    }


    override fun getItemCount(): Int {
        return newsData.getVector.items.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        when (newsData.getVector.items[position].type) {
            "divider" -> {
                holder.titleTextView.visibility = VISIBLE
                holder.llNews.visibility = GONE
                holder.titleTextView.text = newsData.getVector.items[position].title
            }
            "news" -> {
                holder.llNews.visibility = VISIBLE
                holder.titleTextView.visibility = GONE
                holder.newsTextView.text = newsData.getVector.items[position].appearance.mainTitle
                if (newsData.getVector.items[position].appearance.subscript != null)
                    holder.urlTextView.text =
                        newsData.getVector.items[position].appearance.subscript
                holder.newsSubTextView.text = newsData.getVector.items[position].appearance.subTitle
                Glide.with(context).load(newsData.getVector.items[position].appearance.thumbnail)
                    .apply(
                        RequestOptions()
                            .placeholder(R.mipmap.ic_launcher)
                            .error(R.mipmap.ic_launcher)
                    ).transform(CenterCrop(), RoundedCorners(10))
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(holder.imgThumbNail)
                if (newsData.getVector.items[position].extra != null) {
                    val date =
                        Date(newsData.getVector.items[position].extra.created.toLong() * 1000)
                    val format = SimpleDateFormat("MMM d, yyyy")
                    val format2 = SimpleDateFormat("hh:mm aa")
                    holder.createdTextView.text =
                        format.format(date) + " at " + format2.format(date)
                }

            }
            else -> {
                holder.llNews.visibility = GONE
                holder.titleTextView.visibility = GONE

            }
        }


    }

}

class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val titleTextView: TextView = view.findViewById(R.id.tv_title)
    val newsTextView: TextView = view.findViewById(R.id.tv_news)
    val newsSubTextView: TextView = view.findViewById(R.id.tv_news_sub)
    val urlTextView: TextView = view.findViewById(R.id.tv_url)
    val createdTextView: TextView = view.findViewById(R.id.tv_created)
    val imgThumbNail: ImageView = view.findViewById(R.id.img_thumbnail)
    val llNews: LinearLayout = view.findViewById(R.id.ll_news)

}



