package dev.badbird.desfire.objects

class CardNumberData private constructor() {
    var cardManufacturer: Long = 0
    var cardNumber: Long = 0
        set(j2) {
            field = j2
        }
    var isLumCard: Boolean = false
    var isPrestoCard: Boolean = false

    companion object {
        private var cardNumberData: CardNumberData? = null
        val cardNumberDataInstance: CardNumberData?
            get() {
                if (cardNumberData == null) {
                    cardNumberData = CardNumberData()
                }
                return cardNumberData
            }
    }
}