package com.marcoux.austinmarcoux1099028final

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcoux.austinmarcoux1099028final.databinding.ActivityViewBucketListBinding

class ViewBucketListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewBucketListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewBucketListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener{
            finish()
        }

        val model: DestinationViewModel by viewModels()
        model.getDestinations().observe(this) {destinations ->
            val recyclerAdapter = RecyclerViewAdapter(this, destinations)
            binding.recyclerView.adapter = recyclerAdapter
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
        }
    }
}