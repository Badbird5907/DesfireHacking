package dev.badbird.desfire.objects.nfc.lum

import dev.badbird.desfire.objects.nfc.Card
import dev.badbird.desfire.objects.nfc.UltralightReader
import dev.badbird.desfire.utils.m
import java.util.*

/* compiled from: LumCard */
class LumCard : Card() {
    val c: LumBlock

    /* renamed from: d  reason: collision with root package name */
    val f6794d: ByteArray

    /* renamed from: e  reason: collision with root package name */
    val f6795e: ByteArray = ByteArray(16)

    /* renamed from: f  reason: collision with root package name */
    val f6796f: ByteArray = ByteArray(16)

    /* renamed from: g  reason: collision with root package name */
    var f6797g: ByteArray

    /* renamed from: h  reason: collision with root package name */
    val f6798h: ByteArray

    /* renamed from: i  reason: collision with root package name */
    var f6799i: Long = 0

    /* renamed from: j  reason: collision with root package name */
    var f6800j: UltralightReader

    /* renamed from: k  reason: collision with root package name */
    var f6801k: LumBlockHandler<MediaHeader>

    /* renamed from: l  reason: collision with root package name */
    val f6802l: LumBlockHandler<MediaIssuanceStatusDetails>

    /* renamed from: m  reason: collision with root package name */
    var f6803m: MutableList<ByteArray?>? = null

    init {
        val bArr = ByteArray(136)
        this.f6794d = bArr
        val bArr2 = ByteArray(4)
        this.f6798h = bArr2
        val bArr3 = ByteArray(4)
        this.f6797g = bArr3
        bArr3[0] = 1
        bArr3[1] = 0
        bArr3[2] = 0
        bArr3[3] = 0
        this.f6800j = dev.badbird.desfire.objects.nfc.NFCMifareultralightReader
        val lumBlockVar: LumBlock = LumBlock("PageSizeIndicator", 1, 38.toByte(), 38.toByte(), bArr2)
        this.c = lumBlockVar
        val bArr4 = bArr
        val mediaHeaderVar: MediaHeader = MediaHeader("MediaHeader", 2, 4.toByte(), 5.toByte(), bArr4)
        val mediaIssuanceStatusDetailsVar: MediaIssuanceStatusDetails =
            MediaIssuanceStatusDetails("MediaIssuanceStatusDetails", 3, 6.toByte(), 8.toByte(), bArr4)
        LinkedList<Any?>()
        this.f6801k = LumBlockHandler(mediaHeaderVar, 2, lumBlockVar, -1, 0, 1, 0)
        this.f6802l = LumBlockHandler(mediaIssuanceStatusDetailsVar, 3, lumBlockVar, -1, 0, 1, 0)
    }

    fun a(i2: Int): ByteArray {
        return f6803m!![i2]!! // FIXME
    }

    fun b(): Boolean {
        var bArr: ByteArray?
        val z: Boolean
        f6802l.b()
        f6801k.b()
        val ultralightReaderVar: UltralightReader = this.f6800j
        val bArr2 = this.f6796f
        Objects.requireNonNull<Any>(ultralightReaderVar)
        try {
            bArr = mifareUltralight?.transceive(byteArrayOf(48, 4.toByte())) // read, page 4
        } catch (e2: Exception) {
            e2.message
            bArr = null
        }
        if (bArr == null || bArr.size != 16) {
            z = false
        } else {
            System.arraycopy(bArr, 0, bArr2, 0, 16) // pad
            z = true
        }
        if (!z || !f6800j.a(4, 33, this.f6794d) || !f6800j.a(38, 41, this.f6795e)) {
            return false
        }
        val bArr3 = this.f6795e
        this.f6803m = ArrayList()
        if (bArr3 == null || bArr3.size != 16) {
            return false
        }
        var i2 = 0
        while (i2 < bArr3.size) {
            val bArr4 = ByteArray(4)
            System.arraycopy(bArr3, i2, bArr4, 0, 4)
            f6803m?.add(bArr4)
            i2 += 4
        }
        val a = a(3)
        val m2: Long = (m(a, 8, 8) * 256) + m(a, 0, 8)
        this.f6799i = m2
        if (m2 % 2 != 0L) {
            System.arraycopy(a(1), 0, this.f6798h, 0, 4)
        } else {
            System.arraycopy(a(0), 0, this.f6798h, 0, 4)
        }
        return true
    }

    companion object {
        //
        /* renamed from: b  reason: collision with root package name */
        var f6793b: LumCard? = null
    }
}