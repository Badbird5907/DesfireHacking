package dev.badbird.desfire

import android.annotation.SuppressLint
import android.app.Activity
import android.app.PendingIntent
import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.NfcA
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import java.io.IOException
import java.util.*

class DesfireReadVersionActivity : Activity() {
    private lateinit var versionTextView: TextView
    private lateinit var nfcAdapter: NfcAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_desfire_read_version)

        versionTextView = findViewById(R.id.versionTextView)
        nfcAdapter = NfcAdapter.getDefaultAdapter(this)

        if (nfcAdapter == null) {
            Toast.makeText(this, "NFC is not available on this device", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        if (!nfcAdapter.isEnabled) {
            Toast.makeText(this, "NFC is not enabled", Toast.LENGTH_SHORT).show()
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
        if (NfcAdapter.ACTION_TECH_DISCOVERED == intent.action || NfcAdapter.ACTION_TAG_DISCOVERED == intent.action) {
            val tag = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)
            readDesfireVersion(tag)
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
                val version = nfcA.transceive(byteArrayOf(0x60.toByte()))
                versionTextView.text = "Desfire version: ${version.contentToString()}"
                Log.d("DesfireReadVersion", "Desfire version: ${version.contentToString()}")
            } catch (e: IOException) {
                Log.e("DesfireReadVersion", "Error reading Desfire version", e)
                Toast.makeText(this, "${e.message}", Toast.LENGTH_SHORT).show()
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
