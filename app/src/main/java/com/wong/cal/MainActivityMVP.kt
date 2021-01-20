package com.wong.cal

interface MainActivityMVP {
    interface View{
        fun showTipOptions(percentages:Array<Int>)
        fun defaultTipOption(index:Int)
        fun showInitialPrice(total:Double)
        fun showFinalPriceWithTips(total:Double,tips:Double)
        fun showError()
    }
    interface Presenter{
        fun start()
        fun changeTipPercentage(position:Int)
        fun computeTotalPrice(price:String)
    }
    interface Model{
        val tipOptions: Array<Int>
        val defaultTipIndex: Int
        var totalAmount:Double
        var checkAmount: Double
        var tipAmount: Double
        fun setTipOption(position: Int)
        fun computeTotalPrice(price: String?): Double

    }
}