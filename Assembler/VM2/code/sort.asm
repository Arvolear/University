// SELECTION SORT

arr: .dw 2, 4, 3, 1, 5 // inital array to sort
max: .dw 0 // pointer to current max element

begin:

    i: .dw 0 
    outer:
        j: .dw 0

        MOV j, i // copying j position
        MOV max, i // copying current max position

        inner:
            JG *j, *max, toMax // if cur elem > max, goto toMax 
            JL *j, *max, nope // if cur elem < max, skip
            JE *j, *max, nope // if cur elem == max, skip
            
            toMax:
                MOV max, j // copy max elem
            nope: // skip

            ADD j, &2 // inc. j to the next element
            MOV j, %A
        JNE j, &max, inner // if not the end of array

        // swapping 2 elements swap(*i, *max)
        tmp: .dw 0 
        MOV tmp, *i
        MOV *i, *max
        MOV *max, tmp

        ADD i, &2 // inc. i to the next element
        MOV i, %A
    JNE i, &max, outer

    MOV i, &0 // reset i

    print:
        OUT *i // print *i
        ADD i, &2 // inc. i
        MOV i, %A
    JNE i, &max, print

stop
.end begin
