/*    */ package Commands;
/*    */ 
/*    */ import Start.Startcreation;
/*    */ 
/*    */ public class Call
/*    */   extends Compiling.Command {
/*  7 */   public Call(String c, int i, int num) { super(c, i, num); }
/*    */   
/*    */   public boolean Execute() {
/* 10 */     int prefix = Startcreation.frame.GetMemoryTableValue(Startcreation.PointerMemory + 1).intValue();
/* 11 */     int arg = Startcreation.frame.GetMemoryTableDoubleValue(Startcreation.PointerMemory + 2);
/* 12 */     switch (prefix) {
/*    */     case 0: case 1: case 2: 
/* 14 */       Startcreation.frame.SetRegisterValue(3, Startcreation.PointerMemory + 4);
/* 15 */       Startcreation.PointerMemory = arg;
/*    */       
/* 17 */       break;
/* 18 */     default:  Startcreation.PointerMemory += 4;
/*    */     }
/* 20 */     return true;
/*    */   }
/*    */ }


/* Location:              /home/artem/Downloads/VM@.jar!/Commands/Call.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */