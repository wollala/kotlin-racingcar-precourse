package view

class LottoView {
    fun inputManualCount(): Int {
        println("수동으로 구매할 로또 수를 입력해 주세요.")
        return readln().toInt()
    }

    fun inputManualNumbers(): List<Int> {
        println("수동으로 구매할 번호를 입력해 주세요.")
        return readln().split(",").map { it.trim().toInt() }
    }

    fun inputPurchaseAmount(): Int {
        println("구입금액을 입력해 주세요.")
        return readln().toInt()
    }

    fun inputWinningNumbers(): List<Int> {
        println("지난 주 당첨 번호를 입력해 주세요. (예: 1, 2, 3, 4, 5, 6)")
        return readln().split(",").map { it.trim().toInt() }
    }

    fun inputBonusNumber(): Int {
        println("보너스 볼을 입력해 주세요.")
        return readln().toInt()
    }

    fun printLottosType(manualCount: Int, autoCount: Int) {
        println("수동으로 ${manualCount}개, 자동으로 ${autoCount}개를 구매했습니다.")
    }

    fun printLottos(lottos: List<List<Int>>) {
        lottos.forEach { println(it) }
    }

    fun printStatistics(stats: Map<Int, Int>, profitRate: Double) {
        println("당첨 통계")
        println("---------")
        println("3개 일치 (5000원)- ${stats.getOrDefault(3, 0)}개")
        println("4개 일치 (50000원)- ${stats.getOrDefault(4, 0)}개")
        println("5개 일치 (1500000원)- ${stats.getOrDefault(5, 0)}개")
        println("6개 일치 (2000000000원)- ${stats.getOrDefault(6, 0)}개")
        println("총 수익률은 ${String.format("%.2f", profitRate)}입니다.")
    }
}
