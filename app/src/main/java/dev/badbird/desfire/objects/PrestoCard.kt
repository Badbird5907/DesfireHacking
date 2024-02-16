package dev.badbird.desfire.objects

import DesFireFile
import dev.badbird.desfire.objects.nfc.desfire.CardHeader
import dev.badbird.desfire.objects.nfc.desfire.DESFireCard

/* compiled from: PrestoCard */
class PrestoCard : DESFireCard() {
    /* renamed from: e  reason: collision with root package name */
    var desfireFile: DesFireFile? = null

    /* renamed from: f  reason: collision with root package name */
    var f5883f: CardHeader? = null

    init {
        this.desfireFile = DesFireFile("CardHeader", 8.toByte(), 32)
    }

    /* JADX WARNING: Removed duplicated region for block: B:50:0x0103 A[RETURN] */ /* JADX WARNING: Removed duplicated region for block: B:51:0x0104  */ /* Code decompiled incorrectly, please refer to instructions dump. */
    fun checkLumCard(): Boolean {
        /*
            r16 = this;
            r0 = r16
            b.g.a.a.a.e0.k.l r1 = r0.f5866b
            r2 = 256(0x100, float:3.59E-43)
            byte[] r2 = new byte[r2]
            r3 = 96
            r4 = 0
            r5 = 0
            byte[] r3 = r1.a(r3, r4, r5, r5)
            r6 = 0
        L_0x0011:
            r7 = -81
            r8 = 2
            r9 = -1
            if (r3 == 0) goto L_0x003c
            int r10 = r3.length
            if (r10 < r8) goto L_0x003c
            int r10 = r3.length
            int r10 = r10 + r9
            byte r10 = r3[r10]
            if (r10 != r7) goto L_0x003c
            int r10 = r3.length
            int r10 = r10 - r8
            java.lang.System.arraycopy(r3, r5, r2, r6, r10)
            int r3 = r3.length
            int r3 = r3 - r8
            int r6 = r6 + r3
            byte[] r3 = r1.a(r7, r4, r5, r5)
            if (r3 == 0) goto L_0x0011
            int r10 = r3.length
            if (r10 < r8) goto L_0x0011
            int r10 = r3.length
            int r10 = r10 + r9
            byte r10 = r3[r10]
            if (r10 != 0) goto L_0x0011
            int r1 = r3.length
            int r1 = r1 - r8
            java.lang.System.arraycopy(r3, r5, r2, r6, r1)
        L_0x003c:
            b.g.a.a.a.e0.k.f r1 = r0.c
            java.util.Objects.requireNonNull(r1)
            r3 = 6
            r6 = 5
            r10 = 4
            r11 = 7
            r12 = 3
            r13 = 1
            byte[] r14 = new byte[r11]     // Catch:{ Exception -> 0x0075 }
            r15 = 14
            byte r15 = r2[r15]     // Catch:{ Exception -> 0x0075 }
            r14[r5] = r15     // Catch:{ Exception -> 0x0075 }
            r15 = 15
            byte r15 = r2[r15]     // Catch:{ Exception -> 0x0075 }
            r14[r13] = r15     // Catch:{ Exception -> 0x0075 }
            r15 = 16
            byte r15 = r2[r15]     // Catch:{ Exception -> 0x0075 }
            r14[r8] = r15     // Catch:{ Exception -> 0x0075 }
            r15 = 17
            byte r15 = r2[r15]     // Catch:{ Exception -> 0x0075 }
            r14[r12] = r15     // Catch:{ Exception -> 0x0075 }
            r15 = 18
            byte r15 = r2[r15]     // Catch:{ Exception -> 0x0075 }
            r14[r10] = r15     // Catch:{ Exception -> 0x0075 }
            r15 = 19
            byte r15 = r2[r15]     // Catch:{ Exception -> 0x0075 }
            r14[r6] = r15     // Catch:{ Exception -> 0x0075 }
            r15 = 20
            byte r2 = r2[r15]     // Catch:{ Exception -> 0x0075 }
            r14[r3] = r2     // Catch:{ Exception -> 0x0075 }
            r1.a = r14     // Catch:{ Exception -> 0x0075 }
        L_0x0075:
            b.g.a.a.a.e0.k.f r1 = r0.c
            byte[] r1 = r1.a
            b.g.a.a.a.e0.k.l r1 = r0.f5866b
            byte[] r2 = new byte[r12]
            r2 = {-1, 48, -1} // fill-array
            boolean r1 = r1.b(r2)
            if (r1 != 0) goto L_0x0087
            return r5
        L_0x0087:
            b.g.a.a.a.e0.k.e r1 = r0.f5882e
            java.util.Objects.requireNonNull(r1)
            b.g.a.a.a.e0.k.k r2 = a()
            b.g.a.a.a.e0.k.l r2 = r2.f5866b
            r14 = 8
            r15 = 32
            byte[] r1 = r1.a
            java.util.Objects.requireNonNull(r2)
            byte[] r9 = new byte[r11]
            byte r14 = (byte) r14
            r9[r5] = r14
            byte r14 = (byte) r5
            r9[r13] = r14
            r9[r8] = r14
            r9[r12] = r14
            byte r15 = (byte) r15
            r9[r10] = r15
            r9[r6] = r14
            r9[r3] = r14
            r3 = -67
            byte[] r3 = r2.a(r3, r9, r5, r11)
            if (r3 == 0) goto L_0x0100
            int r6 = r3.length
            if (r6 < r8) goto L_0x0100
            int r6 = r3.length
            int r6 = r6 - r13
            byte r6 = r3[r6]
            if (r6 == 0) goto L_0x00c5
            int r6 = r3.length
            int r6 = r6 - r13
            byte r6 = r3[r6]
            if (r6 != r7) goto L_0x0100
        L_0x00c5:
            int r6 = r3.length
            int r6 = r6 - r8
            java.lang.System.arraycopy(r3, r5, r1, r5, r6)
            int r6 = r3.length
            int r6 = r6 - r8
            int r6 = r6 + r5
        L_0x00cd:
            int r9 = r3.length
            if (r9 < r8) goto L_0x00fe
            int r9 = r3.length
            int r9 = r9 - r13
            byte r3 = r3[r9]
            if (r3 != r7) goto L_0x00fe
            byte[] r3 = r2.a(r7, r4, r5, r5)
            if (r3 == 0) goto L_0x00ee
            int r9 = r3.length
            if (r9 < r8) goto L_0x00ee
            int r9 = r3.length
            int r9 = r9 - r13
            byte r9 = r3[r9]
            if (r9 != r7) goto L_0x00ee
            int r9 = r3.length
            int r9 = r9 - r8
            java.lang.System.arraycopy(r3, r5, r1, r6, r9)
            int r9 = r3.length
            int r9 = r9 - r8
            int r6 = r6 + r9
            goto L_0x00cd
        L_0x00ee:
            if (r3 == 0) goto L_0x0100
            int r2 = r3.length
            if (r2 < r8) goto L_0x0100
            int r2 = r3.length
            int r2 = r2 - r13
            byte r2 = r3[r2]
            if (r2 != 0) goto L_0x0100
            int r2 = r3.length
            int r2 = r2 - r8
            java.lang.System.arraycopy(r3, r5, r1, r6, r2)
        L_0x00fe:
            r1 = 1
            goto L_0x0101
        L_0x0100:
            r1 = 0
        L_0x0101:
            if (r1 != 0) goto L_0x0104
            return r5
        L_0x0104:
            b.g.a.a.a.e0.k.b r1 = new b.g.a.a.a.e0.k.b
            b.g.a.a.a.e0.k.e r2 = r0.f5882e
            byte[] r2 = r2.a
            r1.<init>(r2)
            r0.f5883f = r1
            b.g.a.a.a.e0.k.l r1 = r0.f5866b
            byte[] r2 = new byte[r12]
            r2 = {0, 32, 0} // fill-array
            boolean r1 = r1.b(r2)
            if (r1 != 0) goto L_0x011d
            return r5
        L_0x011d:
            b.g.a.a.a.e0.k.l r1 = r0.f5866b
            java.util.Objects.requireNonNull(r1)
            byte[] r2 = new byte[r13]
            r2[r5] = r13
            r3 = 100
            byte[] r1 = r1.a(r3, r2, r5, r13)
            if (r1 == 0) goto L_0x013a
            int r2 = r1.length
            if (r2 <= r8) goto L_0x013a
            int r2 = r1.length
            int r2 = r2 - r13
            byte r2 = r1[r2]
            if (r2 != 0) goto L_0x013a
            byte r1 = r1[r5]
            goto L_0x013b
        L_0x013a:
            r1 = -1
        L_0x013b:
            r2 = -1
            if (r1 != r2) goto L_0x013f
            return r5
        L_0x013f:
            return r13
        */
        if (true) { // TODO: figure this out, for now we can't support LUM cards.
            return false
        }
        throw UnsupportedOperationException("Method not decompiled: b.g.a.a.a.e0.k.k.b():boolean")
    }

    companion object {
        /* renamed from: d  reason: collision with root package name */
        private var prestoCard: PrestoCard? = null

        fun getCard(): PrestoCard? {
            if (prestoCard == null) {
                prestoCard = PrestoCard()
            }
            return prestoCard
        }
    }
}