/*    */ package Commands;
/*    */ 
/*    */ import Start.Startcreation;
/*    */ 
/*    */ public class Add extends Compiling.Command
/*    */ {
/*    */   public Add(String c, int i, int num)
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
/* 21 */     case 0:  Startcreation.frame.SetRegisterValue(0, arg1val + arg2val);
/* 22 */       break;
/* 23 */     case 1:  Startcreation.frame.SetRegisterValue(0, arg1 + arg2val);
/* 24 */       break;
/* 25 */     case 2:  Startcreation.frame.SetRegisterValue(0, arg1val + arg2);
/* 26 */       break;
/* 27 */     case 3:  Startcreation.frame.SetRegisterValue(0, arg1 + arg2);
/* 28 */       break;
/* 29 */     case 4:  Startcreation.frame.SetRegisterValue(0, arg1dog + arg2val);
/* 30 */       break;
/* 31 */     case 5:  Startcreation.frame.SetRegisterValue(0, arg1val + arg2dog);
/* 32 */       break;
/* 33 */     case 6:  Startcreation.frame.SetRegisterValue(0, arg1dog + arg2dog);
/* 34 */       break;
/* 35 */     case 7:  Startcreation.frame.SetRegisterValue(0, arg1 + arg2dog);
/* 36 */       break;
/* 37 */     case 8:  Startcreation.frame.SetRegisterValue(0, arg1dog + arg2);
/* 38 */       break;
/* 39 */     default:  Startcreation.PointerMemory -= 4;
/*    */     }
/* 41 */     Startcreation.PointerMemory += 6;
/* 42 */     return true;
/*    */   }
/*    */ }


/* Location:              /home/artem/Downloads/VM@.jar!/Commands/Add.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */