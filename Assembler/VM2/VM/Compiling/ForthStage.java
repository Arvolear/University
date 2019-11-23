package Compiling;

import Start.Startcreation;

public class ForthStage {
    public static void MainForthStage() {
        int CodePointer = Startcreation.PointerMemory;
        int line = 0;
        int k = 0;
        while (k < CodePointer) {
            Startcreation.frame.SetProgramValue(line, "NOP", k);
            k += 2;
            line++;
        }
        for (int i = line; i < 8192; i++) {
            if ((CodePointer < 0) || (CodePointer > 65527))
                return;
            int commandName = Startcreation.frame.GetMemoryTableValue(CodePointer).intValue();
            if ((commandName == 0) || (commandName > Startcreation.command.length - 4)) {
                Startcreation.frame.SetProgramValue(i, "NOP", CodePointer);
                CodePointer += 2;
            }
            else {
                int commandPrefix = Startcreation.frame.GetMemoryTableValue(CodePointer + 1).intValue();
                int commandArg1 = Startcreation.frame.GetMemoryTableDoubleValue(CodePointer + 2);
                int commandArg2 = Startcreation.frame.GetMemoryTableDoubleValue(CodePointer + 4);
                int commandArg3 = Startcreation.frame.GetMemoryTableDoubleValue(CodePointer + 6);
                for (int j = 0; j < Startcreation.command.length - 3; j++) {
                    if (Startcreation.command[j].GetCommandNum() == commandName) {
                        if (Startcreation.command[j].GetArgNum() == 0) {
                            if (commandPrefix == 0)
                            {
                                Startcreation.frame.SetProgramValue(i, Startcreation.command[j].getCommandName(), CodePointer);
                                CodePointer += 2;
                                break;
                            }
                        } else if (Startcreation.command[j].GetArgNum() == 1) 
                        {
                            String Value;
                            if (commandPrefix == 0) 
                            {
                                Value = Startcreation.command[j].getCommandName() + " " + commandArg1; 
                            } 
                            else 
                            { 
                                if (commandPrefix == 1) 
                                {
                                    Value = Startcreation.command[j].getCommandName() + " &" + commandArg1; 
                                } 
                                else 
                                { 
                                    if (commandPrefix == 2) 
                                    {
                                        Value = Startcreation.command[j].getCommandName() + " *" + commandArg1;
                                    } 
                                    else 
                                    {
                                        CodePointer += 2;
                                        continue; 
                                    } 
                                } 
                            }

                            Startcreation.frame.SetProgramValue(i, Value, CodePointer);
                            CodePointer += 4;
                        }
                        else if ((Startcreation.command[j].GetArgNum() == 2) || (Startcreation.command[j].GetArgNum() == 3)) {
                            String Value;
                            switch (commandPrefix) {
                                case 0:  Value = Startcreation.command[j].getCommandName() + " " + commandArg1 + " " + commandArg2; break;
                                case 1:  Value = Startcreation.command[j].getCommandName() + " &" + commandArg1 + " " + commandArg2; break;
                                case 2:  Value = Startcreation.command[j].getCommandName() + " " + commandArg1 + " &" + commandArg2; break;
                                case 3:  Value = Startcreation.command[j].getCommandName() + " &" + commandArg1 + " &" + commandArg2; break;
                                case 4:  Value = Startcreation.command[j].getCommandName() + " *" + commandArg1 + " " + commandArg2; break;
                                case 5:  Value = Startcreation.command[j].getCommandName() + " " + commandArg1 + " *" + commandArg2; break;
                                case 6:  Value = Startcreation.command[j].getCommandName() + " *" + commandArg1 + " *" + commandArg2; break;
                                case 7:  Value = Startcreation.command[j].getCommandName() + " &" + commandArg1 + " *" + commandArg2; break;
                                case 8:  Value = Startcreation.command[j].getCommandName() + " *" + commandArg1 + " &" + commandArg2; break;
                                default: 
                                         Value = "";
                                         CodePointer += 2;
                                         break;
                            }

                            Startcreation.frame.SetProgramValue(i, Value, CodePointer);
                            CodePointer += 6;
                            if ((Startcreation.command[j].GetArgNum() == 3) && (commandPrefix > -1) && (commandPrefix < 9)) {
                                String Value3 = Value + " " + commandArg3;
                                CodePointer -= 6;
                                Startcreation.frame.SetProgramValue(i, Value3, CodePointer);
                                CodePointer += 8;
                            }
                        }
                    }
                }
            }
        }
    }
}


/* Location:              /home/artem/Downloads/VM@.jar!/Compiling/ForthStage.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */
