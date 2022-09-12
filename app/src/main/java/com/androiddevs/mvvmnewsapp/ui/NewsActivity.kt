package com.androiddevs.mvvmnewsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.androiddevs.mvvmnewsapp.R
import com.androiddevs.mvvmnewsapp.Repository.NewsRepository
import com.androiddevs.mvvmnewsapp.databinding.ActivityNewsBinding
import com.androiddevs.mvvmnewsapp.db.ArticleDatabase

class NewsActivity : AppCompatActivity() {

    lateinit var viewModel: NewsViewModel
    private var navController: NavController? = null
    private var binding: ActivityNewsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_news)

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[NewsViewModel::class.java]

        val navHostFragment: NavHostFragment? =
            supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as? NavHostFragment
        navController = navHostFragment?.navController

        binding?.let {
            navController?.let { nav ->
                it.bottomNavigationView.setupWithNavController(nav)
//                it.bottomNavigationView.setupMenu()
            }
        }
    }
}
