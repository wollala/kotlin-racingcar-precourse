package view

import model.Car

object RaceView {
    fun inputCarNames(): List<String> {
        println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)")
        val input = readlnOrNull()?.trim() ?: throw IllegalArgumentException("자동차 이름을 입력해주세요.")
        val carNames = input.split(",").map { it.trim() }
        if (carNames.any { it.length > 5 }) {
            throw IllegalArgumentException("자동차 이름은 5자 이하여야 합니다.")
        }
        return carNames
    }

    fun inputAttempts(): Int {
        println("시도할 회수는 몇회인가요?")
        val input = readlnOrNull()?.trim()?.toIntOrNull() ?: throw IllegalArgumentException("숫자를 입력해주세요.")
        if (input <= 0) throw IllegalArgumentException("시도 횟수는 1 이상이어야 합니다.")
        return input
    }

    fun displayRaceResults(raceResults: List<List<Pair<Car, Int>>>) {
        println("실행 결과")
        raceResults.forEach { roundResult ->
            roundResult.forEach { (car, position) ->
                println("${car.name} : ${"-".repeat(position)}")
            }
            println()
        }
    }

    fun displayWinners(winners: List<Car>) {
        println("최종 우승자 : ${winners.joinToString(", ") { it.name }}")
    }

    fun displayError(message: String) {
        println("[ERROR] $message")
    }
}
