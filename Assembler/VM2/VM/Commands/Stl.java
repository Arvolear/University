/*    */ package Commands;
/*    */ 
/*    */ import Start.Startcreation;
/*    */ 
/*    */ public class Stl extends Compiling.Command {
/*    */   public Stl(String c, int i, int num) {
/*  7 */     super(c, i, num);
/*    */   }
/*    */   
/*    */   public boolean Execute() {
/* 11 */     int prefix = Startcreation.frame.GetMemoryTableValue(Startcreation.PointerMemory + 1).intValue();
/* 12 */     int arg = Startcreation.frame.GetMemoryTableDoubleValue(Startcreation.PointerMemory + 2);
/* 13 */     int argval = Startcreation.frame.GetMemoryTableDoubleValue(arg);
/* 14 */     switch (prefix) {
/*    */     case 0: 
/* 16 */       System.out.print("argval=" + argval);
/* 17 */       Startcreation.frame.SetMemoryTableDoubleValue(arg, Startcreation.frame.GetRegisterValue(3));
/*    */       
/* 19 */       break;
/* 20 */     case 2:  Startcreation.frame.SetMemoryTableDoubleValue(argval, Startcreation.frame.GetRegisterValue(3));
/* 21 */       break;
/* 22 */     default:  Startcreation.PointerMemory -= 2;
/*    */     }
/* 24 */     Startcreation.PointerMemory += 4;
/* 25 */     return true;
/*    */   }
/*    */ }


/* Location:              /home/artem/Downloads/VM@.jar!/Commands/Stl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */