/*    */ package Commands;
/*    */ 
/*    */ import Start.Startcreation;
/*    */ 
/*    */ public class Shr extends Compiling.Command {
/*    */   public Shr(String c, int i, int num) {
/*  7 */     super(c, i, num);
/*    */   }
/*    */   
/*    */   public boolean Execute() {
/* 11 */     int prefix = Startcreation.frame.GetMemoryTableValue(Startcreation.PointerMemory + 1).intValue();
/* 12 */     int arg1 = Startcreation.frame.GetMemoryTableDoubleValue(Startcreation.PointerMemory + 2);
/* 13 */     int arg2 = Startcreation.frame.GetMemoryTableDoubleValue(Startcreation.PointerMemory + 4);
/* 14 */     int arg1val = Startcreation.frame.GetMemoryTableDoubleValue(arg1);
/* 15 */     int arg2val = Startcreation.frame.GetMemoryTableDoubleValue(arg2);
/* 16 */     int arg1dog = Startcreation.frame.GetMemoryTableDoubleValue(arg1val);
/* 17 */     int arg2dog = Startcreation.frame.GetMemoryTableDoubleValue(arg2val);
/* 18 */     switch (prefix) {
/* 19 */     case 0:  Startcreation.frame.SetRegisterValue(0, arg1val >> arg2val);
/* 20 */       break;
/* 21 */     case 1:  Startcreation.frame.SetRegisterValue(0, arg1 >> arg2val);
/* 22 */       break;
/* 23 */     case 2:  Startcreation.frame.SetRegisterValue(0, arg1val >> arg2);
/* 24 */       break;
/* 25 */     case 3:  Startcreation.frame.SetRegisterValue(0, arg1 >> arg2);
/* 26 */       break;
/* 27 */     case 4:  Startcreation.frame.SetRegisterValue(0, arg1dog >> arg2val);
/* 28 */       break;
/* 29 */     case 5:  Startcreation.frame.SetRegisterValue(0, arg1val >> arg2dog);
/* 30 */       break;
/* 31 */     case 6:  Startcreation.frame.SetRegisterValue(0, arg1dog >> arg2dog);
/* 32 */       break;
/* 33 */     case 7:  Startcreation.frame.SetRegisterValue(0, arg1 >> arg2dog);
/* 34 */       break;
/* 35 */     case 8:  Startcreation.frame.SetRegisterValue(0, arg1dog >> arg2);
/* 36 */       break;
/* 37 */     default:  Startcreation.PointerMemory -= 4;
/*    */     }
/* 39 */     Startcreation.PointerMemory += 6;
/* 40 */     return true;
/*    */   }
/*    */ }


/* Location:              /home/artem/Downloads/VM@.jar!/Commands/Shr.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */