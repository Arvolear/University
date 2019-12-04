#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>

#define SIZE 70 // size of the memory 
#define EXEC_STEPS 100 // number of simulations

int memory[SIZE]; // actual memory

/*
 * simple memory output function
 */
void output() 
{
    printf("\nMemory:\n");

    for (int i = 0; i < SIZE; i++) // loop through the memory
    {
        printf("%d ", memory[i]);
    }

    printf("\n");
}

/* 
 * function to find ZERO memory block of (length) length
 */
int findEmptySpot(int length)
{
    int counter = 0; // length counter
    int res = -1; // index definer

    for (int i = 0; i < SIZE; i++) // loop through memory
    {
        if (memory[i] != 0) // if cell is occupied 
        { 
            res = -1; // reset index
            counter = 0; // reset counter
            continue;
        }
        else // if cell is empty
        {
            if (counter == 0) // if first ZERO cell in a row
            {
                res = i; // save index
            }

            counter++; // inc. counter
        }
    
        if (counter == length) // if counter == length
        {
            return res; // return saved index
        }
    }

    return -1; // end of memory - no ZERO blocks
}

/* 
 * function to generate the process
 */
void genProcess()
{
    int id = rand() % 9 + 1; // gen process id [0, 9]
    int length = rand() % 9 + 3; // gen process size [3, 11]
    
    int spot = findEmptySpot(length); // get fine empty block

    if (spot == -1) // if no blocks exist
    {
        printf("\nCan't gen the process, not enough memory!\n");
        return;
    }

    printf("\nGenerating the process\n");
    for (int i = spot; i < spot + length; i++) // fill in the memory with process's id
    {
        usleep(30000); // sleep 30 ms
        memory[i] = id; // insert the process
    }

    output(); // output new memory state
}

/*
 * function to release the process
 */
void releaseProcess()
{
    int index = rand() % (SIZE - 1); // get random memory cell
    int id = memory[index]; // get process id in that cell

    if (id == 0) // if id == 0, cell is not occupied by the process
    {
        return;
    }

    printf("\nReleasing the process\n");
    for (int i = 0; i < SIZE; i++) // loop through memory
    {
        if (memory[i] == id) // if cell == process's id
        {
            usleep(30000); // sleep 30 ms
            memory[i] = 0; // empty the memory cell
        }
    }

    output(); // output new memory state
}

/*
 * defragemtation function
 * 
 * Simpe bubble sort to push all ZEROs at the
 * memory's end
 */
void defragmentize()
{
    printf("\nDefragmentize\n");
    for (int i = 0; i < SIZE; i++) 
    {
        for (int j = 0; j < SIZE - i - 1; j++)
        {
            if (memory[j] == 0) // if cell == 0
            {
                /* swap(curr, next) */
                int tmp = memory[j + 1];
                memory[j + 1] = memory[j];
                memory[j] = tmp;
            }
        }
    }

    output();
}

int main()
{
    srand(time(NULL)); // init random

    for (int i = 0; i < EXEC_STEPS; i++)
    {
        int task = rand() % 3; // get random task

        switch(task)
        {
            case 0: // gen the process
                {
                    genProcess();
                    break;
                }
            case 1: // release the process
                {
                    releaseProcess();
                    break;
                }
            case 2: // defragmentize
                {
                    defragmentize();
                    break;
                }
        }
    }

    return 0;
}
