package Compiling;

import java.util.ArrayList;
import java.util.List;

public class FirstStage
{
    public static String SetPointerAddress(String line)
    {
        int endSymb = -1;
        int pointerindex = line.lastIndexOf(':');
        if (pointerindex == 0)
            return null;
        if (pointerindex < 0)
            return "";
        for (int j = pointerindex - 1; j > -1; j--) {
            if ((endSymb != -1) || ((line.charAt(j) != ' ') && (line.charAt(j) != '\t')))
            {
                if (endSymb == -1) {
                    endSymb = j;
                }
                char c = line.charAt(j);
                if ((c == '\\') || (c == ':') || (c == '/') || (c == ',') || (c == '.') || (c == '!') || (c == '#') || (c == '@') || (c == '=') || (c == '+') || (c == '-') || (c == '*') || (c == ';') || (c == ' ') || (c == '\t') || (c == '%'))
                    return null;
            }
        }
        String s = new String(line.substring(0, endSymb + 1));
        if ((s.charAt(0) >= '0') && (s.charAt(0) <= '9'))
            return null;
        return s;
    }

    public static boolean IsLegalName(String s) { if ((s.length() == 0) || (s.length() > 255))
        return false;
        for (tableOfPointers pointer : Start.Startcreation.PointersAndVars) {
            if (pointer.GetName().equals(s))
                return false;
        }
        for (int i = 0; i < Start.Startcreation.command.length; i++) {
            if (Start.Startcreation.command[i].getCommandName().equals(s)) {
                return false;
            }
        }
        return true;
    }

    public static boolean getPointers(List<String> lines) {
        for (int k = 0; k < lines.size(); k++) {
            String line = (String)lines.get(k);
            String s = SetPointerAddress(line);
            if (s == null) {
                Start.Startcreation.frame.SetmistakeValue(4, k);
                return false;
            }
            if (s != "")
            {
                if (!IsLegalName(s)) {
                    Start.Startcreation.frame.SetmistakeValue(8, k);
                    return false;
                }
                Start.Startcreation.PointersAndVars.add(new tableOfPointers(s));
            }
        }
        return true;
    }

    public static String DelComments(String code) {
        int len = code.length();
        boolean TodeleteOrNot = true;
        int newCodeLength = 0;
        int j = 0;
        if (code.length() == 0)
            return "";
        for (int i = 0; i < len - 1; i++) {
            if ((code.charAt(i) == '/') && (code.charAt(i + 1) == '/')) {
                TodeleteOrNot = false;
            }
            if (code.charAt(i) == '\n') {
                TodeleteOrNot = true;
            }

            if (TodeleteOrNot == true) {
                newCodeLength++;
            }
        }

        if (TodeleteOrNot == true) {
            newCodeLength++;
        }
        char[] a = new char[newCodeLength];
        TodeleteOrNot = true;
        for (int i = 0; i < len - 1; i++) {
            if ((code.charAt(i) == '/') && (code.charAt(i + 1) == '/')) {
                TodeleteOrNot = false;
            }
            if (code.charAt(i) == '\n') {
                TodeleteOrNot = true;
            }

            if (TodeleteOrNot == true) {
                a[j] = code.charAt(i);
                j++;
            }
        }
        if (TodeleteOrNot == true) {
            a[j] = code.charAt(len - 1);
        }

        String s = new String(a);
        return s;
    }

    public static void formLines(String code, List<String> lines) {
        int currentLength = 0;
        ArrayList<Integer> lineLength = new ArrayList();
        for (int i = 0; i < code.length(); i++)
            if (code.charAt(i) == '\n') {
                lineLength.add(Integer.valueOf(currentLength));
                currentLength = 0;
            }
            else if (i == code.length() - 1) {
                currentLength++;
                lineLength.add(Integer.valueOf(currentLength));
                currentLength = 0;
            }
            else {
                currentLength++;
            }
        int pointer = 0;
        for (Integer j : lineLength) {
            if (pointer == code.length() - 1)
                return;
            if (j.intValue() == 0) {
                pointer++;
                lines.add("");
            }
            else {
                char[] temp = new char[j.intValue()];
                for (int k = 0; k < j.intValue(); pointer++) {
                    temp[k] = code.charAt(pointer);k++;
                }
                pointer++;
                String s = new String(temp);
                String s1 = new String(s.trim());
                lines.add(s1);
            }
        }
    }
}


/* Location:              /home/artem/Downloads/VM@.jar!/Compiling/FirstStage.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */
