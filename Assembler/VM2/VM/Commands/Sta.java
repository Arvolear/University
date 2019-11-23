/*    */ package Commands;
/*    */ 
/*    */ import Start.Startcreation;
/*    */ 
/*    */ public class Sta
/*    */   extends Compiling.Command {
/*  7 */   public Sta(String c, int i, int num) { super(c, i, num); }
/*    */   
/*    */   public boolean Execute() {
/* 10 */     int prefix = Startcreation.frame.GetMemoryTableValue(Startcreation.PointerMemory + 1).intValue();
/* 11 */     int arg = Startcreation.frame.GetMemoryTableDoubleValue(Startcreation.PointerMemory + 2);
/* 12 */     int argval = Startcreation.frame.GetMemoryTableDoubleValue(arg);
/* 13 */     switch (prefix) {
/*    */     case 0: 
/* 15 */       Startcreation.frame.SetMemoryTableDoubleValue(arg, Startcreation.frame.GetRegisterValue(0));
/*    */       
/* 17 */       break;
/* 18 */     case 2:  Startcreation.frame.SetMemoryTableDoubleValue(argval, Startcreation.frame.GetRegisterValue(0));
/* 19 */       break;
/* 20 */     default:  Startcreation.PointerMemory -= 2;
/*    */     }
/* 22 */     Startcreation.PointerMemory += 4;
/* 23 */     return true;
/*    */   }
/*    */ }


/* Location:              /home/artem/Downloads/VM@.jar!/Commands/Sta.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */