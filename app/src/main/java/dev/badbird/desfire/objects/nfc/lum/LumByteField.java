package dev.badbird.desfire.objects.nfc.lum;

public class LumByteField {
    public LumField a = null;

    public LumByteField(LumBlock lumBlockVar, int i2, int i3, int i4) {
        this.a = new LumField(lumBlockVar, i2, i3, i4);
    }
}
