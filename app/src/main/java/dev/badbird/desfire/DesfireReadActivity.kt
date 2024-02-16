package dev.badbird.desfire

import android.annotation.SuppressLint
import android.app.Activity
import android.app.PendingIntent
import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.IsoDep
import android.nfc.tech.MifareUltralight
import android.nfc.tech.NfcA
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import dev.badbird.desfire.objects.CardNumberData
import dev.badbird.desfire.objects.PrestoCard
import dev.badbird.desfire.utils.Callback
import java.io.IOException

class DesfireReadActivity : Activity() {
    private lateinit var versionTextView: TextView
    private lateinit var cardTypeTextView: TextView
    private lateinit var cardNumberTextView: TextView
    private lateinit var nfcAdapter: NfcAdapter
    var nfcHandler: NFCHandler? = null // f5873n

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_desfire_read)

        versionTextView = findViewById(R.id.versionTextView)
        cardTypeTextView = findViewById(R.id.cardTypeTextView)
        cardNumberTextView = findViewById(R.id.cardNumberTextView)
        nfcAdapter = NfcAdapter.getDefaultAdapter(this)

        if (nfcAdapter == null) {
            Toast.makeText(this, "NFC is not available on this device", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        if (!nfcAdapter.isEnabled) {
            Toast.makeText(this, "NFC is not enabled", Toast.LENGTH_SHORT).show()
        }


        this.nfcHandler = NFCHandler(this, object : Callback<ByteArray?> {
            override fun run(t: ByteArray?) {
                Log.d("DesfireReadVersion", "Callback run")
                if (t != null) {
                    Log.d("DesfireReadVersion", "bArr is not null")
                    versionTextView.text = getString(R.string.card_version, t.contentToString())
                    Log.d("DesfireReadVersion", versionTextView.text.toString())
                } else {
                    versionTextView.text = getString(R.string.error_reading_version)
                }
            }
        }) {
            Log.d("DesfireReadVersion", "Update callback run")
            val card = nfcHandler?.card
            if (card != null) {
                cardTypeTextView.text = getString(R.string.card_type, card.type)
            } else {
                cardTypeTextView.text = getString(R.string.card_type, "Unknown")
            }

            if (CardNumberData.cardNumberDataInstance != null) {
                cardNumberTextView.text = getString(R.string.card_number, CardNumberData.cardNumberDataInstance?.cardNumber ?: "Unknown")
            }
        }
        Toast.makeText(this, "NFC is enabled", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        enableNfcForegroundDispatch()
    }

    override fun onPause() {
        super.onPause()
        disableNfcForegroundDispatch()
    }

    private fun enableNfcForegroundDispatch() {
        val intent = Intent(this, javaClass).apply {
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE)
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null)
    }

    private fun disableNfcForegroundDispatch() {
        nfcAdapter.disableForegroundDispatch(this)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.d("DesfireReadVersion", "New intent: ${intent.action}")
        if (NfcAdapter.ACTION_TECH_DISCOVERED == intent.action || NfcAdapter.ACTION_TAG_DISCOVERED == intent.action || NfcAdapter.ACTION_NDEF_DISCOVERED == intent.action) {
            val tag = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)
            /*
              String[] techList = tag.getTechList();
            try {
                if (Arrays.asList(techList).contains(IsoDep.class.getName())) {
                    IsoDep isoDep = IsoDep.get(tag);
                    this.f5871g = isoDep;
                    message = this.f5873n.obtainMessage(0, isoDep);
                } else {
                    if (Arrays.asList(techList).contains(MifareUltralight.class.getName())) {
                        message = this.f5873n.obtainMessage(1, MifareUltralight.get(tag));
                    }
                    message = null;
                }
            } catch (Exception e2) {
                e2.toString();
            }
            try {
                this.f5873n.sendMessage(message);
            } catch (Exception unused) {
                if (message != null) {
                    bundle.putString("msg", message.toString());
                }
                bundle.putString("SCREEN_NAME", "NFCDiscoveryImpl");
                "NFCException".replace(TokenAuthenticationScheme.SCHEME_DELIMITER, "_");
                throw null;
            }
             */
            val techList = tag!!.techList
            var message: Message? = null
            val action = intent.action
            val bundle = Bundle()
            try {
                if (listOf(*techList).contains(IsoDep::class.java.name)) {
                    val isoDep = IsoDep.get(tag)
                    // this.f5871g = isoDep
                    message = this.nfcHandler?.obtainMessage(0, isoDep)
                } else {
                    if (listOf(*techList).contains(MifareUltralight::class.java.name)) {
                        message = this.nfcHandler?.obtainMessage(1, MifareUltralight.get(tag))
                    }
                    // message = null
                }
            } catch (e2: Exception) {
                e2.toString()
            }
            try {
                if (message != null) {
                    this.nfcHandler?.sendMessage(message)
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

    @SuppressLint("SetTextI18n")
    private fun readDesfireVersion(tag: Tag?) {
        val nfcA = NfcA.get(tag)
        if (nfcA != null) {
            try {
                nfcA.timeout = 15000
                nfcA.connect()

                Toast.makeText(this, "Connected to tag", Toast.LENGTH_SHORT).show()
                val version = nfcA.transceive(byteArrayOf(0x60))

                versionTextView.text = "Desfire version: ${version.contentToString()}"
                Log.d("DesfireReadVersion", "Desfire version: ${version.contentToString()}")
            } catch (e: IOException) {
                Log.e("DesfireReadVersion", "Error reading Desfire version", e)
                Toast.makeText(this, "${e.message}", Toast.LENGTH_SHORT).show()
                versionTextView.text = "Error reading Desfire version"
            } finally {
                nfcA.close()
                Log.d("DesfireReadVersion", "NfcA closed")
            }
        } else {
            Log.e("DesfireReadVersion", "NfcA is null")
            Toast.makeText(this, "NfcA is null", Toast.LENGTH_SHORT).show()
        }
    }
}
