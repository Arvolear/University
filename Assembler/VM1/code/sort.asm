.org 0

arr: .dw 2, 4, 3, 1, 5
min: .dw 0
tmp: .dw 0
i: .dw 0
j: .dw 0

begin:

    outer:
        LDA i
        STA j
        LDA i
        STA min

        inner:
            LDA @min
            CMP @j
            JL toMin
            JG nope
            JE nope

            toMin:
                LDA j
                STA min
            nope:

            LDA j
            ADD #2
            STA j
        LDA j
        CMP #min
        JNE inner

        LDA @i
        STA tmp
        LDA @min
        STA @i
        LDA tmp
        STA @min

        LDA i
        ADD #2
        STA i
    LDA i
    CMP #min
    JNE outer

    LDA #0
    STA i

    print:
        LDA @i
        OUT #1

        LDA i
        ADD #2
        STA i
    LDA i
    CMP #min
    JNE print

HALT
.END begin
