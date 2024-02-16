package dev.badbird.desfire.utils

fun m(bArr: ByteArray, i2: Int, i3: Int): Long {
    var j2: Long = 0
    for ((i4, i5) in ((i3 + i2) - 1 downTo i2).withIndex()) {
        if (((bArr[i5 / 8].toInt() shr (8 - ((i5 % 8) + 1))) and 1) == 1) {
            j2 = if (i4 == 0) 1 else j2 + ((2 shl (i4 - 1)).toLong())
        }
    }
    return j2
}