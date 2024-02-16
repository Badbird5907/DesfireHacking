package dev.badbird.desfire.objects.nfc

import android.app.Activity
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.IntentFilter.MalformedMimeTypeException
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.TagLostException
import android.nfc.tech.IsoDep
import android.nfc.tech.MifareUltralight
import android.os.*
import dev.badbird.desfire.NFCHandler
import dev.badbird.desfire.utils.Callback
import java.io.IOException
import java.util.*

/* compiled from: NFCDiscoveryImpl */
class NFCDiscoveryImpl(
    private val ctx: Context,
    private val cb: Callback<ByteArray?>,
    private val rb: Runnable
) : Thread() {
    /* renamed from: e  reason: collision with root package name */
    var f5870e: NfcAdapter? = null

    /* renamed from: g  reason: collision with root package name */
    var f5871g: IsoDep? = null

    /* renamed from: k  reason: collision with root package name */
    var f5872k: Boolean = true

    /* renamed from: n  reason: collision with root package name */
    var f5873n: NFCHandler? = null

    init {
        isDaemon = true
        priority = 10
        start()
    }

    fun sendCommand(str: String?): String? {
        if (str == null) {
            return null
        }
        try {
            if (!f5871g!!.isConnected) {
                f5871g!!.connect()
            }
            if (!f5871g!!.isConnected) {
                return null
            }
            // i.a(this.f5871g) - some watchdog function
            val isoDep = this.f5871g
            val length = str.length / 2
            val bArr = ByteArray(length)
            for (i2 in 0 until length) {
                val i3 = i2 * 2
                bArr[i2] = str.substring(i3, i3 + 2).toInt(16).toByte()
            }
            return a(isoDep!!.transceive(bArr))
        } catch (e2: TagLostException) {
            e2.toString()
            return "NFC_TAG_LOST"
        } catch (e3: Exception) {
            if (e3.message == null || !e3.message!!.contains("Tag was lost.")) {
                try {
                    if (e3.message == null || (!e3.message!!.contains("NFC service died") && !e3.message!!.contains("NFC service dead") && !e3.message!!.contains(
                            "died"
                        ))
                    ) {
                        e3.toString()
                        try {
                            f5871g!!.close()
                            return null
                        } catch (unused: IOException) {
                            return null
                        } catch (unused: Exception) {
                            return null
                        }
                    } else {
                        e3.toString()
                        f5871g!!.close()
                        try {
                            f5871g!!.close()
                        } catch (unused2: IOException) {
                        } catch (unused2: Exception) {
                        }
                        return "NFC service died"
                    }
                } catch (e4: IOException) {
                    e4.toString()
                } catch (th: Throwable) {
                    try {
                        f5871g!!.close()
                    } catch (unused3: IOException) {
                    } catch (unused3: Exception) {
                    }
                    throw th
                }
            } else {
                f5871g!!.timeout = 3000
                e3.toString()
                try {
                    f5871g!!.close()
                } catch (unused4: IOException) {
                } catch (unused4: Exception) {
                }
                return "NFC_TAG_LOST"
            }
        }
        return null
    }

    fun onNewIntent(intent: Intent, z: Boolean) {
        var message: Message? = null
        val action = intent.action
        val bundle = Bundle()
        if (action == null) {
            return
        }
        if ("android.nfc.action.TECH_DISCOVERED" == action || "android.nfc.action.TAG_DISCOVERED" == action || "android.nfc.action.NDEF_DISCOVERED" == action) {
            val tag = intent.getParcelableExtra<Parcelable>("android.nfc.extra.TAG") as Tag?
            f5873n!!.c = z
            val techList = tag!!.techList
            try {
                if (listOf(*techList).contains(IsoDep::class.java.name)) {
                    val isoDep = IsoDep.get(tag)
                    // this.f5871g = isoDep
                    message = this.f5873n?.obtainMessage(0, isoDep)
                } else {
                    if (listOf(*techList).contains(MifareUltralight::class.java.name)) {
                        message = this.f5873n?.obtainMessage(1, MifareUltralight.get(tag))
                    }
                    // message = null
                }
            } catch (e2: Exception) {
                e2.toString()
            }
            try {
                if (message != null) {
                    this.f5873n?.sendMessage(message)
                }
            } catch (unused: Exception) {
                if (message != null) {
                    bundle.putString("msg", message.toString())
                }
                bundle.putString("SCREEN_NAME", "NFCDiscoveryImpl")
                throw RuntimeException("NFCException")
            }
        }
    }

    fun e(context: Context) {
        val defaultAdapter = NfcAdapter.getDefaultAdapter(context.applicationContext)
        this.f5870e = defaultAdapter
        defaultAdapter?.isEnabled
    }

    fun f(activity: Activity) {
        val intent = Intent(activity.applicationContext, activity.javaClass)
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        val activity2 = PendingIntent.getActivity(
            activity.applicationContext,
            0,
            intent,
            PendingIntent.FLAG_MUTABLE
        )
        val intentFilterArr = arrayOf(IntentFilter())
        intentFilterArr[0].addAction("android.nfc.action.TECH_DISCOVERED")
        intentFilterArr[0].addCategory("android.intent.category.DEFAULT")
        try {
            intentFilterArr[0].addDataType("*/*")
            val strArr = arrayOf(
                arrayOf(
                    IsoDep::class.java.name
                ), arrayOf(MifareUltralight::class.java.name)
            )
            val nfcAdapter = this.f5870e
            nfcAdapter?.enableForegroundDispatch(activity, activity2, intentFilterArr, strArr)
        } catch (unused: MalformedMimeTypeException) {
            throw RuntimeException("Check the Mime Type.")
        }
    }

    override fun run() {
        Looper.prepare()
        this.f5873n = NFCHandler(ctx, cb, rb)
        Looper.loop()
    }

    companion object {
        /* renamed from: b  reason: collision with root package name */
        var f5868b: NFCDiscoveryImpl? = null

        /* renamed from: d  reason: collision with root package name */
        val f5869d: CharArray = "0123456789ABCDEF".toCharArray()

        fun a(bArr: ByteArray): String {
            val cArr = CharArray((bArr.size * 2))
            for (i2 in bArr.indices) {
                val b2 = (bArr[i2].toInt() and 255).toByte()
                val i3 = i2 * 2
                val cArr2: CharArray = f5869d
                cArr[i3] = cArr2[b2.toInt() ushr 4]
                cArr[i3 + 1] = cArr2[b2.toInt() and 15]
            }
            return String(cArr)
        }

        fun c(ctx: Context, cb: Callback<ByteArray?>, rb: Runnable): NFCDiscoveryImpl {
            if (f5868b == null) {
                // i.b() - some watchdog function
                // b.g.a.a.a.e0.k.h.Companion.f5868b = b.g.a.a.a.e0.k.h()
                f5868b = NFCDiscoveryImpl(ctx,cb, rb)
            }
            return f5868b!!
        }
    }
}