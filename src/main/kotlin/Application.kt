import kotlin.random.Random

fun main() {
    try {
        val cars = inputCarNames()
        val attempts = inputAttempts()
        val raceResults = race(cars, attempts)

        println("실행 결과")
        raceResults.forEach { roundResult ->
            roundResult.forEach { (car, position) ->
                println("${car.name} : ${"-".repeat(position)}")
            }
            println()
        }

        // 우승자 발표
        val winners = findWinners(cars)
        println("최종 우승자 : ${winners.joinToString(", ") { it.name }}")
    } catch (e: IllegalArgumentException) {
        println("[ERROR] ${e.message}")
    }
}

data class Car(val name: String, var position: Int = 0)

fun inputCarNames(): List<Car> {
    println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)")
    val input = readlnOrNull()?.trim() ?: throw IllegalArgumentException("자동차 이름을 입력해주세요.")

    val carNames = input.split(",").map { it.trim() }
    if (carNames.any { it.length > 5 }) {
        throw IllegalArgumentException("자동차 이름은 5자 이하여야 합니다.")
    }

    return carNames.map { Car(it) }
}

fun inputAttempts(): Int {
    println("시도할 회수는 몇회인가요?")
    val input = readlnOrNull()?.trim()?.toIntOrNull() ?: throw IllegalArgumentException("숫자를 입력해주세요.")
    if (input <= 0) throw IllegalArgumentException("시도 횟수는 1 이상이어야 합니다.")
    return input
}

fun race(cars: List<Car>, attempts: Int): List<List<Pair<Car, Int>>> {
    val raceResults = mutableListOf<List<Pair<Car, Int>>>()

    repeat(attempts) {
        cars.forEach { car ->
            if (Random.nextInt(0, 10) >= 4) {
                car.position++
            }
        }
        raceResults.add(cars.map { car -> car to car.position })
    }

    return raceResults
}

fun findWinners(cars: List<Car>): List<Car> {
    val maxPosition = cars.maxOf { it.position }
    return cars.filter { it.position == maxPosition }
}