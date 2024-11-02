package com.example.databaseapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.util.query
import com.example.databaseapp.databinding.ActivityMainBinding
import com.example.databaseapp.databinding.ActivityProfileBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.getIntExtra("id", -1)
        val db = AppDatabase.getBase(context = this)
        val personDao = db.personDao()
        lifecycleScope.launch(Dispatchers.IO) {
            val person = personDao.getPersonById(id.toLong())
            withContext(Dispatchers.Main) {
                binding.nameTv.text = "Name: " + person.name
                binding.ageTv.text = "Age: " + person.age
                binding.jobTv.text = "Job: " + person.job
            }

        }
    }
}