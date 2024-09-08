package controller

import model.Car
import model.Race
import view.RaceView
import kotlin.random.Random

object RaceController {
    fun startRace() {
        try {
            val carNames = RaceView.inputCarNames()
            val attempts = RaceView.inputAttempts()

            val cars = carNames.map { Car(it) }
            val race = Race(cars, attempts)

            val raceResults = runRace(race)
            RaceView.displayRaceResults(raceResults)

            val winners = findWinners(race.getCars())
            RaceView.displayWinners(winners)
        } catch (e: IllegalArgumentException) {
            RaceView.displayError(e.message ?: "알 수 없는 오류")
        }
    }

    private fun runRace(race: Race): List<List<Pair<Car, Int>>> {
        val raceResults = mutableListOf<List<Pair<Car, Int>>>()

        repeat(race.attempts) {
            moveCars(race.getCars())
            raceResults.add(getCurrentPositions(race.getCars()))
        }

        return raceResults
    }

    fun moveCars(cars: List<Car>) {
        cars.forEach { car ->
            if (shouldMoveForward()) {
                car.position++
            }
        }
    }

    private fun shouldMoveForward(): Boolean {
        return Random.nextInt(0, 10) >= 4
    }

    fun getCurrentPositions(cars: List<Car>): List<Pair<Car, Int>> {
        return cars.map { car -> car to car.position }
    }

    fun findWinners(cars: List<Car>): List<Car> {
        val maxPosition = cars.maxOf { it.position }
        return cars.filter { it.position == maxPosition }
    }
}
