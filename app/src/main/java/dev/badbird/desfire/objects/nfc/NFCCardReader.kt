package dev.badbird.desfire.objects.nfc

import dev.badbird.desfire.objects.nfc.desfire.Reader
import android.nfc.tech.IsoDep
import android.nfc.tech.MifareUltralight
import android.util.Log
import dev.badbird.desfire.objects.nfc.lum.formatHex

class NFCCardReader : Reader() {
    var desfire: IsoDep? = null

    /* renamed from: b  reason: collision with root package name */
    var ultralight: MifareUltralight? = null


    override fun executeCommand(b2: Byte, bArr: ByteArray?, i2: Int, i3: Int): ByteArray? {
        Log.d("NFCCardReader", "------ Executing command: ${formatHex(b2)} ------")
        val bArr2 = if (i3 > 0) ByteArray((i3 + 6)) else ByteArray(5)
        bArr2[0] = -112
        bArr2[1] = b2
        bArr2[2] = 0
        bArr2[3] = 0
        bArr2[4] = i3.toByte()
        if (i3 > 0) {
            System.arraycopy(bArr, i2, bArr2, 5, i3)
            bArr2[i3 + 5] = 0
        }
        if (true) {
            try {
                return execRaw(bArr2)
            } finally {
                Log.d("NFCCardReader", "------ Command executed ------")
            }
        }
        val nfcCardReader: NFCCardReader = this
        try {
            // TODO: make sure this is right, original below: (see execRaw too)
            /*
            byte[] transceive = nfcCardReader.desfire.transceive(bArr2);
            return nfcCardReader.ultralight.transceive(bArr2);
             */
            val desfireTX = nfcCardReader.desfire?.transceive(bArr2)
            val ulTX = nfcCardReader.ultralight?.transceive(bArr2)
            if (desfireTX != null) {
                Log.d("NFCCardReader", "desfire.transceive is not null")
                return desfireTX
            } else if (ulTX != null) {
                Log.d("NFCCardReader", "ultralight.transceive is not null")
                return ulTX
            } else {
                Log.e("NFCCardReader", "No card detected!")
                return null
            }
        } catch (e2: Exception) {
            Log.e("NFCCardReader", "Error executing command", e2)
            return null
        }
    }

    fun execRaw(bytes: ByteArray?): ByteArray? {
        val nfcCardReader: NFCCardReader = this
        try {
            /*
            byte[] transceive = nfcCardReader.desfire.transceive(bArr2);
            return nfcCardReader.ultralight.transceive(bArr2);
             */
            if (nfcCardReader.desfire != null) {
                val desfireTX = nfcCardReader.desfire!!.transceive(bytes)
                Log.d("NFCCardReader", "desfire.transceive")
                Log.d("NFCCardReader", " -> " + desfireTX.contentToString())
                return desfireTX
            } else if (nfcCardReader.ultralight != null) {
                val ulTX = nfcCardReader.ultralight!!.transceive(bytes)
                Log.d("NFCCardReader", "ultralight.transceive")
                Log.d("NFCCardReader", " -> " + ulTX.contentToString())
                return ulTX
            } else {
                Log.e("NFCCardReader", "No card detected")
                return null
            }
        } catch (e2: Exception) {
            Log.e("NFCCardReader", "Error executing command", e2)
            return null
        }
    }
}
