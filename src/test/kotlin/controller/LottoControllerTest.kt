package controller

import model.Lotto
import model.LottoResult
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import view.LottoView

class LottoControllerTest {

    @Test
    fun generateLottos() {
        val view = LottoView()
        val controller = LottoController(view)
        val lottos = controller.generateLottos(5)
        assertEquals(5, lottos.size)
    }

    @Test
    fun generateRandomLottoNumbers() {
        val view = LottoView()
        val controller = LottoController(view)
        val lottoNumbers = controller.generateRandomLottoNumbers()
        assertEquals(6, lottoNumbers.size)
    }

    @Test
    fun checkLottoResults() {
        val view = LottoView()
        val controller = LottoController(view)
        val lottos = listOf(
            Lotto(listOf(1, 2, 3, 4, 5, 6)),
            Lotto(listOf(1, 2, 3, 4, 5, 7)),
            Lotto(listOf(1, 2, 3, 4, 8, 9)),
            Lotto(listOf(1, 2, 3, 7, 8, 9)),
            Lotto(listOf(1, 2, 7, 8, 9, 10))
        )
        val winningNumbers = listOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = 20
        val lottoResults = controller.checkLottoResults(lottos, winningNumbers, bonusNumber)
        assertEquals(5, lottoResults.size)

    }

    @Test
    fun calculatePrize() {
        val view = LottoView()
        val controller = LottoController(view)
        assertEquals(0, controller.calculatePrize(0, false))
    }

    @Test
    fun countWinnings() {
        val view = LottoView()
        val controller = LottoController(view)
        val lottoResults = listOf(
            LottoResult(3, 5000),
            LottoResult(4, 50000),
            LottoResult(5, 1500000),
            LottoResult(6, 2000000000)
        )
        val stats = controller.countWinnings(lottoResults)
        assertEquals(1, stats[3])
        assertEquals(1, stats[4])
        assertEquals(1, stats[5])
        assertEquals(1, stats[6])
    }

    @Test
    fun `1등`() {
        val prize = 2000000000
        val purchaseAmount = 10000
        val view = LottoView()
        val controller = LottoController(view)
        val lottoResults = listOf(
            LottoResult(6, prize)
        )
        val profitRate = controller.calculateProfitRate(lottoResults, purchaseAmount)
        assertEquals((prize/purchaseAmount).toDouble(), profitRate)
    }
    @Test
    fun `2등`() {
        val prize = 1500000
        val purchaseAmount = 10000
        val view = LottoView()
        val controller = LottoController(view)
        val lottoResults = listOf(
            LottoResult(5, prize)
        )
        val profitRate = controller.calculateProfitRate(lottoResults, purchaseAmount)
        assertEquals((prize/purchaseAmount).toDouble(), profitRate)
    }
    @Test
    fun `3등`() {
        val prize = 50000
        val purchaseAmount = 10000
        val view = LottoView()
        val controller = LottoController(view)
        val lottoResults = listOf(
            LottoResult(4, prize)
        )
        val profitRate = controller.calculateProfitRate(lottoResults, purchaseAmount)
        assertEquals((prize/purchaseAmount).toDouble(), profitRate)
    }
    @Test
    fun `4등`() {
        val prize = 5000
        val purchaseAmount = 5000
        val view = LottoView()
        val controller = LottoController(view)
        val lottoResults = listOf(
            LottoResult(3, prize)
        )
        val profitRate = controller.calculateProfitRate(lottoResults, purchaseAmount)
        assertEquals((prize/purchaseAmount).toDouble(), profitRate)
    }

    @Test
    fun `로또 번호 1등`() {
        val view = LottoView()
        val controller = LottoController(view)
        val lottos = Lotto(listOf(1, 2, 3, 4, 5, 6))

        val winningNumbers = listOf(1, 2, 3, 4, 5, 6)

        val bonusNumber = 7
        val lottoResult = controller.checkLottoResults(listOf(lottos), winningNumbers, bonusNumber)
        assertEquals(2000000000, lottoResult[0].prize)
    }

    @Test
    fun `로또 꽝`() {
        val view = LottoView()
        val controller = LottoController(view)
        val lottos = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val winningNumbers = listOf(7, 8, 9, 10, 11, 12)
        val bonusNumber = 13
        val lottoResult = controller.checkLottoResults(listOf(lottos), winningNumbers, bonusNumber)
        assertEquals(0, lottoResult[0].prize)
    }

    @Test
    fun getView() {
        val view = LottoView()
        val controller = LottoController(view)
        assertEquals(view, controller.view)
    }
}
