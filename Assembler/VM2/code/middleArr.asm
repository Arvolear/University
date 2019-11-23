arr: .dw 1, 2, 3, 4, 5
addr: .dw 0
sum: .dw 0
amount: .dw 0

sumArr:
  mov addr, &arr

  loop:
    add sum, *addr
    mov sum, %A 
    add addr, &2    
    mov addr, %A
    add amount, &1
    mov amount, %A
  
  jne addr, &addr, loop
ret 

begin:
  call sumArr

  div sum, amount
  out %A   
 
stop
.end begin
