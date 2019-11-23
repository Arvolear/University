/*    */ package Commands;
/*    */ 
/*    */ import Start.Startcreation;
/*    */ 
/*    */ 
/*    */ public class Lda
/*    */   extends Compiling.Command
/*    */ {
/*  9 */   public Lda(String c, int i, int num) { super(c, i, num); }
/*    */   
/*    */   public boolean Execute() {
/* 12 */     int prefix = Startcreation.frame.GetMemoryTableValue(Startcreation.PointerMemory + 1).intValue();
/* 13 */     int arg = Startcreation.frame.GetMemoryTableDoubleValue(Startcreation.PointerMemory + 2);
/* 14 */     int argval = Startcreation.frame.GetMemoryTableDoubleValue(arg);
/* 15 */     int argdog = Startcreation.frame.GetMemoryTableDoubleValue(argval);
/* 16 */     switch (prefix) {
/* 17 */     case 0:  Startcreation.frame.SetRegisterValue(0, argval);
/* 18 */       break;
/* 19 */     case 1:  Startcreation.frame.SetRegisterValue(0, arg);
/* 20 */       break;
/* 21 */     case 2:  Startcreation.frame.SetRegisterValue(0, argdog);
/* 22 */       break;
/* 23 */     default:  Startcreation.PointerMemory -= 2;
/*    */     }
/* 25 */     Startcreation.PointerMemory += 4;
/* 26 */     return true;
/*    */   }
/*    */ }


/* Location:              /home/artem/Downloads/VM@.jar!/Commands/Lda.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */