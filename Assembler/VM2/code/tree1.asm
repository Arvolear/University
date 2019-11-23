//      L --- S --- H-0    76 --- 83 --- 72-0
//      |     |     |      |      |      |
//      D-0   T-0   L-0    68-0   84-0   76-0
//      |     |     |      |      |      |
//      A-B   A-B   T-0    65-66  65-66  84-0
//      | |   | |   |      |  |   |  |   |
//      0 0   0 0   0      0  0   0  0   0
//
// Each node in the tree consists of 3 elements:
// 1 = ASCII code of the represented letter
// 2 = address of the sibling node
// 3 = address of the child node
tree: .dw 76, 24, 6,  68, -1, 12,  65, 18, -1,  66, -1, -1,  83, 48, 30,  84, -1, 36,  65, 42, -1,  66, -1, -1,  72, -1, 54,  76, -1, 60,  84, -1, -1

// input command in ASCII representation
input: .dw 76, 68, 65

// initial pointers
treeAddr: .dw 0
inputAddr: .dw 0

// if (ans == 0) => still validating
// if (ans == 1) => valid command
// if (ans == -1) => invalid command
ans: .dw 0

// moves treePointer to the sibling with ans validation
toSib:
    ADD treeAddr, &2 // get sibling address
    MOV treeAddr, %A
    
    je *treeAddr, &-1, bad // if sibling address == -1, invalid command was entered 

    MOV treeAddr, *treeAddr // else take sibling ASCII value
    ret
    
    bad:
        MOV ans, &-1 // mark the command is invalid
ret

// moves treePointer to the son with ans validation
toSon:
    ADD treeAddr, &4 // get son address
    MOV treeAddr, %A

    je *treeAddr, &-1, good // if son address == -1, valid command was entered

    MOV treeAddr, *treeAddr // else take son ASCII value
    
    ADD inputAddr, &2 // move input pointer to the next letter
    MOV inputAddr, %A
    ret
    
    good:
        MOV ans, &1 // mark the command is valid
ret

begin:
    MOV inputAddr, &input // take address of the input command
  
    // loop while ans == 0
    loop:
        je *inputAddr, *treeAddr, callSon // if tree element ASCII == input element ASCII, move to the son
        jne *inputAddr, *treeAddr, callSib // if tree element ASCII != input element ASCII, move to the sibling

        callSon: // move to the son
            call toSon
            je ans, &0, loop // if not validated yet, loop
            jne ans, &0, finish // if validated, finish
           
        callSib: // move to the sibling
            call toSib
            je ans, &0, loop // if not validated yet, loop 
            jne ans, &0, finish // if validated, finish

    finish:
        out ans // print ans
STOP
.END begin
