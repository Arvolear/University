function genMatrix(name)
{
    let html = '';

    html += '<table>\n';

    for (let i = 0; i < 3; i++)
    {
        html += '<tr>\n';

        for (let j = 0; j < 3; j++)
        {
            html += '<td>\n' +
                '<input type="text" name="' + name + i.toString() + j.toString() + '" value="">\n' +
                '</td>\n';
        }
    }

    html += '</table>\n';

    return html;
}

let html = ''

html += '<div class="table_in">\n';
html += 'Матрица A:\n';
html += '<br>\n'
html += '<br>\n'
html += genMatrix('a');

html += '<br>\n'
html += 'Матрица B:\n';
html += '<br>\n'
html += '<br>\n'
html += genMatrix('b');
html += '</div>\n';

html += '<div class="table_out">\n'
html += '<br>\n'
html += '<input type="button" name="mult" value="Умножение" onclick="multiply(3, 3, 3, 3)">\n'
html += '<br>\n'
html += '<br>\n'

html += 'Результат:\n';
html += '<br>\n'
html += '<br>\n'
html += genMatrix('res');
html += '</div>\n';

document.body.innerHTML = html
