package dev.badbird.desfire.objects.nfc.lum

import dev.badbird.desfire.utils.m
import java.util.*

/* compiled from: LumBlockHolder */
class LumBlockHandler<T : LumBlock?>(
    lumBlockVar: LumBlock?,
    i2: Int,
    lumBlockVar2: LumBlock?,
    i3: Int,
    i4: Int,
    i5: Int,
    i6: Int
) {
    var a: T? = null

    /* renamed from: b  reason: collision with root package name */
    var f6812b: Int = 0
    var c: Int = 0

    /* renamed from: d  reason: collision with root package name */
    var f6813d: Int = 0

    /* renamed from: e  reason: collision with root package name */
    var f6814e: Int = 0

    /* renamed from: f  reason: collision with root package name */
    var f6815f: LumBlock? = null

    /* renamed from: g  reason: collision with root package name */
    var f6816g: ByteArray? = null

    init {
        try {
            this.a = lumBlockVar as T? // FIXME
            this.f6812b = i3
            this.c = i4
            this.f6815f = lumBlockVar2
            this.f6813d = i5
            val bArr = ByteArray((i2 * 4))
            val i7 = i5 - i6
            this.f6814e = i7
            this.f6816g = ByteArray(i7)
        } catch (e2: Exception) {
            e2.message
        }
    }

    fun a(): T? {
        val i2 = this.f6812b
        if (i2 >= 0) {
            val t: T? = this.a
            val bArr: ByteArray = f6815f!!.f6810d
            val i3 = this.c
            t!!.c = m(bArr, (0 * i3) + i2, i3) as Int
        } else {
            a!!.c = 0
        }
        return this.a
    }

    fun b() {
        a?.f6811e?.clear()
        Objects.requireNonNull(this.a)
        for (i2 in 0 until this.f6814e) {
            f6816g!![i2] = -1
        }
    }
}