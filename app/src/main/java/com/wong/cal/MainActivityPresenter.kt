package com.wong.cal

class MainActivityPresenter(private val view:MainActivityMVP.View,private val model:MainActivityMVP.Model):MainActivityMVP.Presenter {
    override fun start() {
        view.showTipOptions(model.tipOptions)
        view.defaultTipOption(model.defaultTipIndex)
        view.showInitialPrice(model.checkAmount)
    }

    override fun changeTipPercentage(position: Int) {
        model.setTipOption(position)
        if(position < 0 || position >= model.tipOptions.size){
            view.defaultTipOption(model.defaultTipIndex)
        }
    }

    override fun computeTotalPrice(price: String) {
        when(val finalPrice = model.computeTotalPrice(price)){
            0.00 ->{
                view.showError()
                view.defaultTipOption(model.defaultTipIndex)
                view.showInitialPrice(model.checkAmount)
            }
            else->{
                view.showFinalPriceWithTips(finalPrice,model.tipAmount)
            }
        }
    }
}