package model

data class Lotto(val numbers: List<Int>) {
    companion object {
        const val LOTTO_PRICE = 1000
        const val NUMBER_COUNT = 6
        const val MAX_NUMBER = 45
    }
}
