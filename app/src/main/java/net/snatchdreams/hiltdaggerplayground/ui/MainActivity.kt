package net.snatchdreams.hiltdaggerplayground.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.provider.ContactsContract
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import net.snatchdreams.hiltdaggerplayground.R
import net.snatchdreams.hiltdaggerplayground.model.Blog
import net.snatchdreams.hiltdaggerplayground.util.DataState
import java.lang.StringBuilder

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG: String = "AppDebug"

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetBlogEvents)
    }

    private fun subscribeObservers()
    {
        viewModel.dataState.observe(this, Observer {
            dataState ->
            when(dataState)
            {
                is DataState.Success<List<Blog>> -> {
                    displayProgressBar(false)
                    appendBlogTitles(dataState.data)
                }

                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message.toString())
                }

                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun displayError(message: String)
    {
        if(message != null){
            dataText.text = message
        }
        else
        {
            dataText.text = "Unknown Error"
        }
    }

    private fun displayProgressBar(isDisplayed: Boolean)
    {
        progressBar.visibility = if(isDisplayed) View.VISIBLE else View.GONE
    }

    private fun appendBlogTitles(blogs: List<Blog>)
    {
        val sb: StringBuilder = StringBuilder()
        for(blog in blogs)
        {
            sb.append(blog.title + "\n")
        }
        dataText.text = sb.toString()
    }
}