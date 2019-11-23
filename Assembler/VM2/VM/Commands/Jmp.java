/*    */ package Commands;
/*    */ 
/*    */ import Start.Startcreation;
/*    */ 
/*    */ public class Jmp extends Compiling.Command {
/*    */   public Jmp(String c, int i, int num) {
/*  7 */     super(c, i, num);
/*    */   }
/*    */   
/*    */   public boolean Execute() {
/* 11 */     int prefix = Startcreation.frame.GetMemoryTableValue(Startcreation.PointerMemory + 1).intValue();
/* 12 */     int arg = Startcreation.frame.GetMemoryTableDoubleValue(Startcreation.PointerMemory + 2);
/* 13 */     int argval = Startcreation.frame.GetMemoryTableDoubleValue(arg);
/* 14 */     switch (prefix) {
/* 15 */     case 0:  Startcreation.PointerMemory = arg;
/* 16 */       return true;
/* 17 */     case 1:  Startcreation.PointerMemory = argval;
/* 18 */       return true;
/*    */     case 2: 
/* 20 */       Startcreation.PointerMemory = argval;
/* 21 */       return true;
/*    */     }
/* 23 */     Startcreation.PointerMemory += 2;
/*    */     
/* 25 */     return true;
/*    */   }
/*    */ }


/* Location:              /home/artem/Downloads/VM@.jar!/Commands/Jmp.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */