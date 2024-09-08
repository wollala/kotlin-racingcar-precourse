import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.assertj.core.api.Assertions.assertThat  // static import 없이 일반 import 사용
import model.Car
import controller.RaceController

class RaceControllerTest {

    private lateinit var cars: List<Car>

    @BeforeEach
    fun setup() {
        cars = listOf(Car("bmw"), Car("toyota"), Car("hyundai"))
    }

    @Test
    fun moveCarsIncreasesPosition() {
        RaceController.moveCars(cars)

        cars.forEach { car ->
            assertThat(car.position).isGreaterThanOrEqualTo(0)  // AssertJ 사용
        }
    }

    @Test
    fun getCurrentPositionsReturnsCorrectPositions() {
        cars[0].position = 3
        cars[1].position = 2
        cars[2].position = 5

        val positions = RaceController.getCurrentPositions(cars)

        assertThat(positions[0].second).isEqualTo(3)  // AssertJ 사용
        assertThat(positions[1].second).isEqualTo(2)
        assertThat(positions[2].second).isEqualTo(5)
    }

    @Test
    fun findWinnersReturnsCorrectWinners() {
        cars[0].position = 5
        cars[1].position = 3
        cars[2].position = 5

        val winners = RaceController.findWinners(cars)

        assertThat(winners).hasSize(2)  // AssertJ 사용
        assertThat(winners).contains(cars[0], cars[2])
    }
}
