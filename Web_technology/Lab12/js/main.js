import { UI } from './ui.js'

document.onreadystatechange = function()
{
    if (document.readyState == "complete")
    {        
        let ui = new UI();
    }
}