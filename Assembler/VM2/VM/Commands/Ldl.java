/*    */ package Commands;
/*    */ 
/*    */ import Start.Startcreation;
/*    */ 
/*    */ public class Ldl extends Compiling.Command {
/*    */   public Ldl(String c, int i, int num) {
/*  7 */     super(c, i, num);
/*    */   }
/*    */   
/*    */   public boolean Execute() {
/* 11 */     int prefix = Startcreation.frame.GetMemoryTableValue(Startcreation.PointerMemory + 1).intValue();
/* 12 */     int arg = Startcreation.frame.GetMemoryTableDoubleValue(Startcreation.PointerMemory + 2);
/* 13 */     int argval = Startcreation.frame.GetMemoryTableDoubleValue(arg);
/* 14 */     int argdog = Startcreation.frame.GetMemoryTableDoubleValue(argval);
/* 15 */     switch (prefix) {
/* 16 */     case 0:  Startcreation.frame.SetRegisterValue(3, argval);
/* 17 */       break;
/* 18 */     case 1:  Startcreation.frame.SetRegisterValue(3, arg);
/* 19 */       break;
/* 20 */     case 2:  Startcreation.frame.SetRegisterValue(3, argdog);
/* 21 */       break;
/* 22 */     default:  Startcreation.PointerMemory -= 2;
/*    */     }
/* 24 */     Startcreation.PointerMemory += 4;
/* 25 */     return true;
/*    */   }
/*    */ }


/* Location:              /home/artem/Downloads/VM@.jar!/Commands/Ldl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */