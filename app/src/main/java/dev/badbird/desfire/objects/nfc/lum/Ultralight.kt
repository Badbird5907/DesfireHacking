package dev.badbird.desfire.objects.nfc.lum

import android.nfc.tech.MifareUltralight
import kotlin.math.abs

var mifareUltralight: MifareUltralight? = null

fun formatHex(byte: Byte): String {
    return "${(byte.toInt() < 0).let { if (it) "-" else ""}}0x${abs(byte.toInt()).toString(16)} (${byte.toInt()})"
}