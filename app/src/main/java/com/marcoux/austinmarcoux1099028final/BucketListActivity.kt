package com.marcoux.austinmarcoux1099028final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.marcoux.austinmarcoux1099028final.databinding.ActivityBucketListBinding

class BucketListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBucketListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBucketListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.labelName.text = String.format("%s's Bucket list", FirebaseAuth.getInstance().currentUser.displayName)

        binding.buttonAddBucketList.setOnClickListener {
            val intent = Intent(this, AddBucketItemActivity::class.java)
            startActivity(intent);
        }
    }
}