export class FunctionsGenerator
{    
    #ns = "http://www.w3.org/2000/svg";    

    constructor()
    {        
    }    

    generateSin(centerX, centerY)
    {
        let svg = document.getElementById('svg_wrap').children[0];
                
        let xMult = 15;
        let yMult = 25;
        let alpha = 0; 

        let pathArr = [];

        for (let x = 0; x <= centerX * 2; x++)
        {
            let angle = (x / (centerX * 2)) * Math.PI * 2; // angle = 0 -> 2π            
            let y = Math.sin(xMult * (angle + alpha)) * yMult + (centerY);
            
            pathArr.push((x == 0 ? 'M' : 'L') + x + ',' + y);
        }

        let path = document.createElementNS(this.#ns, 'path');
        path.classList.add('chart_line');
        path.setAttribute('d', pathArr.join(' '));        

        svg.appendChild(path);
    }

    generateCos(centerX, centerY)
    {
        let svg = document.getElementById('svg_wrap').children[0];

        let xMult = 15;
        let yMult = 25;
        let alpha = 0;

        let pathArr = [];

        for (let x = 0; x <= centerX * 2; x++)
        {
            let angle = (x / (centerX * 2)) * Math.PI * 2; // angle = 0 -> 2π            
            let y = Math.cos(xMult * (angle + alpha)) * yMult + (centerY);

            pathArr.push((x == 0 ? 'M' : 'L') + x + ',' + y);
        }

        let path = document.createElementNS(this.#ns, 'path');
        path.classList.add('chart_line');
        path.setAttribute('d', pathArr.join(' '));

        svg.appendChild(path);
    }

    generateTg(centerX, centerY)
    {        
        let svg = document.getElementById('svg_wrap').children[0];

        let xMult = 3;
        let yMult = 25;
        let alpha = 0;

        let pathArr = [];

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
                pathArr.push('M' + (centerX * 2 - x) + ',' + y);
                continue;
            }            

            pathArr.push((x == 0 ? 'M' : 'L') + (centerX * 2 - x) + ',' + y);
        }

        let path = document.createElementNS(this.#ns, 'path');
        path.classList.add('chart_line');
        path.setAttribute('d', pathArr.join(' '));

        svg.appendChild(path);
    }
}