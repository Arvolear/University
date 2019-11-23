.org 0

arr: .dw 1, 2, 3, 4, 5
addr: .dw 0
sum: .dw 0

begin:
  lda #arr
  sta addr

  loop:
    lda @addr
    add sum
    sta sum

    lda addr
    add #2
    sta addr

    lda addr
    cmp #addr
  jne loop

  lda sum
  out #1

halt
.end begin
