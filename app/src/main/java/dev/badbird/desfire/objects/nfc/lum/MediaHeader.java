package dev.badbird.desfire.objects.nfc.lum;

public class MediaHeader extends LumBlock {

    /* renamed from: f  reason: collision with root package name */
    public LumUInt32Field f6807f = new LumUInt32Field(this, 1, 4, 28);

    /* renamed from: g  reason: collision with root package name */
    public LumByteField f6808g = new LumByteField(this, 1, 0, 4);

    public MediaHeader(String str, int i2, byte b2, byte b3, byte[] bArr) {
        super(str, i2, b2, b3, bArr);
    }
}
