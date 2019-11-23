.org 0

arr: .dw 1002, 2, 3, 576, 1002, 3904, 1004, 5120, 1, 8448

copyFrom: .dw 0
copyTo: .dw 1000
addr: .dw 0

begin:
    LDA #arr
    STA copyFrom

    LDA @copyfrom
    STA addr

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
