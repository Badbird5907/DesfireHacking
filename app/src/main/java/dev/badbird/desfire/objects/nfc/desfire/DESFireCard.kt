package dev.badbird.desfire.objects.nfc.desfire

import dev.badbird.desfire.objects.nfc.Card

open class DESFireCard : Card() {
    /* renamed from: b  reason: collision with root package name */
    var reader: Reader? = null
    var version: DESFireVersion = DESFireVersion()
}
