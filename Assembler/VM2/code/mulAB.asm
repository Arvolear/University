.START 200 // starting from 200 address

copyTo: .dw 500 // address to copy to
addr: .dw 502 // address to start the loader from
a: .dw 2
b: .dw 3

begin:
    MUL a, b
    
    OUT %A

STOP
.END begin
