arr: .dw 1, 2, 3, 4, 5
addr: .dw 0
sum: .dw 0

begin:
  mov addr, &arr

  loop:
    add sum, *addr
    mov sum, %A 
    add addr, &2    
    mov addr, %A
  jne addr, &addr, loop        

  out sum   
 
stop
.end begin