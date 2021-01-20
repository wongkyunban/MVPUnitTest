package com.wong.cal

class MainActivityModel:MainActivityMVP.Model {

    private var selectedTipOption = tipOptions[defaultTipIndex]
    override var totalAmount = 0.00


    override val tipOptions: Array<Int>
        get() = arrayOf(5,10,15,20,22)
    override val defaultTipIndex: Int
        get() = 2
    override var checkAmount: Double = 0.00
    override var tipAmount: Double = 0.0

    override fun setTipOption(position: Int) {
        selectedTipOption = if(position < 0 || position >= tipOptions.size){
            tipOptions[defaultTipIndex]
        }else{
            tipOptions[position]
        }
    }

    override fun computeTotalPrice(price: String?): Double {
        if(price.isNullOrEmpty()) return 0.00
        val doublePrice = price.toDouble()
        checkAmount = if(doublePrice < 0) 0.00 else doublePrice
        tipAmount = checkAmount * (selectedTipOption.toDouble() / 100)
        totalAmount  = checkAmount + tipAmount
        return totalAmount


    }

}