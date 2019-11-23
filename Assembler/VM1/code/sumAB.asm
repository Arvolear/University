.ORG 100

addr: .dw 1002
a: .dw 2
b: .dw 3

begin:
    LDA a
    ADD b

    out #1

HALT
.END begin
