/*    */ package Commands;
/*    */ 
/*    */ import Compiling.Command;
/*    */ 
/*    */ public class Ret
/*    */   extends Command {
/*  7 */   public Ret(String c, int i, int num) { super(c, i, num); }
/*    */   
/*    */   public boolean Execute() {
/* 10 */     Start.Startcreation.PointerMemory = Start.Startcreation.frame.GetRegisterValue(3);
/* 11 */     return true;
/*    */   }
/*    */ }


/* Location:              /home/artem/Downloads/VM@.jar!/Commands/Ret.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */