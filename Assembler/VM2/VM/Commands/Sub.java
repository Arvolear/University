/*    */ package Commands;
/*    */ 
/*    */ import Start.Startcreation;
/*    */ 
/*    */ public class Sub
/*    */   extends Compiling.Command {
/*  7 */   public Sub(String c, int i, int num) { super(c, i, num); }
/*    */   
/*    */   public boolean Execute() {
/* 10 */     int prefix = Startcreation.frame.GetMemoryTableValue(Startcreation.PointerMemory + 1).intValue();
/* 11 */     int arg1 = Startcreation.frame.GetMemoryTableDoubleValue(Startcreation.PointerMemory + 2);
/* 12 */     int arg2 = Startcreation.frame.GetMemoryTableDoubleValue(Startcreation.PointerMemory + 4);
/* 13 */     int arg1val = Startcreation.frame.GetMemoryTableDoubleValue(arg1);
/* 14 */     int arg2val = Startcreation.frame.GetMemoryTableDoubleValue(arg2);
/* 15 */     int arg1dog = Startcreation.frame.GetMemoryTableDoubleValue(arg1val);
/* 16 */     int arg2dog = Startcreation.frame.GetMemoryTableDoubleValue(arg2val);
/* 17 */     switch (prefix) {
/* 18 */     case 0:  Startcreation.frame.SetRegisterValue(0, arg1val - arg2val);
/* 19 */       break;
/* 20 */     case 1:  Startcreation.frame.SetRegisterValue(0, arg1 - arg2val);
/* 21 */       break;
/* 22 */     case 2:  Startcreation.frame.SetRegisterValue(0, arg1val - arg2);
/* 23 */       break;
/* 24 */     case 3:  Startcreation.frame.SetRegisterValue(0, arg1 - arg2);
/* 25 */       break;
/* 26 */     case 4:  Startcreation.frame.SetRegisterValue(0, arg1dog - arg2val);
/* 27 */       break;
/* 28 */     case 5:  Startcreation.frame.SetRegisterValue(0, arg1val - arg2dog);
/* 29 */       break;
/* 30 */     case 6:  Startcreation.frame.SetRegisterValue(0, arg1dog - arg2dog);
/* 31 */       break;
/* 32 */     case 7:  Startcreation.frame.SetRegisterValue(0, arg1 - arg2dog);
/* 33 */       break;
/* 34 */     case 8:  Startcreation.frame.SetRegisterValue(0, arg1dog - arg2);
/* 35 */       break;
/* 36 */     default:  Startcreation.PointerMemory -= 4;
/*    */     }
/* 38 */     Startcreation.PointerMemory += 6;
/* 39 */     return true;
/*    */   }
/*    */ }


/* Location:              /home/artem/Downloads/VM@.jar!/Commands/Sub.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */