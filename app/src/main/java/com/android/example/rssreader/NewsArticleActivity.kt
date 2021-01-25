package com.android.example.rssreader

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.example.rssreader.model.Item


class NewsArticleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_article)

        // TODO PHASE 2
        val article : Item = intent.getParcelableExtra<Item>("article")!!
        val time_text: TextView = findViewById(R.id.article_time)
        time_text.text = article.pubDate
        val title_text: TextView = findViewById(R.id.article_title)
        title_text.text = article.title
        val description_text: TextView = findViewById(R.id.article_description)
        description_text.text = article.description

        val roller: Button = findViewById(R.id.article_button)
        roller.setOnClickListener { openWebPage(article.link) }
    }
    fun openWebPage(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse(url))
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this,"No Web Browser in your phone", Toast.LENGTH_SHORT).show()
        }
        //val webpage: Uri = Uri.parse(url)
        //val intent = Intent(Intent.ACTION_VIEW, webpage)
        //if (intent.resolveActivity(packageManager) != null) {
            //startActivity(intent)
        }
    }