package com.wong.cal

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class MainActivityModelTest{
    private val model = MainActivityModel()
    @Test
    fun nullPriceShouldReturnZeroTotal(){
        val total = model.computeTotalPrice(null)
        assertThat(total).isZero
    }

    // 3
    @Test
    fun negativePriceShouldReturnZeroCheckAmount(){
        model.computeTotalPrice("-1")
        assertThat(model.totalAmount).isEqualTo(0.00)
        assertThat(model.checkAmount).isEqualTo(0.00)
        assertThat(model.tipAmount).isEqualTo(0.00)

    }
    @Test
    fun invalidTipOptionShouldReturnTotalPriceWithTwentyPercentTip() {
        model.setTipOption(6)
        val price = model.computeTotalPrice("100")
        assertThat(price).isEqualTo(115.00)
        assertThat(model.totalAmount).isEqualTo(115.00)
        assertThat(model.checkAmount).isEqualTo(100.00)
        assertThat(model.tipAmount).isEqualTo(15.00)
    }
    @Test
    fun fivePercentFor100Re105(){
        model.setTipOption(0)
        val price = model.computeTotalPrice("100")
        assertThat(price).isEqualTo(105.00)
        assertThat(model.totalAmount).isEqualTo(105.00)
        assertThat(model.checkAmount).isEqualTo(100.00)
        assertThat(model.tipAmount).isEqualTo(5.00)
    }
    @Test
    fun tenPercentFor100Re105(){
        model.setTipOption(1)
        val price = model.computeTotalPrice("100")
        assertThat(price).isEqualTo(110.00)
        assertThat(model.totalAmount).isEqualTo(110.00)
        assertThat(model.checkAmount).isEqualTo(100.00)
        assertThat(model.tipAmount).isEqualTo(10.00)
    }
    @Test
    fun fifteenPercentFor100Re105(){
        model.setTipOption(2)
        val price = model.computeTotalPrice("100")
        assertThat(price).isEqualTo(115.00)
        assertThat(model.totalAmount).isEqualTo(115.00)
        assertThat(model.checkAmount).isEqualTo(100.00)
        assertThat(model.tipAmount).isEqualTo(15.00)
    }
    @Test
    fun twentyPercentFor100Re105(){
        model.setTipOption(3)
        val price = model.computeTotalPrice("100")
        assertThat(price).isEqualTo(120.00)
        assertThat(model.totalAmount).isEqualTo(120.00)
        assertThat(model.checkAmount).isEqualTo(100.00)
        assertThat(model.tipAmount).isEqualTo(20.00)
    }
    @Test
    fun twentyTwoPercentFor100Re105(){
        model.setTipOption(4)
        val price = model.computeTotalPrice("100")
        assertThat(price).isEqualTo(122.00)
        assertThat(model.totalAmount).isEqualTo(122.00)
        assertThat(model.checkAmount).isEqualTo(100.00)
        assertThat(model.tipAmount).isEqualTo(22.00)
    }

}