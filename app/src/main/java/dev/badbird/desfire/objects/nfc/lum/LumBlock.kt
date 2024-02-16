package dev.badbird.desfire.objects.nfc.lum

import java.util.*

/* compiled from: LumBlock */
open class LumBlock(
    str: String?, var a: Int, /* renamed from: b  reason: collision with root package name */
    var f6809b: Byte, b3: Byte, /* renamed from: d  reason: collision with root package name */
    var f6810d: ByteArray
) {
    var c: Int = 0

    /* renamed from: e  reason: collision with root package name */
    var f6811e: LinkedList<Int>? = null

    init {
        this.f6811e = LinkedList()
    }
}