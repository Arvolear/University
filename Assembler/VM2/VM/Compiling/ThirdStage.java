/*    */ package Compiling;
/*    */ 
/*    */ import Start.Startcreation;
/*    */ 
/*    */ public class ThirdStage
/*    */ {
/*    */   static int lineCounter;
/*    */   
/*    */   public static boolean MainThirdStage(java.util.List<String> lines) {
/* 10 */     for (lineCounter = 0; lineCounter < lines.size(); lineCounter += 1) {
/* 11 */       String line = (String)lines.get(lineCounter);
/* 12 */       String Command = SecondStage.GetCommand(line);
/* 13 */       if (Command != null)
/*    */       {
/* 15 */         if (!AnalizeCommand(Command, line))
/* 16 */           return false; }
/*    */     }
/* 18 */     return true;
/*    */   }
/*    */   
/* 21 */   public static boolean AnalizeCommand(String command, String line) { int CommandNum = -1;
/* 22 */     for (int j = 0; j < Startcreation.command.length; j++) {
/* 23 */       if (Startcreation.command[j].getCommandName().equals(command)) {
/* 24 */         CommandNum = j;
/* 25 */         break;
/*    */       }
/*    */     }
/* 28 */     if (CommandNum == -1) {
/* 29 */       Startcreation.frame.SetmistakeValue(6, lineCounter);
/* 30 */       return false;
/*    */     }
/* 32 */     if (command.equals(".START")) {
/* 33 */       int res = GetArgs.GetOrgArg(line);
/* 34 */       if (res == -1) {
/* 35 */         Startcreation.frame.SetmistakeValue(1, lineCounter);
/* 36 */         return false;
/*    */       }
/*    */       
/*    */     }
/* 40 */     else if (command.equals(".DW")) {
/* 41 */       boolean res = GetArgs.GetDwordArg(line);
/* 42 */       if (!res) {
/* 43 */         Startcreation.frame.SetmistakeValue(10, lineCounter);
/* 44 */         return false;
/*    */       }
/*    */     }
/* 47 */     else if (command.equals(".END")) {
/* 48 */       GetArgs.GetEndArg(line);
/*    */     }
/* 50 */     else if (command.equals("MOV")) {
/* 51 */       boolean res = GetArgs.AnalizeMOVArg(line, Startcreation.command[CommandNum]);
/* 52 */       if (!res) {
/* 53 */         Startcreation.frame.SetmistakeValue(10, lineCounter);
/* 54 */         return false;
/*    */       }
/*    */     }
/* 57 */     else if ((command.equals("STA")) || (command.equals("STB")) || (command.equals("STC")) || (command.equals("STL")) || (command.equals("STD")) || (command.equals("CALL"))) {
/* 58 */       boolean res = GetArgs.GetSTAArgs(line, Startcreation.command[CommandNum]);
/* 59 */       if (!res) {
/* 60 */         Startcreation.frame.SetmistakeValue(10, lineCounter);
/* 61 */         return false;
/*    */       }
/*    */     }
/* 64 */     else if (Startcreation.command[CommandNum].GetArgNum() == 0) {
/* 65 */       Startcreation.frame.SetMemoryTableValue(MainCompile.GetPointAtMemory(), Startcreation.command[CommandNum].GetCommandNum());
/* 66 */       Startcreation.frame.SetMemoryTableValue(MainCompile.GetPointAtMemory() + 1, 0);
/* 67 */       MainCompile.SetPointAtMemory(MainCompile.GetPointAtMemory() + 2);
/*    */     }
/* 69 */     else if (Startcreation.command[CommandNum].GetArgNum() == 1) {
/* 70 */       boolean res = GetArgs.GetOneArg(line, Startcreation.command[CommandNum]);
/* 71 */       if (!res) {
/* 72 */         Startcreation.frame.SetmistakeValue(10, lineCounter);
/* 73 */         return false;
/*    */       }
/*    */     }
/* 76 */     else if (Startcreation.command[CommandNum].GetArgNum() == 2) {
/* 77 */       boolean res = GetArgs.GetTwoArg(line, Startcreation.command[CommandNum]);
/* 78 */       if (!res) {
/* 79 */         Startcreation.frame.SetmistakeValue(10, lineCounter);
/* 80 */         return false;
/*    */       }
/*    */     }
/* 83 */     else if (Startcreation.command[CommandNum].GetArgNum() == 3) {
/* 84 */       boolean res = GetArgs.GetThreeArg(line, Startcreation.command[CommandNum]);
/* 85 */       if (!res) {
/* 86 */         Startcreation.frame.SetmistakeValue(10, lineCounter);
/* 87 */         return false;
/*    */       }
/*    */     }
/*    */     else {
/* 91 */       MainCompile.SetPointAtMemory(MainCompile.GetPointAtMemory() + Startcreation.command[CommandNum].GetArgNum() * 2 + 2);
/*    */     }
/* 93 */     return true;
/*    */   }
/*    */ }


/* Location:              /home/artem/Downloads/VM@.jar!/Compiling/ThirdStage.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */