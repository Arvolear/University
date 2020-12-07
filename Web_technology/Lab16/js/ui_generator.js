export class UIGenerator
{   
    #chartWidth = 1000;
    #chartHeight = 600;    

    #axisColor = "#eeeeee";

    #generateChartButtons()
    {
        let buttons = document.createElement('div');
        buttons.classList.add('buttons');

        let sin = document.createElement('input');
        sin.classList.add('blue_button');
        sin.setAttribute('type', 'button');
        sin.setAttribute('id', 'sin');
        sin.setAttribute('value', 'sin');

        let cos = document.createElement('input');
        cos.classList.add('blue_button');
        cos.setAttribute('type', 'button');
        cos.setAttribute('id', 'cos');
        cos.setAttribute('value', 'cos');

        let tg = document.createElement('input');
        tg.classList.add('blue_button');
        tg.setAttribute('type', 'button');
        tg.setAttribute('id', 'tg');
        tg.setAttribute('value', 'tg');

        buttons.appendChild(sin);
        buttons.appendChild(cos);
        buttons.appendChild(tg);

        document.getElementById('content').appendChild(buttons);
    } 

    #drawLine(context, x1, y1, x2, y2)
    {
        context.beginPath()

        context.moveTo(x1, y1);
        context.lineTo(x2, y2);
        context.lineWidth = 2;
        context.strokeStyle = this.#axisColor;
        
        context.stroke();
    }

    #generateCanvasChart()
    {
        let canvasDiv = document.createElement('div');
        canvasDiv.setAttribute('id', 'canvas_wrap');

        let canvas = document.createElement('canvas');
        canvas.setAttribute('width', this.#chartWidth); 
        canvas.setAttribute('height', this.#chartHeight);        

        canvasDiv.appendChild(canvas);

        document.getElementById('content').appendChild(canvasDiv);

        this.clear();
    }

    constructor()
    {
        let content = document.createElement('div');
        content.setAttribute('id', 'content');
        content.classList.add('content');

        document.body.appendChild(content);    
    }

    clear()
    {
        var canvas = document.getElementById('canvas_wrap').children[0];
        
        let context = canvas.getContext('2d');
        context.clearRect(0, 0, this.#chartWidth, this.#chartHeight);

        this.#drawLine(context, 0, this.getCenterY(), this.#chartWidth, this.getCenterY());
        this.#drawLine(context, this.getCenterX(), 0, this.getCenterX(), this.#chartHeight);
    }

    generateStuff()
    {
        this.#generateChartButtons();    
        this.#generateCanvasChart();
    }

    getCenterX()
    {
        return this.#chartWidth / 2;
    }

    getCenterY()
    {
        return this.#chartHeight / 2;
    }
}