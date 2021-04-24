package com.marcoux.austinmarcoux1099028final

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class DestinationViewModel : ViewModel() {
    private val destinations = MutableLiveData<List<Destination>>()

    init{
        loadDestinations()
    }

    fun getDestinations() : LiveData<List<Destination>>{
        return destinations
    }

    private fun loadDestinations(){
        val db = FirebaseFirestore.getInstance().collection("destinations")
            .orderBy("rank", Query.Direction.ASCENDING)

        db.addSnapshotListener {documents, exception ->
            Log.i("DB_RESPONSE", "Returned ${documents?.size()} items")
            if(exception != null){
                Log.w("DB_ERROR", exception)
                return@addSnapshotListener
            }

            documents?.let {
                val destTemp = ArrayList<Destination>()
                for(document in documents){
                    val dest = document.toObject(Destination::class.java)
                    if(dest.owner == FirebaseAuth.getInstance().currentUser.uid){
                        destTemp.add(dest)
                    }
                }
                destinations.value = destTemp
            }

        }
    }

}