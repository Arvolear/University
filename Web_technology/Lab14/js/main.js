function readFile()
{
    let x = document.getElementById("x_file").value;
    let y = document.getElementById("y_file").value;

    if (x == "" || y == "")
    {
        alert("x or y is empty");
        return;
    }

    let file = document.getElementById('file').files[0];

    let reader = new FileReader();

    reader.onload = function() 
    {
        drawLines(reader.result);
    };

    reader.readAsText(file);
}

function drawLines(fileString)
{
    let initX = document.getElementById("x_file").value;
    let initY = document.getElementById("y_file").value;

    var coords = fileString.split('\n');

    let canvas = document.getElementById("canvas");
    let ctx = canvas.getContext("2d");

    ctx.beginPath();
    ctx.moveTo(initX, initY);

    for (let i = 0; i < coords.length; i++)
    {
        let curCoords = coords[i].split(';');

        ctx.lineTo(curCoords[0], curCoords[1]);        
    }

    ctx.stroke();
}

function drawText()
{
    let canvas = document.getElementById("canvas");
    let ctx = canvas.getContext("2d");

    let x = document.getElementById("x_text").value;
    let y = document.getElementById("y_text").value;
    let text = document.getElementById("text").value;

    ctx.font = "20px Arial";
    ctx.fillText(text, x, y);   
}   