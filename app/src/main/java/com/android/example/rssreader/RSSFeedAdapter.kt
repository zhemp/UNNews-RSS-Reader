package com.android.example.rssreader

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.example.rssreader.model.Item

class RSSFeedAdapter(private val rss_list: MutableList<FeedTopic>) : RecyclerView.Adapter<RSSFeedAdapter.FeedViewHolder>(){
    class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView : LinearLayout = itemView.findViewById(R.id.card_card)
        val topic_title : TextView = itemView.findViewById(R.id.feed_topic)
        //val arrow: ImageView = itemView.findViewById(R.id.feed_arrow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.rss_feed_item, parent, false)
        return FeedViewHolder(view)
    }
    override fun getItemCount(): Int {
        return rss_list.size
    }
    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.topic_title.text = rss_list[position].topic
        //holder.arrow. = TopicList[position].description

        holder.cardView.setOnClickListener {
            //Toast.makeText(it.context, "Hi there! This is a Toast.", Toast.LENGTH_SHORT).show()
            val one2two = Intent(it.context,FeedActivity::class.java)
            one2two.putExtra("feed", rss_list[position])
            it.context.startActivity(one2two)
        }
    }
}
