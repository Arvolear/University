res: .dw 0

stackBeg: .dw 400
stackCur: .dw 0
n: .dw 5
LL: .dw 0

fact:
  x: .dw 0
  MOV x, *stackCur

  SUB stackCur, &2
  MOV stackCur, %A
  MOV *stackCur, %L

  JE &0, x, finish

  SUB stackCur, &2
  MOV stackCur, %A
  SUB x, &1
  MOV *stackCur, %A

  CALL fact

  ADD stackCur, &2
  MOV stackCur, %A

  MUL res, *stackCur
  MOV res, %A

  ADD stackCur, &2
  MOV stackCur, %A
  MOV %L, *stackCur
ret

finish:
  MOV res, &1
  MOV %L, *stackCur

  ADD stackCur, &4
  MOV stackCur, %A
ret

begin:
  MOV stackCur, stackBeg
  MOV *stackCur, n

  CALL fact

  ADD stackCur, &2
  MOV stackCur, %A

  MUL res, *stackCur
  MOV res, %A

  out res

stop
.end begin
