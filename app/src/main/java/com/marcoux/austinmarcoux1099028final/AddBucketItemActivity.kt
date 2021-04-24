package com.marcoux.austinmarcoux1099028final

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.marcoux.austinmarcoux1099028final.databinding.ActivityAddBucketItemBinding

class AddBucketItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBucketItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBucketItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.labelName.text =
            String.format("%s's Bucket list", FirebaseAuth.getInstance().currentUser.displayName)

        binding.buttonAddDestination.setOnClickListener {
            //toast to let user know all fields are mandatory
            if (binding.textDestinationName.text.trim().isEmpty() ||
                binding.textDestinationDescription.text.trim().isEmpty() ||
                binding.textDestinationRanking.text.trim().isEmpty() ||
                binding.textDestinationRanking.text.toString().toInt() < 0 ||
                binding.textDestinationRanking.text.toString().toInt() > 10
            ){
                Toast.makeText(this, "At least 1 field was not filled correctly", Toast.LENGTH_SHORT).show()
            } else {
                val db = FirebaseFirestore.getInstance().collection("destinations")

                val dest = Destination()
                dest.id = db.document().id
                dest.name = binding.textDestinationName.text.toString()
                dest.desc = binding.textDestinationDescription.text.toString()
                dest.rank = binding.textDestinationRanking.text.toString().toInt()
                dest.owner = FirebaseAuth.getInstance().currentUser.uid

                val dbpush = db.document(dest.id!!).set(dest)
                dbpush.addOnSuccessListener {
                    Toast.makeText(this, "Successfully added destination", Toast.LENGTH_SHORT).show()
                    finish()
                }

            }
        }

    }
}