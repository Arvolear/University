export class UIGenerator
{
    #currentMatrixDimension;

    #MINDIM = 2;
    #MAXDIM = 5;

    #generateDimensionButtons()
    {
        let dimensions = document.createElement('div');
        dimensions.classList.add('dimensions');

        for (let i = this.#MINDIM; i <= this.#MAXDIM; i++)
        {
            let input = document.createElement('input');
            input.classList.add('blue_button');
            input.setAttribute('type', 'button');
            input.setAttribute('id', i + 'x' + i);
            input.setAttribute('value', i + 'x' + i);

            dimensions.appendChild(input);
        }

        let input = document.createElement('input');
        input.classList.add('red_button');
        input.setAttribute('type', 'button');
        input.setAttribute('id', 'clear');
        input.setAttribute('value', 'clear');

        dimensions.appendChild(input);

        document.getElementById('content').appendChild(dimensions);
    }

    #genMatrix(name)
    {
        let table = document.createElement('table');

        for (let i = 0; i < this.#currentMatrixDimension; i++)
        {
            let tr = document.createElement('tr');

            for (let j = 0; j < this.#currentMatrixDimension; j++)
            {
                let td = document.createElement('td');

                let input = document.createElement('input');
                input.setAttribute('type', 'text');
                input.setAttribute('id', name + i.toString() + j.toString());
                input.setAttribute('value', '');

                td.appendChild(input);
                tr.appendChild(td);
            }

            table.appendChild(tr);
        }

        return table;
    }

    #generateInputMatricesAndSign()
    {
        let inputMatrix = document.createElement('div');
        inputMatrix.classList.add('input_matrix');

        inputMatrix.appendChild(this.#genMatrix('first'));
        document.getElementById('content').appendChild(inputMatrix);

        let mult = document.createElement('div');
        mult.classList.add('mult');
        mult.appendChild(document.createTextNode("×"));
        document.getElementById('content').appendChild(mult);

        inputMatrix = document.createElement('div');
        inputMatrix.classList.add('input_matrix');

        inputMatrix.appendChild(this.#genMatrix('second'));
        document.getElementById('content').appendChild(inputMatrix);
    }

    #generateEqualsButton()
    {
        let equals = document.createElement('div');
        equals.classList.add('equals');

        let input = document.createElement('input');
        input.classList.add('green_button');
        input.setAttribute('type', 'button');
        input.setAttribute('id', 'equals');
        input.setAttribute('value', '=');

        equals.appendChild(input);

        document.getElementById('content').appendChild(equals);
    }

    #generateOutputMatrix()
    {
        let outputMatrix = document.createElement('div');
        outputMatrix.classList.add('output_matrix');

        outputMatrix.appendChild(this.#genMatrix('result'));

        document.getElementById('content').appendChild(outputMatrix);
    }

    #clear()
    {
        if (document.getElementById('content') != null)
        {
            document.getElementById('content').remove();
        }

        let content = document.createElement('div');
        content.setAttribute('id', 'content');
        content.classList.add('content');

        document.body.appendChild(content);
    }

    constructor()
    {
    }

    generateStuff(matrixDimension)
    {
        this.#currentMatrixDimension = matrixDimension;

        this.#clear()

        this.#generateDimensionButtons();
        this.#generateInputMatricesAndSign();
        this.#generateEqualsButton();
        this.#generateOutputMatrix();
    }

    getCurrentMatrixDimension()
    {
        return this.#currentMatrixDimension;
    }

    getMinDimension()
    {
        return this.#MINDIM;
    }

    getMaxDimension()
    {
        return this.#MAXDIM;
    }
}