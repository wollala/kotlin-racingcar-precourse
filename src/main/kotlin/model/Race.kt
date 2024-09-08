package model

class Race(private val cars: List<Car>, val attempts: Int) {
    fun getCars(): List<Car> {
        return cars
    }
}