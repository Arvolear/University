.org 0

stackBeg: .dw 400
stackCur: .dw 0
n : .dw 5

x: .dw 0
LL: .dw 0
res: .dw 0

fibbo:
  lda @stackCur
  sta x

  lda stackCur
  sub #2
  sta stackCur

  lda #0
  sta @stackCur

  lda stackCur
  sub #2
  sta stackCur

  stl LL
  lda LL
  sta @stackCur  

  lda #0
  cmp x
  je finish

  lda #1
  cmp x
  je finish

  lda x
  sub #1
  sta x

  lda stackCur
  sub #2
  sta stackCur

  lda x
  sta @stackCur

  call fibbo

  lda stackCur
  add #2
  sta stackCur

  lda @stackCur
  sub #1
  sta x

  lda stackCur
  sub #6
  sta stackCur

  lda x
  sta @stackCur
    
  call fibbo

  lda @stackCur
  sta res

  lda stackCur
  add #10
  sta stackCur

  lda @stackCur
  sta LL
  ldl LL

  lda stackCur
  sub #4
  sta stackCur

  lda @stackCur
  add res
  sta @stackCur
  sta res

  lda stackCur
  add #6
  sta stackCur

  lda @stackCur
  add res
  sta @stackCur
ret

finish:
  lda stackCur
  add #2
  sta stackCur

  lda #1
  sta @stackCur 
ret

begin:
  lda stackBeg
  sta stackCur

  lda n
  sta @stackCur

  call fibbo

  lda @stackCur
  out #1  

halt
.end begin  
