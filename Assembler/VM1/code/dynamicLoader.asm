.org 0

table: .dw 5, 504, 7, 506
arr: .dw 500, 504, 2, 3, 576, 104, 4416, 106, 5120, 1, 8448

copyFrom: .dw 0
copyTo: .dw 0
addr: .dw 0

tmpTableInd: .dw 0
tmpArrInd: .dw 0
elem: .dw 0

begin:
    LDA #arr
    STA copyFrom

    LDA @copyFrom
    STA copyTo

    LDA copyFrom
    ADD #2
    STA copyFrom

    LDA @copyfrom
    STA addr

    LDA copyFrom
    SUB #2
    STA copyFrom

    LDA #table
    STA tmpTableInd

    indloop:
        LDA #arr
        STA tmpArrInd

        LDA @tmpTableInd
        MUL #2
        STA elem

        LDA tmpArrInd
        ADD elem
        STA tmpArrInd

        LDA tmpTableInd
        ADD #2
        STA tmpTableInd

        LDA @tmpTableInd
        STA @tmpArrInd

        LDA tmpTableInd
        ADD #2
        STA tmpTableInd

    LDA tmpTableInd
    CMP #arr
    JNE indloop

    loop:
        LDA @copyFrom
        STA @copyTo

        LDA copyTo
        ADD #2
        STA copyTo

        LDA copyFrom
        ADD #2
        STA copyFrom

    LDA #copyFrom
    CMP copyFrom
    JNE loop

    JMP @addr

halt
.END begin
