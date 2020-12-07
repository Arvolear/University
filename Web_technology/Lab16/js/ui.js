import { FunctionsGenerator } from "./functions_generator.js";
import { UIGenerator } from "./ui_generator.js";

export class UI
{
    #uiGenerator;
    #functionsGenerator;

    #attachEventListeners(ui)
    {
        document.getElementById('sin').addEventListener('click', function () 
        {
            ui.#uiGenerator.clear();
            ui.#functionsGenerator.generateSin(ui.#uiGenerator.getCenterX(), ui.#uiGenerator.getCenterY())
        });

        document.getElementById('cos').addEventListener('click', function () 
        {
            ui.#uiGenerator.clear();
            ui.#functionsGenerator.generateCos(ui.#uiGenerator.getCenterX(), ui.#uiGenerator.getCenterY())
        });

        document.getElementById('tg').addEventListener('click', function () 
        {
            ui.#uiGenerator.clear();
            ui.#functionsGenerator.generateTg(ui.#uiGenerator.getCenterX(), ui.#uiGenerator.getCenterY())
        });
    }

    constructor()
    {
        this.#uiGenerator = new UIGenerator();
        this.#uiGenerator.generateStuff();
        
        this.#functionsGenerator = new FunctionsGenerator();

        this.#attachEventListeners(this);
    }
}