/*    */ package Execute;
/*    */ 
/*    */ import Start.Startcreation;
/*    */ 
/*    */ public class MainExecution {
/*    */   public static boolean Execute() {
/*  7 */     Integer command = Startcreation.frame.GetMemoryTableValue(Startcreation.PointerMemory);
/*  8 */     for (int i = 0; i < Startcreation.command.length; i++) {
/*  9 */       if (Startcreation.command[i].GetCommandNum() == command.intValue())
/* 10 */         return Startcreation.command[i].Execute();
/*    */     }
/* 12 */     Startcreation.PointerMemory += 2;
/* 13 */     return true;
/*    */   }
/*    */ }


/* Location:              /home/artem/Downloads/VM@.jar!/Execute/MainExecution.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */