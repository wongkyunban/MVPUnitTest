package com.wong.cal

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test

internal class MainActivityPresenterTest {

    @RelaxedMockK
    private lateinit var view:MainActivityMVP.View

    @MockK(relaxUnitFun = true)
    private lateinit var model:MainActivityMVP.Model

    private lateinit var presenter: MainActivityPresenter


    /**
     * @Before it will run before every test is executed.
     */
    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        every { model.tipOptions } returns arrayOf(5,10,15,20,22)
        every { model.totalAmount } returns 115.00
        every { model.tipAmount } returns 15.00
        every { model.defaultTipIndex } returns 2
        every { model.checkAmount } returns 100.00
        presenter = MainActivityPresenter(view,model)
    }
    @Test
    fun startShouldCallExpectedMethodsWithExpectedValues(){
        presenter.start()
        verify {
            view.showTipOptions(arrayOf(5,10,15,20,22))
            view.defaultTipOption(2)
            view.showInitialPrice(100.00)
        }
    }
    @Test
    fun changeTipPercentWithNegativeValueShouldCallSelectDefaultTipOption(){

        presenter.changeTipPercentage(-1)

        verify {
            view.defaultTipOption(2)
        }
    }

    @Test
    fun computeTotalPriceShouldCallDisplayFinalPriceWithTip(){
        every { model.computeTotalPrice("100") } returns 115.00

        presenter.computeTotalPrice("100")

        verify{
            view.showFinalPriceWithTips(115.00, 15.00)
        }
    }

    @Test
    fun computeTotalPriceWithZeroShouldCallDisplayError(){
        every { model.computeTotalPrice("0") } returns 0.00

        presenter.computeTotalPrice("0")

        verify{
            view.showError()
        }
    }
}