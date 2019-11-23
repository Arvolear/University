.ORG 100

copyTo: .dw 500
addr: .dw 504
a: .dw 2
b: .dw 3

begin:
    LDA a
    MUL b

    out #1

HALT
.END begin
