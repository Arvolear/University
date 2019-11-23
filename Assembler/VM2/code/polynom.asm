a: .dw 1
b: .dw 0
c: .dw 1
i: .dw -5
j: .dw 5
h: .dw 1

ans: .dw 0

poly:  
  loop:
    mul i, i
    mov ans, %A
    mul ans, a
    mov ans, %A

    mul i, b
    sub ans, %A
    mov ans, %A

    add ans, c
    mov ans, %A

    out ans

    add i, h
    mov i, %A
  jl i, j, loop
  je i, j, loop  

ret

begin:
  call poly 

stop
.end begin