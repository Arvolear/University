export class UIGenerator
{   
    #chartWidth = 1000;
    #chartHeight = 600;
    #ns = "http://www.w3.org/2000/svg";

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

    #getLine(x1, y1, x2, y2)
    {
        let line = document.createElementNS(this.#ns, 'line');
        line.classList.add('axis_line');

        line.setAttribute('x1', x1);
        line.setAttribute('y1', y1);
        line.setAttribute('x2', x2);
        line.setAttribute('y2', y2);    

        return line;
    }

    #generateSVGChart()
    {
        let svgDiv = document.createElement('div');
        svgDiv.setAttribute('id', 'svg_wrap');

        let svg = document.createElementNS(this.#ns, 'svg');
        svg.setAttribute('width', this.#chartWidth); 
        svg.setAttribute('height', this.#chartHeight);        

        svgDiv.appendChild(svg);

        document.getElementById('content').appendChild(svgDiv);

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
        var svg = document.getElementById('svg_wrap').children[0];
        svg.innerHTML = '';

        let lineX = this.#getLine(0, this.getCenterY(), this.#chartWidth, this.getCenterY());
        let lineY = this.#getLine(this.getCenterX(), 0, this.getCenterX(), this.#chartHeight);

        svg.appendChild(lineX);
        svg.appendChild(lineY);
    }

    generateStuff()
    {
        this.#generateChartButtons();    
        this.#generateSVGChart();
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