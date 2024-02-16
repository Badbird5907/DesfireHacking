package dev.badbird.desfire.objects.nfc;


import static dev.badbird.desfire.objects.nfc.lum.UltralightKt.getMifareUltralight;

public abstract class UltralightReader {
    public boolean a(int i2, int i3, byte[] bArr) {
        byte[] bArr2;
        int i4 = i2;
        while (i2 <= i3) {
            try {
                bArr2 = getMifareUltralight().transceive(new byte[]{48, (byte) i2});
            } catch (Exception e2) {
                e2.getMessage();
                bArr2 = null;
            }
            int i5 = (i4 == 38 || i4 == 39) ? 0 : i4 * 4;
            if (bArr2 == null || bArr2.length != 16) {
                return false;
            }
            System.arraycopy(bArr2, 0, bArr, i5, i3 + -3 >= i2 ? bArr2.length : ((i3 - i2) + 1) * 4);
            i4 += 4;
            i2 += 4;
        }
        return true;
    }
}
