table: .dw 5, 504, 6, 506 // lables table ([arrInd], changeTo, [arrInd], changeTo)
arr: .dw 500, 504, 2, 3, 3840, 204, 206, 2304, 65531, 256 // mulAB program

copyFrom: .dw 0 // to start copying arr from
copyTo: .dw 0 // to start copying arr to
addr: .dw 0 // jmp to

tmpTableInd: .dw 0 // will loop through table and copy correct addresses to arr
tmpArrInd: .dw 0 // arr[tmpArrInd]

begin:
    MOV copyFrom, &arr // copy start copying from address
    MOV copyTo, *copyFrom // copy start copying to address

    ADD copyFrom, &2 // inc copyFrom
    MOV copyFrom, %A

    MOV addr, *copyFrom // copy jmp to address
    
    SUB copyFrom, &2 // dec copyFrom
    MOV copyFrom, %A

    MOV tmpTableInd, &table // copy table address

    indloop: // this loop takes [even] elements in the table as arr indices and copyies [odd] elements to those cells 
        MOV tmpArrInd, &arr // tmp arrInd = 0

        MUL *tmpTableInd, &2 // take [even] and mul by 2 (making offset out of this)

        ADD tmpArrInd, %A // calc index
        MOV tmpArrInd, %A

        ADD tmpTableInd, &2 // inc tmpTableInd to odd element
        MOV tmpTableInd, %A

        MOV *tmpArrInd, *tmpTableInd // copy odd element to arr[tmpArrInd]

        ADD tmpTableInd, &2 // inc tmpTableInd to even element
        MOV tmpTableInd, %A

    JNE tmpTableInd, &arr, indloop // if not the end of table
    
    loop: // this loop copies modified arr to copyTo+ addresses
        MOV *copyTo, *copyFrom // copy arr cell to copyTo cell

        ADD copyTo, &2 // inc copyTo
        MOV copyTo, %A

        ADD copyFrom, &2 // inc arr
        MOV copyFrom, %A

    JNE copyFrom, &copyFrom, loop // if not the end of arr
    JMP *addr // execute copied program

STOP
.END begin
