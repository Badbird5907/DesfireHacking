package dev.badbird.desfire.objects

import DesFireFile
import android.util.Log
import dev.badbird.desfire.objects.nfc.desfire.CardHeader
import dev.badbird.desfire.objects.nfc.desfire.DESFireCard
import dev.badbird.desfire.objects.nfc.desfire.DESFireVersion
import dev.badbird.desfire.objects.nfc.desfire.Reader
import java.util.*


/* compiled from: PrestoCard */
class PrestoCard : DESFireCard() {
    /* renamed from: e  reason: collision with root package name */
    var desfireFile: DesFireFile? = null

    /* renamed from: f  reason: collision with root package name */
    var f5883f: CardHeader? = null

    init {
        this.desfireFile = DesFireFile("CardHeader", 8.toByte(), 32)
    }

    /* JADX WARNING: Removed duplicated region for block: B:50:0x0103 A[RETURN] */ /* JADX WARNING: Removed duplicated region for block: B:51:0x0104  */ /* Code decompiled incorrectly, please refer to instructions dump. */
    fun checkPrestoCard(): Boolean {
        // if (true) return false;
        val b: Reader? = super.reader
        val array = ByteArray(256)
        var array2: ByteArray? = b?.executeCommand(96.toByte(), null, 0, 0)
        var n = 0
        while (array2 != null && array2.size >= 2 && array2[array2.size - 1].toInt() == -81) {
            System.arraycopy(array2, 0, array, n, array2.size - 2)
            val n2 = n + (array2.size - 2)
            array2 = b?.executeCommand((-81).toByte(), null, 0, 0)
            val array3 = array2
            n = n2
            if (array3 != null) {
                array2 = array3
                n = n2
                if (array3.size < 2) {
                    continue
                }
                array2 = array3
                n = n2
                if (array3[array3.size - 1].toInt() == 0) {
                    System.arraycopy(array3, 0, array, n2, array3.size - 2)
                    break
                }
                continue
            }
        }
        val c: DESFireVersion = super.version
        Objects.requireNonNull<Any>(c)
        while (true) {
            Log.d("PrestoCard", "Checking if it is a presto card")
            try {
                c.bytes = byteArrayOf(array[14], array[15], array[16], array[17], array[18], array[19], array[20])
//                val a: ByteArray = super.version.bytes
                if (!super.reader?.checkIfPrestoCard(byteArrayOf(-1, 48, -1))!!) {
                    return false
                }
                val e: CardHeader? = this.f5883f
                val b2: Reader = getCard()?.reader!!
                val a2: ByteArray = e?.data ?: ByteArray(256) // TODO: Copilot suggested the null check here, which fixes a infinite loop
                Objects.requireNonNull<Any>(b2)
                val b3: Byte = 8
                val b4: Byte = 0
                var array4: ByteArray? = b2.executeCommand((-67).toByte(), byteArrayOf(b3, b4, b4, b4, 32, b4, b4), 0, 7)
                var b5 = false
                run {
                    run {
                        if (array4 != null && array4!!.size >= 2 && (array4!![array4!!.size - 1].toInt() == 0 || array4!![array4!!.size - 1].toInt() == -81)) {
                            System.arraycopy(array4, 0, a2, 0, array4!!.size - 2)
                            var n3 = array4!!.size - 2 + 0
                            while (array4!!.size >= 2 && array4!![array4!!.size - 1].toInt() == -81) {
                                array4 = b2.executeCommand((-81).toByte(), null, 0, 0)
                                if (array4 != null && array4!!.size >= 2 && array4!![array4!!.size - 1].toInt() == -81) {
                                    System.arraycopy(array4, 0, a2, n3, array4!!.size - 2)
                                    n3 += array4!!.size - 2
                                } else {
                                    if (array4 != null && array4!!.size >= 2 && array4!![array4!!.size - 1].toInt() == 0) {
                                        System.arraycopy(array4, 0, a2, n3, array4!!.size - 2)
                                        break
                                    }
                                    break
                                }
                            }
                            b5 = true
                            return@run
                        }
                    }
                    b5 = false
                }
                /*
                if (!b5) {
                    return false
                }
                 */
                Log.d("PrestoCard", "Check passed run blocks")
                this.f5883f = CardHeader(this.desfireFile?.data!!)
                if (!super.reader?.checkIfPrestoCard(byteArrayOf(0, 32, 0))!!) {
                    return false
                }
                val b6: Reader? = super.reader
                Objects.requireNonNull<Any>(b6)
                val a3: ByteArray? = b6?.executeCommand(100.toByte(), byteArrayOf(1), 0, 1)
                val n4 = if (a3 != null && a3.size > 2 && a3[a3.size - 1].toInt() == 0) {
                    a3[0].toInt()
                } else {
                    -1
                }
                return n4 != -1
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    companion object {
        /* renamed from: d  reason: collision with root package name */
        private var prestoCard: PrestoCard? = null

        fun getCard(): PrestoCard? {
            if (prestoCard == null) {
                prestoCard = PrestoCard()
            }
            return prestoCard
        }
    }
}