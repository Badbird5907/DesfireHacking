package dev.badbird.desfire.objects.nfc.lum;

public class LumUInt32Field {
    public LumField a = null;

    public LumUInt32Field(LumBlock lumBlockVar, int i2, int i3, int i4) {
        this.a = new LumField(lumBlockVar, i2, i3, i4);
    }
}
