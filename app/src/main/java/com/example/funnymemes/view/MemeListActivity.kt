package com.example.funnymemes.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.funnymemes.R
import com.example.funnymemes.databinding.ActivityMainBinding
import com.example.funnymemes.viewmodel.MemeListViewModel
import androidx.lifecycle.*
import com.example.funnymemes.model.response.MemeResponse
import kotlinx.coroutines.*
import kotlin.math.log

class MemeListActivity : AppCompatActivity() {

    //create a main scope for calling suspend methods
    private val mainScope = MainScope()
    //define the binding
    private lateinit var binding: ActivityMainBinding
    //View model
    private val viewModel: MemeListViewModel by viewModels()
    //define the adapter
    private val adapter = MemesRecyclerViewAdapter(arrayListOf()) {
        //handle the onclick event, here we'll get the MemeResponse
        val intent = Intent(this, MemeDetailsActivity::class.java)
        intent.putExtra("meme_id",it.memeId)
        startActivity(intent)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        initialiseUI()
    }


    private fun initialiseUI() {
        binding.listItems.adapter = adapter
        binding.listItems.layoutManager = LinearLayoutManager(this)

        mainScope.launch {
            withContext(Dispatchers.IO) {
                val memes = viewModel.getMemes()
                Log.d("API Call",  memes.toString())
            }
            //update UI here, if the mutable state is not empty then display the list
            if(!viewModel.memeState.value.isNullOrEmpty())
                adapter.updateList(viewModel.memeState.value as List<MemeResponse>);
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        //we need to cancel the job in order to prevent from memory leaks if any
        mainScope.cancel()
    }
}