function multiply(aRows, aCols, bRows, bCols)
{
    if (aCols != bRows)
    {
        alert("Wrong matrices dimensions");
        return;
    }

    for (let i = 0; i < aRows; i++)
    {
        for (let j = 0; j < bCols; j++)
        {
            document.getElementsByName('res' + i.toString() + j.toString())[0].value = "";

            for (let k = 0; k < aCols; k++)
            {
                let aElem = document.getElementsByName('a' + i.toString() + k.toString())[0];
                let bElem = document.getElementsByName('b' + k.toString() + j.toString())[0];
                let resElem = document.getElementsByName('res' + i.toString() + j.toString())[0];

                let aNum = Number(aElem.value) == NaN ? 0 : Number(aElem.value);
                let bNum = Number(bElem.value) == NaN ? 0 : Number(bElem.value);
                let resNum = Number(resElem.value) == NaN ? 0 : Number(resElem.value);

                resElem.value = resNum + aNum * bNum;
            }
        }
    }
}