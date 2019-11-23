/*    */ package Commands;
/*    */ 
/*    */ import Compiling.Command;
/*    */ 
/*    */ 
/*    */ public class Nop
/*    */   extends Command
/*    */ {
/*  9 */   public Nop(String c, int i, int num) { super(c, i, num); }
/*    */   
/*    */   public boolean Execute() {
/* 12 */     Start.Startcreation.PointerMemory += 2;
/* 13 */     return true;
/*    */   }
/*    */ }


/* Location:              /home/artem/Downloads/VM@.jar!/Commands/Nop.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */