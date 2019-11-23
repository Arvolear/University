.org 0

tree: .dw 76, 24, 6,  68, -1, 12,  65, 18, -1,  66, -1, -1,  83, 48, 30,  84, -1, 36,  65, 42, -1,  66, -1, -1,  72, -1, 54,  76, -1, 60,  84, -1, -1

input: .dw 76, 68, 65

treeAddr: .dw 0
inputAddr: .dw 0

ans: .dw 0

toSib:
    LDA treeAddr
    ADD #2
    STA treeAddr
    LDA @treeAddr

    CMP #-1
    je bad

    STA treeAddr
    ret

    bad:
        LDA #-1
        STA ans
ret

toSon:
    LDA treeAddr
    ADD #4
    STA treeAddr
    LDA @treeAddr

    CMP #-1
    je good

    STA treeAddr

    LDA inputAddr
    ADD #2
    STA inputAddr
    ret

    good:
        LDA #1
        STA ans
ret

begin:
    LDA #input
    STA inputAddr

    loop:
        LDA @inputAddr
        CMP @treeAddr
        JE callSon
        JNE callSib

        callSon:
            call toSon

            LDA ans
            CMP #0
            JE loop
            JNE finish

        callSib:
            call toSib

            LDA ans
            CMP #0
            JE loop
            JNE finish

    finish:
        LDA ans
        OUT #1

HALT
.END begin
