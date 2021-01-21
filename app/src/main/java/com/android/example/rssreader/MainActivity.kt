package com.android.example.rssreader

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ========== PHASE 3 : from here ==========================================================
        // TODO: Add RecyclerView here for all "feeds by topic" options
        //  found on https://news.un.org/en/rss-feeds
        // ========== PHASE 3 : to here ============================================================


        // ========== PHASE 3 : Remove this section if needed from here ============================
        // TODO: Remove this section
        /* Send User to FeedActivity to view UN News Article by Topic */
        val topic = FeedTopic("Health", "health")
        // TODO: try switching above line with below and see what happens on FeedActivity Page
        // val topic = FeedTopic("Human Rights", "human-rights")

        val intent = Intent(this, FeedActivity::class.java)
        intent.putExtra("feed", topic)
        this.startActivity(intent)
        // ========== PHASE 3 : to here ============================================================
    }
}
