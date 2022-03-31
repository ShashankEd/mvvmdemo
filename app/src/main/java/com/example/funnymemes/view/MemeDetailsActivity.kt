package com.example.funnymemes.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import coil.load
import com.example.funnymemes.databinding.ActivityMemeDetailsBinding
import com.example.funnymemes.viewmodel.MemeDetailViewModel

class MemeDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMemeDetailsBinding
    //View model
    private val viewModel: MemeDetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("Detail Activity", "MemeID: "+ intent.extras?.get("meme_id"))

        viewModel.getMeme(intent.extras?.get("meme_id").toString())

        //bind the views using the view model live data
        if(viewModel.memeDetailState.value !== null) {
            binding.memeDetailName.text = viewModel.memeDetailState.value!!.memeName
            binding.memeDetailsImage.load(viewModel.memeDetailState.value!!.memeURL)
        }
    }
}