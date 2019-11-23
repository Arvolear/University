.org 0

fibA: .dw 0
fibB: .dw 1

num: .dw 0
i: .dw 0

tmp: .dw 0

fibbo:
  loop:
    lda i
    add #1
    sta i 

    lda fibA
    add fibB
    sta fibA

    lda fibA
    sta tmp
    lda fibB
    sta fibA
    lda tmp
    sta fibB                

  lda i
  cmp num
    jne loop

  ret

begin:
  in #2
  sta num

  call fibbo
  
  lda fibA
  out #1  
 
halt
.end begin
