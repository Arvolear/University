function callServer()
{
    let surname = document.getElementById('surname').value;
    let position = document.getElementById('position').value;

    if (surname == "")
    {
        alert("Surname is empty");
        return;
    }

    let request = new XMLHttpRequest();
    let url = "/called.html?surname=" + surname + "&position=" + position;

    request.onreadystatechange = function ()
    {
        if (request.readyState == 4)
        {
            window.location.href = request.responseURL;            
        }
    };

    request.open("GET", url, true);
    request.send();
}