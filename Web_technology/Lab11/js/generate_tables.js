function genMatrix(name)
{
    let table = document.createElement('table');

    for (let i = 0; i < 3; i++)
    {
        let tr = document.createElement('tr');        

        for (let j = 0; j < 3; j++)
        {
            let td = document.createElement('td');

            let input = document.createElement('input');
            input.setAttribute('type', 'text');
            input.setAttribute('name', name + i.toString() + j.toString());
            input.setAttribute('value', '');

            td.appendChild(input);
            tr.appendChild(td);
        }

        table.appendChild(tr);
    }

    return table;
}

function createButton()
{
    let input = document.createElement('input');
    input.setAttribute('type', 'button');
    input.setAttribute('name', 'mult');
    input.setAttribute('value', 'Умножение');
    input.setAttribute('onclick', 'multiply(3, 3, 3, 3)');

    return input;
}

let tableIn = document.createElement('div');
tableIn.classList.add('table_in');

tableIn.appendChild(document.createTextNode('Матрица A:'));
tableIn.appendChild(document.createElement('br'));
tableIn.appendChild(document.createElement('br'));
tableIn.appendChild(genMatrix('a'));

tableIn.appendChild(document.createElement('br'));
tableIn.appendChild(document.createTextNode('Матрица B:'));
tableIn.appendChild(document.createElement('br'));
tableIn.appendChild(document.createElement('br'));
tableIn.appendChild(genMatrix('b'));

let tableOut = document.createElement('div');
tableOut.classList.add('table_out');

tableOut.appendChild(document.createElement('br'));
tableOut.appendChild(createButton());
tableOut.appendChild(document.createElement('br'));
tableOut.appendChild(document.createElement('br'));

tableOut.appendChild(document.createTextNode('Результат:'));
tableOut.appendChild(document.createElement('br'));
tableOut.appendChild(document.createElement('br'));
tableOut.appendChild(genMatrix('res'));

document.body.appendChild(tableIn);
document.body.appendChild(tableOut);
