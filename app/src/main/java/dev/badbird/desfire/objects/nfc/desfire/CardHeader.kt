package dev.badbird.desfire.objects.nfc.desfire

import android.text.format.Time
import dev.badbird.desfire.utils.m
import java.io.IOException

/* compiled from: CardHeader */
class CardHeader(var data: ByteArray) {
    val MAX_URL_LENGTH: Int = 2000

    /* renamed from: b  reason: collision with root package name */
    var f5865b: Long

    init {
        m(data, 0, 10)
        m(this.data, 10, 20)
        var i2 = 30
        m(data, 30, 3)
        m(data, 33, 20)
        m(data, 53, 4)
        m(data, 57, 8)
        m(data, 65, 12)
        m(data, 77, 6)
        this.f5865b = m(data, 83, 26)
        m(data, 109, 6)
        try {
            val m2: Long = m(data, 115, 16)
            val i3 = (31L and m2).toInt()
            val i4: Int = (((m2 shr 9) and 127L).toInt()) + MAX_URL_LENGTH
            val i5 = ((m2 shr 5) and 15L).toInt()
            if (i5 != 0 && i5 <= 12 && i3 > 0) {
                when (i5) {
                    1, 3, 5, 7, 8, 10, 12 -> i2 = 31
                    2 -> if (i4 % 4 == 0) {
                        if (i4 % 100 == 0) {
                        }
                        i2 = 29
                    } else {
                        if (i4 % 400 != 0) {
                            i2 = 28
                        } else {
                            i2 = 29
                        }
                    }
                    4, 6, 9, 11 -> {
                    }
                    else -> throw IOException()
                }
                if (i3 <= i2) {
                    Time().set(i3, i5 - 1, i4)
                }
            }
        } catch (e2: IOException) {
            e2.message
        }
    }
}