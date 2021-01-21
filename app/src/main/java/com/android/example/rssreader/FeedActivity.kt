package com.android.example.rssreader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.example.rssreader.model.Item
import com.android.example.rssreader.model.RSSWrapper
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory


class FeedActivity : AppCompatActivity() {

    /* Used for fetching rss feed */
    private val baseUrl = "https://news.un.org/feed/subscribe/en/news/"

    /* Holds all News Articles for Selected Topic */
    private val rssFeedList = mutableListOf<Item>()     // Hint: You'll need this for your adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        val feedTopic : FeedTopic = intent.getParcelableExtra<FeedTopic>("feed")!!
        val topicUrl = feedTopic.link


        // ========== PHASE 1 : from here ==========================================================
        // TODO: Add RecyclerView here based on Item objects
        // ========== PHASE 1 : to here ============================================================
        val topic_rv = findViewById<RecyclerView>(R.id.topic_recyclerview)
        topic_rv.layoutManager = LinearLayoutManager(this)
        topic_rv.adapter = TopicAdapter(rssFeedList)




        /* Fetch UN Article Feeds */
        fetchRssFeed(topicUrl, ::onRssResponse, ::onRssFailure)
    }


    /**
     * This will run if fetching the UN News Articles was successful. Add logic here that will update
     * the list corresponding to your RecyclerView and make sure to notify the RescyclerView adapter
     * that the list has changed
     */
    private fun onRssResponse(call: Call<RSSWrapper?>?, response: Response<RSSWrapper?>) {
        val feed: RSSWrapper? = response.body()
        if (feed != null) {
            for (item in feed.channel?.items!!) {
                rssFeedList.add(item)
            }

            // TODO PHASE 1 : Update RecyclerView list and notify data set changed
        }
    }

    /**
     * This will run if there was an error when fetching the UN News Article. Add logic here that
     * would help signify to the user something went wrong and in logcat for developers to be aware of.
     */
    private fun onRssFailure(call: Call<RSSWrapper?>?, t: Throwable?) {
        // TODO PHASE 1: Log error here since request failed
        // TODO PHASE 1: Make toast telling user articles could not be fetched
    }

    /**
     * You wont need to update this function. We will cover more in depth what is happening in this
     * function later on in the course, so do not worry if it does not make complete sense.
     *
     * Here we are using a REST client library to fetch all the UN News Articles under the category
     * @topicUrl. We have two callback functions @onRssResponse and @onRssFailure which are defined
     * above for you to add on to.
     *
     * Note: @topicUrl is the section in the UN RSS Feed url between /topic/ and /feed/
     *
     * ex1:
     *      topicUrl = "health"
     *      UN RSS Feed url = https://news.un.org/feed/subscribe/en/news/topic/health/feed/rss.xml
     *
     * ex2:
     *      topicUrl = "human-rights"
     *      UN RSS Feed url = https://news.un.org/feed/subscribe/en/news/topic/human-rights/feed/rss.xml
     */
    private fun fetchRssFeed(topicUrl: String, onRssResponse: (call: Call<RSSWrapper?>?, response: Response<RSSWrapper?>) -> Unit, onRssFailure: (call: Call<RSSWrapper?>?, t: Throwable?) -> Unit) {
        /* Define how API requests are made to the rss feed  */
        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(OkHttpClient())
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()

        /* Connect retrofit to the API feed Interface */
        val unNews = retrofit.create(FeedApi::class.java)

        /* Make API call to get RSS Feed */
        val call: Call<RSSWrapper> = unNews.getFeed(topicUrl)

        /* Process returned data */
        call.enqueue(object : Callback<RSSWrapper?> {
            override fun onResponse(call: Call<RSSWrapper?>?, response: Response<RSSWrapper?>) {
                // val statusCode: Int = response.code()
                onRssResponse(call, response)
            }

            override fun onFailure(call: Call<RSSWrapper?>?, t: Throwable?) {
                onRssFailure(call, t)
            }
        })
    }
}