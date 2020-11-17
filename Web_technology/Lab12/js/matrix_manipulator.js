import { Matrix } from "./matrix.js";

export class MatrixManipulator
{
    constructor()
    {        
    }

    #from(name, dimension)
    {
        let res = new Array(dimension);

        for (let i = 0; i < res.length; i++)
        {
            res[i] = new Array(dimension);
        }

        for (let i = 0; i < dimension; i++)
        {
            for (let j = 0; j < dimension; j++)
            {
                let num = document.getElementById(name + i.toString() + j.toString()).value;
                num = (Number(num) == NaN ? 0 : Number(num));

                res[i][j] = num;
            }
        }

        return res;
    }

    #to(name, matrix)
    {
        for (let i = 0; i < matrix.getNumRows(); i++)
        {
            for (let j = 0; j < matrix.getNumCols(); j++)
            {
                document.getElementById(name + i.toString() + j.toString()).value = matrix.getInnerMatrix()[i][j];                
            }
        }
    }

    multiply(dimension)
    {
        let first = new Matrix(this.#from('first', dimension));
        let second = new Matrix(this.#from('second', dimension));

        let result = first.multiply(second);        

        this.#to('result', result);
    }
}