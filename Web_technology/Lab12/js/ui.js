import { UIGenerator } from "./ui_generator.js";
import { MatrixManipulator } from "./matrix_manipulator.js";

export class UI
{
    #uiGenerator;
    #matrixManipulator;

    #attachEventListeners(ui)
    {
        for (let i = ui.#uiGenerator.getMinDimension(); i <= ui.#uiGenerator.getMaxDimension(); i++)
        {
            document.getElementById(i + 'x' + i).addEventListener('click', function () 
            {
                ui.#uiGenerator.generateStuff(i);
                ui.#attachEventListeners(ui);
            });
        }

        document.getElementById('equals').addEventListener('click', function () 
        {
            ui.#matrixManipulator.multiply(ui.#uiGenerator.getCurrentMatrixDimension());
        });

        document.getElementById('clear').addEventListener('click', function () 
        {
            ui.#uiGenerator.generateStuff(ui.#uiGenerator.getCurrentMatrixDimension());
            ui.#attachEventListeners(ui);
        });
    }

    constructor()
    {
        this.#uiGenerator = new UIGenerator();
        this.#uiGenerator.generateStuff(2);

        this.#matrixManipulator = new MatrixManipulator();

        this.#attachEventListeners(this);
    }
}