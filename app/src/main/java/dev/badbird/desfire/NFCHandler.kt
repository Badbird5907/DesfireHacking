package dev.badbird.desfire

import android.content.Context
import android.nfc.tech.IsoDep
import android.nfc.tech.MifareUltralight
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.Toast
import dev.badbird.desfire.objects.CardNumberData
import dev.badbird.desfire.objects.PrestoCard
import dev.badbird.desfire.objects.nfc.*
import dev.badbird.desfire.objects.nfc.lum.LumCard
import dev.badbird.desfire.objects.nfc.lum.LumCard.Companion.f6793b
import dev.badbird.desfire.objects.nfc.lum.mifareUltralight

/* compiled from: NfcHandler */
class NFCHandler(
    private val ctx: Context,
    private val callback: dev.badbird.desfire.utils.Callback<ByteArray?>,
    private val updateCallback: Runnable
) : Handler() {
    var cardReader: NFCCardReader? = null


    var card: Card? = null
    var c = true


    fun testVer() {
        try {
            val array2: ByteArray? = cardReader?.executeCommand(96.toByte(), null, 0, 0)
            callback.run(array2)
        } catch (e: Exception) {
            e.printStackTrace()
            callback.run(null)
        }
    }
    override fun handleMessage(message: Message) {
        val prestoCard: PrestoCard?
        if (message.obj != null) {
            this.cardReader = NFCCardReader()
            val bundle = Bundle()
            try {
                val i2 = message.what
                if (i2 == 0) {
                    Log.d("NFCHandler", "Is Presto card")
                    this.card = PrestoCard.getCard()
                    val isoDep = message.obj as IsoDep
                    isoDep.connect()
                    val nfcCardReader: NFCCardReader = this.cardReader!!
                    nfcCardReader.desfire = isoDep
                    if (nfcCardReader.checkIfPrestoCard(byteArrayOf(-1, 48, -1))) {
                        Log.d("NFCHandler", "Presto card detected!!")
                        Toast.makeText(ctx, "Presto card detected!!", Toast.LENGTH_SHORT).show()
                        prestoCard = PrestoCard.getCard()!!
                        prestoCard.type = CardType.PRESTO
                        prestoCard.reader = nfcCardReader

                        testVer()
                        updateCallback.run()
                    } else {
                        Log.d("NFCHandler", "Unsupported card detected!!")
                        prestoCard = PrestoCard.getCard()!!
                        prestoCard.type = CardType.UNSUPPORTED
                        updateCallback.run()
                    }
                    this.card = prestoCard
                    if (prestoCard == null || prestoCard.type !== CardType.PRESTO) {
//                        CardNumberData.getCardNumberDataInstance().setPrestoCard(false)
                        CardNumberData.cardNumberDataInstance?.isPrestoCard = false
                    } else {
                        Log.d("NFCHandler", "Checking if it is a presto card")
                        if (prestoCard.checkPrestoCard()) {
                            val aVar: PrestoCard = this.card as PrestoCard
                            val j2: Long = aVar.f5883f!!.f5865b
                            val j3: Long = aVar.f5883f!!.f5865b
                            Log.d("NFCHandler", "Is also a presto card!")
                            Log.d("NFCHandler", "J2: $j2 | J3: $j3")
                            if (this.c) {
//                            CardNumberData.getCardNumberDataInstance().setCardNumber(j2)
//                            CardNumberData.getCardNumberDataInstance().setLumCard(false)
//                            CardNumberData.getCardNumberDataInstance().setPrestoCard(true)
                                CardNumberData.cardNumberDataInstance?.cardNumber = j2
                                CardNumberData.cardNumberDataInstance?.isLumCard = false
                                CardNumberData.cardNumberDataInstance?.isPrestoCard = true
                                updateCallback.run()
                            }
                        } else {
                            Log.d("NFCHandler", "Doesn't respond like a presto card!")
                        }
                    }
                } else if (i2 == 1) {
                    Log.d("NFCHandler", "Is possible LUM card")
                    if (f6793b == null) {
                        f6793b = LumCard()
                    }
                    this.card = f6793b
                    val mfUl = message.obj as MifareUltralight
                    mfUl.connect()
                    mifareUltralight = mfUl;
                    cardReader!!.ultralight = mfUl
                    if (f6793b == null) {
                        f6793b = LumCard()
                    }
                    val lumCardVar2: LumCard = f6793b as LumCard
                    lumCardVar2.type = CardType.LUM
                    this.card = lumCardVar2
                    lumCardVar2.b()
                    val a2 = ((card as LumCard?)?.f6801k?.a()?.f6807f?.a?.a())
                    val a3 = ((card as LumCard?)?.f6801k?.a()?.f6808g?.a?.a())
                    CardNumberData.cardNumberDataInstance?.cardNumber = a2!!
                    CardNumberData.cardNumberDataInstance?.isPrestoCard = false
                    CardNumberData.cardNumberDataInstance?.isLumCard = true
                    Toast.makeText(ctx, "LUM card detected!!", Toast.LENGTH_SHORT).show()
                    Log.d("NFCHandler", "LUM card info:")
                    Log.d("NFCHandler", "Card number: $a2")
                    Log.d("NFCHandler", "LUM type: $a3")
                    testVer()
                    updateCallback.run()
                    if (a3 != null) {
                        bundle.putLong("lum type", a3)
                    }
                }
            } catch (exception: Exception) {
                bundle.putString("SCREEN_NAME", "NFCHandler Exception")
                throw exception
            }
        }
    }
}
