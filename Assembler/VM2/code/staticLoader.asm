arr: .dw 1002, 2, 3, 3328, 1002, 1004, 2304, 65531, 256 // array of sumAB program values starting from the 100 address

copyFrom: .dw 0 // pointer to the array element
copyTo: .dw 1000 // pointer to the active address cell to copy to
addr: .dw 0 // entry address

begin:
    MOV copyFrom, &arr // statring position of the array
    MOV addr, *copyFrom // starting position of 'to copy to'

    loop:
        MOV *copyTo, *copyFrom // copy the value

        ADD copyTo, &2 // next 'copy to' location
        MOV copyTo, %A

        ADD copyFrom, &2 // next array element
        MOV copyFrom, %A

    JNE copyFrom, &copyFrom, loop
    JMP *addr // call the copied program

STOP
.END begin
