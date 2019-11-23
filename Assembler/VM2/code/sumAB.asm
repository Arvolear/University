.START 100 // starting from 100 address

addr: .dw 1002 // address to start the loader from
a: .dw 2
b: .dw 3

begin:
    ADD a, b
    
    out %A

STOP
.END begin
