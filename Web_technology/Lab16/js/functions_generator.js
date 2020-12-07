export class FunctionsGenerator
{
    #lineColor = "#f7f7da";

    constructor()
    {
    }

    generateSin(centerX, centerY)
    {
        let canvas = document.getElementById('canvas_wrap').children[0];
        let context = canvas.getContext('2d');

        let xMult = 10;
        let yMult = 25;
        let alpha = 0;

        context.beginPath();

        for (let x = 0; x <= centerX * 2; x++)
        {
            let angle = (x / (centerX * 2)) * Math.PI * 2; // angle = 0 -> 2π            
            let y = Math.sin(xMult * (angle + alpha)) * yMult + (centerY);

            x == 0 ? context.moveTo(x, y) : context.lineTo(x, y);
        }

        context.lineWidth = 2;
        context.strokeStyle = this.#lineColor;
        context.stroke();
    }

    generateCos(centerX, centerY)
    {
        let canvas = document.getElementById('canvas_wrap').children[0];
        let context = canvas.getContext('2d');

        let xMult = 10;
        let yMult = 25;
        let alpha = 0;

        context.beginPath();

        for (let x = 0; x <= centerX * 2; x++)
        {
            let angle = (x / (centerX * 2)) * Math.PI * 2; // angle = 0 -> 2π            
            let y = Math.cos(xMult * (angle + alpha)) * yMult + (centerY);

            x == 0 ? context.moveTo(x, y) : context.lineTo(x, y);
        }

        context.lineWidth = 2;
        context.strokeStyle = this.#lineColor;
        context.stroke();
    }

    generateTg(centerX, centerY)
    {
        let canvas = document.getElementById('canvas_wrap').children[0];
        let context = canvas.getContext('2d');

        let xMult = 2;
        let yMult = 25;
        let alpha = 0;

        context.beginPath();        

        for (let x = 0; x <= centerX * 2; x++)
        {
            let angle = (x / (centerX * 2)) * Math.PI * 2; // angle = 0 -> 2π
            let y = Math.tan(xMult * (angle + alpha)) * yMult + (centerY);

            let aroundPI2 = (angle + alpha) * xMult / (Math.PI / 2);
            aroundPI2 -= parseInt(aroundPI2)

            let aroundPI = (angle + alpha) * xMult / Math.PI;
            aroundPI -= parseInt(aroundPI)

            if (aroundPI2 <= 0.05 && aroundPI > 0.05)
            {                
                context.moveTo(centerX * 2 - x, y)
                continue;
            }
            
            x == 0 ? context.moveTo(centerX * 2 - x, y) : context.lineTo(centerX * 2 - x, y);
        }

        context.lineWidth = 2;
        context.strokeStyle = this.#lineColor;
        context.stroke();
    }
}