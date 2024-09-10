package controller

import model.Lotto
import model.LottoResult
import view.LottoView

class LottoController(val view: LottoView) {

    fun start() {
        // 1. 구입 금액 입력받기
        val purchaseAmount = view.inputPurchaseAmount()
        val numberOfLottos = purchaseAmount / Lotto.LOTTO_PRICE
        println("$numberOfLottos 개를 구매했습니다.")

        // 1-1. 수동으로 구매할 로또 수 입력받기
        val manualLottoCount = view.inputManualCount()

        // 1-2. 수동으로 구매할 로또 번호 입력
        val manualLottos = (1..manualLottoCount).map {
            val numbers = view.inputManualNumbers()
            Lotto(numbers)
        }

        // 2. 로또 번호 생성
        val purchasedLottos = generateLottos(numberOfLottos - manualLottoCount)
        val allLottos = manualLottos + purchasedLottos

        // 2-1. 수동, 자동 로또 개수 출력
        view.printLottosType(manualLottoCount, purchasedLottos.size)
        view.printLottos(allLottos.map { it.numbers })

        // 3. 당첨 번호 입력받기
        val winningNumbers = view.inputWinningNumbers()
        val bonusNumber = view.inputBonusNumber()

        // 4. 당첨 결과 확인
        val lottoResults = checkLottoResults(allLottos, winningNumbers, bonusNumber)

        // 5. 통계 및 수익률 계산 후 출력
        val profitRate = calculateProfitRate(lottoResults, purchaseAmount)
        view.printStatistics(countWinnings(lottoResults), profitRate)
    }

    // 로또 번호 생성
    fun generateLottos(count: Int): List<Lotto> {
        return (1..count).map { Lotto(generateRandomLottoNumbers()) }
    }

    fun generateRandomLottoNumbers(): List<Int> {
        return (1..Lotto.MAX_NUMBER).shuffled().take(Lotto.NUMBER_COUNT).sorted()
    }

    // 로또 결과 확인
    fun checkLottoResults(lottos: List<Lotto>, winningNumbers: List<Int>, bonusNumber: Int): List<LottoResult> {
        return lottos.map { lotto ->
            val matchCount = lotto.numbers.count { it in winningNumbers }
            val isBonusMatched = bonusNumber in lotto.numbers
            LottoResult(matchCount, calculatePrize(matchCount, isBonusMatched))
        }
    }

    // 당첨 금액 계산
    fun calculatePrize(matchCount: Int, isBonusMatched: Boolean): Int {
        return when (matchCount) {
            3 -> 5000
            4 -> 50000
            5 -> if (isBonusMatched) 30000000 else 1500000
            6 -> 2000000000
            else -> 0
        }
    }

    // 당첨 통계 계산
    fun countWinnings(lottoResults: List<LottoResult>): Map<Int, Int> {
        return lottoResults.groupingBy { it.matchCount }.eachCount()
    }

    // 수익률 계산
    fun calculateProfitRate(lottoResults: List<LottoResult>, purchaseAmount: Int): Double {
        val totalPrize = lottoResults.sumOf { it.prize }
        return totalPrize.toDouble() / purchaseAmount
    }
}
