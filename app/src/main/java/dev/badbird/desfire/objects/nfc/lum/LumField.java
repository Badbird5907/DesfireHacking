package dev.badbird.desfire.objects.nfc.lum;

import static dev.badbird.desfire.utils.UtilitiesKt.m;

public class LumField {
    public int a;

    /* renamed from: b  reason: collision with root package name */
    public int f6804b;
    public LumBlock c;

    /* renamed from: d  reason: collision with root package name */
    public int f6805d;

    /* renamed from: e  reason: collision with root package name */
    public int f6806e;

    public LumField(LumBlock lumBlockVar, int i2, int i3, int i4) {
        this.a = i4;
        this.f6804b = i3;
        this.c = lumBlockVar;
        this.f6806e = i2;
    }

    public long a() {
        LumBlock lumBlockVar = this.c;
        int i2 = lumBlockVar.getC() * lumBlockVar.getA() * 4 * 8;
        this.f6805d = (this.f6806e * 4 * 8) + i2 + (lumBlockVar.getF6809b() * 4 * 8) + this.f6804b;
        return m(this.c.getF6810d(), this.f6805d, this.a);
    }
}
