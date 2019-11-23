/*    */ package Commands;
/*    */ 
/*    */ import Start.Startcreation;
/*    */ 
/*    */ public class Div extends Compiling.Command {
/*    */   public Div(String c, int i, int num) {
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
/*    */     try {
/* 19 */       switch (prefix) {
/*    */       case 0: 
/* 21 */         Startcreation.frame.SetRegisterValue(0, arg1val / arg2val);
/* 22 */         Startcreation.frame.SetRegisterValue(4, arg1val % arg2val);
/*    */         
/* 24 */         break;
/*    */       case 1: 
/* 26 */         Startcreation.frame.SetRegisterValue(0, arg1 / arg2val);
/* 27 */         Startcreation.frame.SetRegisterValue(4, arg1 % arg2val);
/*    */         
/* 29 */         break;
/*    */       case 2: 
/* 31 */         Startcreation.frame.SetRegisterValue(0, arg1val / arg2);
/* 32 */         Startcreation.frame.SetRegisterValue(4, arg1val % arg2);
/*    */         
/* 34 */         break;
/*    */       case 3: 
/* 36 */         Startcreation.frame.SetRegisterValue(0, arg1 / arg2);
/* 37 */         Startcreation.frame.SetRegisterValue(4, arg1 % arg2);
/*    */         
/* 39 */         break;
/*    */       case 4: 
/* 41 */         Startcreation.frame.SetRegisterValue(0, arg1dog / arg2val);
/* 42 */         Startcreation.frame.SetRegisterValue(4, arg1dog % arg2val);
/*    */         
/* 44 */         break;
/*    */       case 5: 
/* 46 */         Startcreation.frame.SetRegisterValue(0, arg1val / arg2dog);
/* 47 */         Startcreation.frame.SetRegisterValue(4, arg1val % arg2dog);
/*    */         
/* 49 */         break;
/*    */       case 6: 
/* 51 */         Startcreation.frame.SetRegisterValue(0, arg1dog / arg2dog);
/* 52 */         Startcreation.frame.SetRegisterValue(4, arg1dog % arg2dog);
/*    */         
/* 54 */         break;
/*    */       case 7: 
/* 56 */         Startcreation.frame.SetRegisterValue(0, arg1 / arg2dog);
/* 57 */         Startcreation.frame.SetRegisterValue(4, arg1 % arg2dog);
/*    */         
/* 59 */         break;
/*    */       case 8: 
/* 61 */         Startcreation.frame.SetRegisterValue(0, arg1dog / arg2);
/* 62 */         Startcreation.frame.SetRegisterValue(4, arg1dog % arg2);
/*    */         
/* 64 */         break;
/* 65 */       default:  Startcreation.PointerMemory -= 4;
/*    */       }
/*    */     }
/*    */     catch (ArithmeticException e) {
/* 69 */       Startcreation.frame.SetmistakeValue(14, 0);
/*    */     }
/* 71 */     Startcreation.PointerMemory += 6;
/* 72 */     return true;
/*    */   }
/*    */ }


/* Location:              /home/artem/Downloads/VM@.jar!/Commands/Div.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */