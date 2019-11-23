/*    */ package Commands;
/*    */ 
/*    */ import Start.Startcreation;
/*    */ 
/*    */ public class Stb
/*    */   extends Compiling.Command {
/*  7 */   public Stb(String c, int i, int num) { super(c, i, num); }
/*    */   
/*    */   public boolean Execute() {
/* 10 */     int prefix = Startcreation.frame.GetMemoryTableValue(Startcreation.PointerMemory + 1).intValue();
/* 11 */     int arg = Startcreation.frame.GetMemoryTableDoubleValue(Startcreation.PointerMemory + 2);
/* 12 */     int argval = Startcreation.frame.GetMemoryTableDoubleValue(arg);
/* 13 */     switch (prefix) {
/*    */     case 0: 
/* 15 */       System.out.print("argval=" + argval);
/* 16 */       Startcreation.frame.SetMemoryTableDoubleValue(arg, Startcreation.frame.GetRegisterValue(1));
/*    */       
/* 18 */       break;
/* 19 */     case 2:  Startcreation.frame.SetMemoryTableDoubleValue(argval, Startcreation.frame.GetRegisterValue(1));
/* 20 */       break;
/* 21 */     default:  Startcreation.PointerMemory -= 2;
/*    */     }
/* 23 */     Startcreation.PointerMemory += 4;
/* 24 */     return true;
/*    */   }
/*    */ }


/* Location:              /home/artem/Downloads/VM@.jar!/Commands/Stb.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */