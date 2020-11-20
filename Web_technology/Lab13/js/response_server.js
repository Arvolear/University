let params = (new URL(document.location)).searchParams;

console.log(params.toString());

let all = document.createElement('div');
all.classList.add('all');

let content = document.createElement('div');
content.classList.add('response_content');

let title = document.createElement('div');
title.classList.add('response_title');
title.appendChild(document.createTextNode('Вызывающая страница передала следующее:'));

let table = document.createElement('table');

let row1 = document.createElement('tr');
let cell1 = document.createElement('td');
let cell2 = document.createElement('td');

cell1.appendChild(document.createTextNode('Фамилия:'));
cell2.appendChild(document.createTextNode(params.get('surname')))

row1.appendChild(cell1);
row1.appendChild(cell2);

let row2 = document.createElement('tr');
cell1 = document.createElement('td');
cell2 = document.createElement('td');

cell1.appendChild(document.createTextNode('Должность:'));
cell2.appendChild(document.createTextNode(params.get('position')))

row2.appendChild(cell1);
row2.appendChild(cell2);

table.appendChild(row1);
table.appendChild(row2);

content.appendChild(title);
content.appendChild(table);

all.appendChild(content);

document.body.appendChild(all);