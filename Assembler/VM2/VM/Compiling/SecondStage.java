package Compiling;

import Start.Startcreation;

public class SecondStage
{
    static int lineCounter;

    public static boolean MainSecondStage(java.util.List<String> lines) {
        MainCompile.GetPointAtMemory();
        for (lineCounter = 0; lineCounter < lines.size(); lineCounter += 1) {
            String line = (String)lines.get(lineCounter);
            FillPointers(line);
            String Command = GetCommand(line);
            if (Command != null)
            {
                if (!IsCommand(Command, line))
                    return false; }
        }
        return true;
    }

    public static void FillPointers(String line) { String pointer = FirstStage.SetPointerAddress(line);
        if (pointer == null)
            return;
        if (pointer == "")
            return;
        for (tableOfPointers point : Startcreation.PointersAndVars) {
            if (point.GetName().equals(pointer)) {
                point.SetAddress(MainCompile.GetPointAtMemory());
            }
        }
    }

    public static String GetCommand(String line) {
        int FirstLetter = -1;int lastLetter = -1;

        int startPointSearch = line.indexOf(':') + 1;
        if (startPointSearch == line.length())
            return null;
        for (int i = startPointSearch; i < line.length(); i++)
            if (((line.charAt(i) == ' ') || (line.charAt(i) == '\t')) && (FirstLetter == -1)) {
                startPointSearch++;
            }
            else {
                if (FirstLetter == -1) {
                    FirstLetter = i;
                }
                if ((FirstLetter != -1) && ((line.charAt(i) == ' ') || (line.charAt(i) == '\t'))) {
                    lastLetter = i;
                    break; } }
        String command;
        if (lastLetter != -1) {
            command = line.substring(FirstLetter, lastLetter);
        } else {
            command = line.substring(FirstLetter);
        }
        return command;
    }

    public static boolean IsCommand(String command, String line) { int CommandNum = -1;
        for (int j = 0; j < Startcreation.command.length; j++) {
            if (Startcreation.command[j].getCommandName().equals(command)) {
                CommandNum = j;
                break;
            }
        }
        if (CommandNum == -1) {
            Startcreation.frame.SetmistakeValue(6, lineCounter);
            return false;
        }
        if (command.equals(".START")) {
            int res = GetArgs.GetOrgArg(line);
            if (res == -1) {
                Startcreation.frame.SetmistakeValue(1, lineCounter);
                return false;
            }
        }
        else if (command.equals(".END")) {
            boolean res = GetArgs.GetEndArg(line);
            if (!res) {
                Startcreation.frame.SetmistakeValue(9, lineCounter);
                return false;
            }
        }
        else if (command.equals(".DW")) {
            boolean res = GetArgs.GetDwordArg(line);
            if (!res) {
                Startcreation.frame.SetmistakeValue(10, lineCounter);
                return false;
            }
        }
        else if (Startcreation.command[CommandNum].GetArgNum() == 0) {
            Startcreation.frame.SetMemoryTableValue(MainCompile.GetPointAtMemory(), Startcreation.command[CommandNum].GetCommandNum());
            Startcreation.frame.SetMemoryTableValue(MainCompile.GetPointAtMemory() + 1, 0);
            MainCompile.SetPointAtMemory(MainCompile.GetPointAtMemory() + 2);







        }
        else
        {







            MainCompile.SetPointAtMemory(MainCompile.GetPointAtMemory() + Startcreation.command[CommandNum].GetArgNum() * 2 + 2);
        }
        return true;
    }
}


/* Location:              /home/artem/Downloads/VM@.jar!/Compiling/SecondStage.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */
