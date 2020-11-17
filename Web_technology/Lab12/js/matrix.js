export class Matrix
{
    #matrix;

    constructor(matrix)
    {   
        this.#matrix = matrix;
    }

    multiply(other)
    {
        if (!(other instanceof Matrix))
        {
            throw new Error("Not a matrix");
        }

        if (this.getNumCols() != other.getNumRows())
        {
            throw new Error("Wrong matrices dimensions");            
        }

        let res = new Array(this.getNumRows());

        for (let i = 0; i < res.length; i++)
        {
            res[i] = new Array(other.getNumCols());
        }        

        for (let i = 0; i < this.getNumRows(); i++)
        {
            for (let j = 0; j < other.getNumCols(); j++)
            {   
                res[i][j] = 0;
                
                for (let k = 0; k < this.getNumCols(); k++)
                {                    
                    res[i][j] += this.#matrix[i][k] * other.#matrix[k][j];
                }
            }
        }

        return new Matrix(res);
    }

    getNumRows()
    {
        return this.#matrix.length
    }

    getNumCols()
    {
        return this.#matrix[0].length
    }

    getInnerMatrix()
    {
        return this.#matrix;
    }
}