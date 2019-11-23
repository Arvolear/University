/*    */ package Commands;
/*    */ 
/*    */ import Start.Startcreation;
/*    */ 
/*    */ public class Halt extends Compiling.Command
/*    */ {
/*  7 */   public static boolean isHalt = false;
/*    */   
/*  9 */   public Halt(String c, int i, int num) { super(c, i, num); }
/*    */   
/*    */   public boolean Execute() {
/* 12 */     Startcreation.PointerMemory += 2;
/* 13 */     isHalt = true;
/* 14 */     Startcreation.frame.SetmistakeValue(13, 0);
/* 15 */     return false;
/*    */   }
/*    */ }


/* Location:              /home/artem/Downloads/VM@.jar!/Commands/Halt.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */