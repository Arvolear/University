/*    */ package Commands;
/*    */ 
/*    */ import Start.Startcreation;
/*    */ 
/*    */ public class Mul extends Compiling.Command
/*    */ {
/*    */   public Mul(String c, int i, int num)
/*    */   {
/*  9 */     super(c, i, num);
/*    */   }
/*    */   
/*    */   public boolean Execute() {
/* 13 */     int prefix = Startcreation.frame.GetMemoryTableValue(Startcreation.PointerMemory + 1).intValue();
/* 14 */     int arg1 = Startcreation.frame.GetMemoryTableDoubleValue(Startcreation.PointerMemory + 2);
/* 15 */     int arg2 = Startcreation.frame.GetMemoryTableDoubleValue(Startcreation.PointerMemory + 4);
/* 16 */     int arg1val = Startcreation.frame.GetMemoryTableDoubleValue(arg1);
/* 17 */     int arg2val = Startcreation.frame.GetMemoryTableDoubleValue(arg2);
/* 18 */     int arg1dog = Startcreation.frame.GetMemoryTableDoubleValue(arg1val);
/* 19 */     int arg2dog = Startcreation.frame.GetMemoryTableDoubleValue(arg2val);
/* 20 */     switch (prefix) {
/*    */     case 0: 
/* 22 */       Startcreation.frame.SetRegisterValue(0, arg1val * arg2val);
/* 23 */       Startcreation.frame.SetRegisterValue(4, arg1val * arg2val / 65536);
/*    */       
/* 25 */       break;
/*    */     case 1: 
/* 27 */       Startcreation.frame.SetRegisterValue(0, arg1 * arg2val);
/* 28 */       Startcreation.frame.SetRegisterValue(4, arg1 * arg2val / 65536);
/*    */       
/* 30 */       break;
/*    */     case 2: 
/* 32 */       Startcreation.frame.SetRegisterValue(0, arg1val * arg2);
/* 33 */       Startcreation.frame.SetRegisterValue(4, arg1val * arg2 / 65536);
/*    */       
/* 35 */       break;
/*    */     case 3: 
/* 37 */       Startcreation.frame.SetRegisterValue(0, arg1 * arg2);
/* 38 */       Startcreation.frame.SetRegisterValue(4, arg1 * arg2 / 65536);
/*    */       
/* 40 */       break;
/*    */     case 4: 
/* 42 */       Startcreation.frame.SetRegisterValue(0, arg1dog * arg2val);
/* 43 */       Startcreation.frame.SetRegisterValue(4, arg1dog * arg2val / 65536);
/*    */       
/* 45 */       break;
/*    */     case 5: 
/* 47 */       Startcreation.frame.SetRegisterValue(0, arg1val * arg2dog);
/* 48 */       Startcreation.frame.SetRegisterValue(4, arg1val * arg2dog / 65536);
/*    */       
/* 50 */       break;
/*    */     case 6: 
/* 52 */       Startcreation.frame.SetRegisterValue(0, arg1dog * arg2dog);
/* 53 */       Startcreation.frame.SetRegisterValue(4, arg1dog * arg2dog / 65536);
/*    */       
/* 55 */       break;
/*    */     case 7: 
/* 57 */       Startcreation.frame.SetRegisterValue(0, arg1 * arg2dog);
/* 58 */       Startcreation.frame.SetRegisterValue(4, arg1 * arg2dog / 65536);
/*    */       
/* 60 */       break;
/*    */     case 8: 
/* 62 */       Startcreation.frame.SetRegisterValue(0, arg1dog * arg2);
/* 63 */       Startcreation.frame.SetRegisterValue(4, arg1dog * arg2 / 65536);
/*    */       
/* 65 */       break;
/* 66 */     default:  Startcreation.PointerMemory -= 4;
/*    */     }
/* 68 */     Startcreation.PointerMemory += 6;
/* 69 */     return true;
/*    */   }
/*    */ }


/* Location:              /home/artem/Downloads/VM@.jar!/Commands/Mul.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */