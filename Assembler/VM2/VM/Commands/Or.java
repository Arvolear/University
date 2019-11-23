/*    */ package Commands;
/*    */ 
/*    */ import Start.Startcreation;
/*    */ 
/*    */ 
/*    */ public class Or
/*    */   extends Compiling.Command
/*    */ {
/*  9 */   public Or(String c, int i, int num) { super(c, i, num); }
/*    */   
/*    */   public boolean Execute() {
/* 12 */     int prefix = Startcreation.frame.GetMemoryTableValue(Startcreation.PointerMemory + 1).intValue();
/* 13 */     int arg1 = Startcreation.frame.GetMemoryTableDoubleValue(Startcreation.PointerMemory + 2);
/* 14 */     int arg2 = Startcreation.frame.GetMemoryTableDoubleValue(Startcreation.PointerMemory + 4);
/* 15 */     int arg1val = Startcreation.frame.GetMemoryTableDoubleValue(arg1);
/* 16 */     int arg2val = Startcreation.frame.GetMemoryTableDoubleValue(arg2);
/* 17 */     int arg1dog = Startcreation.frame.GetMemoryTableDoubleValue(arg1val);
/* 18 */     int arg2dog = Startcreation.frame.GetMemoryTableDoubleValue(arg2val);
/* 19 */     switch (prefix) {
/* 20 */     case 0:  Startcreation.frame.SetRegisterValue(0, arg1val | arg2val);
/* 21 */       break;
/* 22 */     case 1:  Startcreation.frame.SetRegisterValue(0, arg1 | arg2val);
/* 23 */       break;
/* 24 */     case 2:  Startcreation.frame.SetRegisterValue(0, arg1val | arg2);
/* 25 */       break;
/* 26 */     case 3:  Startcreation.frame.SetRegisterValue(0, arg1 | arg2);
/* 27 */       break;
/* 28 */     case 4:  Startcreation.frame.SetRegisterValue(0, arg1dog | arg2val);
/* 29 */       break;
/* 30 */     case 5:  Startcreation.frame.SetRegisterValue(0, arg1val | arg2dog);
/* 31 */       break;
/* 32 */     case 6:  Startcreation.frame.SetRegisterValue(0, arg1dog | arg2dog);
/* 33 */       break;
/* 34 */     case 7:  Startcreation.frame.SetRegisterValue(0, arg1 | arg2dog);
/* 35 */       break;
/* 36 */     case 8:  Startcreation.frame.SetRegisterValue(0, arg1dog | arg2);
/* 37 */       break;
/* 38 */     default:  Startcreation.PointerMemory -= 4;
/*    */     }
/* 40 */     Startcreation.PointerMemory += 6;
/* 41 */     return true;
/*    */   }
/*    */ }


/* Location:              /home/artem/Downloads/VM@.jar!/Commands/Or.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */