package com.example.androidhw01

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidhw01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var seatsCounter = 0
    private val maxSeats = 49

    @SuppressLint("SetTextI18n")
    fun getSeatsStatus (counter: Int, binding: ActivityMainBinding){

//        if (counter == 0) {
//            binding.textCenter.text = SeatsStatus.ALL_SEATS_FREE.value
//            SeatsStatus.ALL_SEATS_FREE.setAllSeatsFreeStyle(binding)
//        } else {
//            binding.textCenter.text = SeatsStatus.SEATS_LEFT.value + (maxSeats - counter).toString()
//        }
//        if (counter in 1..maxSeats) {
//            SeatsStatus.SEATS_LEFT.setSeatsLeftStyle(binding)
//        }
//        if (counter > maxSeats) {
//            binding.textCenter.text = SeatsStatus.BUST.value
//            SeatsStatus.BUST.setBustStyle(binding)
//        }

        when {
            counter != 0 -> {
                binding.textCenter.text = SeatsStatus.SEATS_LEFT.value + (maxSeats - counter).toString()
                when {
                    counter in 1..maxSeats -> SeatsStatus.SEATS_LEFT.setSeatsLeftStyle(binding)
                    counter > maxSeats -> {
                        binding.textCenter.text = SeatsStatus.BUST.value
                        SeatsStatus.BUST.setBustStyle(binding)
                    }
                }
            }
            else -> {
                binding.textCenter.text = SeatsStatus.ALL_SEATS_FREE.value
                SeatsStatus.ALL_SEATS_FREE.setAllSeatsFreeStyle(binding)
            }
        }
        binding.textCounter.text = (counter).toString()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getSeatsStatus(seatsCounter,binding)
        val buttonPlus = binding.buttonPlus
        val buttonMinus = binding.buttonMinus
        val buttonClear = binding.buttonClear

        buttonPlus.setOnClickListener {
            seatsCounter++
            getSeatsStatus(seatsCounter,binding)

        }

        buttonMinus.setOnClickListener {
            seatsCounter--
            getSeatsStatus(seatsCounter,binding)
        }

        buttonClear.setOnClickListener {
            seatsCounter = 0
            getSeatsStatus(seatsCounter,binding)
        }
    }
}
