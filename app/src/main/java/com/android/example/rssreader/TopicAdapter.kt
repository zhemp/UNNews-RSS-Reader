package com.android.example.rssreader

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.example.rssreader.model.Item

class TopicAdapter(private val TopicList: MutableList<Item>) : RecyclerView.Adapter<TopicAdapter.TopicViewHolder>() {
    class TopicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val entryView : LinearLayout = itemView.findViewById(R.id.topic_entry)
        val timeView : TextView = itemView.findViewById(R.id.topic_time_view)
        val topicView : TextView = itemView.findViewById(R.id.topic_title_view)
        val descriptionView : TextView = itemView.findViewById(R.id.topic_description_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.feed_topic, parent, false)

        return TopicViewHolder(view)
    }

    override fun getItemCount(): Int {
        return TopicList.size
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        holder.timeView.text = TopicList[position].pubDate
        holder.topicView.text = TopicList[position].title
        holder.descriptionView.text = TopicList[position].description

        holder.entryView.setOnClickListener {
            val intent = Intent(this, NewsArticleActivity::class.java)
            startActivity(intent)
        }
    }
}