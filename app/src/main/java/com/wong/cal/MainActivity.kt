package com.wong.cal

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat

class MainActivity : AppCompatActivity(),MainActivityMVP.View{
    private lateinit var presenter:MainActivityMVP.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainActivityPresenter(this,MainActivityModel())
        presenter.start()
        fabComputePrice.setOnClickListener {
            val checkAmount = editTextTextCheckTotal.text.toString()
            presenter.computeTotalPrice(checkAmount)
        }
        chipGroup.setOnCheckedChangeListener { group, checkedId ->
            // subtract 1 since views start at index 1
            presenter.changeTipPercentage(group.checkedChipId-1)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun showTipOptions(percentages: Array<Int>) {
        for(index in percentages.indices){
            val chip = Chip(chipGroup.context)
            chip.text = "${percentages[index]}%"
            chip.isCheckable = true
            chip.isClickable = true
            chipGroup.addView(chip)
        }
    }

    override fun defaultTipOption(index: Int) {
        chipGroup.check(chipGroup.getChildAt(index).id)
    }

    @SuppressLint("SetTextI18n")
    override fun showInitialPrice(total: Double) {
        textViewTotal.text = "Total: ${NumberFormat.getCurrencyInstance().format(total)}"
    }

    @SuppressLint("SetTextI18n")
    override fun showFinalPriceWithTips(total: Double, tips: Double) {
        textViewTotal.text = "Total: ${NumberFormat.getCurrencyInstance().format(total)}"
        textViewTips.text = "Tips: ${NumberFormat.getCurrencyInstance().format(tips)}"
    }

    override fun showError() {
        Toast.makeText(this,getString(R.string.error_price),Toast.LENGTH_SHORT).show()
    }
}