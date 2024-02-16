package dev.badbird.desfire.objects.nfc.desfire

import android.util.Log

abstract class Reader {
    abstract fun executeCommand(b2: Byte, bArr: ByteArray?, i2: Int, i3: Int): ByteArray?
    fun checkIfPrestoCard(bArr: ByteArray): Boolean {
        val a = executeCommand(90.toByte(), bArr, 0, bArr.size)
        Log.d("NFCCardReader", "Command executed:")
        Log.d("NFCCardReader", a?.contentToString() ?: "null")
        if (a == null || a.size < 2 || a[a.size - 1].toInt() != 0 || a[a.size - 2].toInt() != -111) {
            Log.d("NFCCardReader", "Error executing command")
            return false
        }
        Log.d("NFCCardReader", "Command executed successfully")
        return true
    }
}
