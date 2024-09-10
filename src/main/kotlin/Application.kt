import controller.LottoController
import view.LottoView

fun main() {
    val view = LottoView()
    val controller = LottoController(view)
    controller.start()
}
