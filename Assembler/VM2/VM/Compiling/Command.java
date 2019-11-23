/*    */ package Compiling;
/*    */ 
/*    */ public class Command {
/*    */   private String command;
/*    */   private int NumberOfArgs;
/*    */   private int commandNum;
/*    */   
/*    */   public Command(String c, int i, int num) {
/*  9 */     this.command = c;
/* 10 */     this.NumberOfArgs = i;
/* 11 */     this.commandNum = num;
/*    */   }
/*    */   
/*    */   public String getCommandName() {
/* 15 */     return this.command;
/*    */   }
/*    */   
/* 18 */   public int GetArgNum() { return this.NumberOfArgs; }
/*    */   
/*    */   public int GetCommandNum() {
/* 21 */     return this.commandNum;
/*    */   }
/*    */   
/* 24 */   public boolean Execute() { return true; }
/*    */ }


/* Location:              /home/artem/Downloads/VM@.jar!/Compiling/Command.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */