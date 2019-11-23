/*    */ package Commands;
/*    */ 
/*    */ import Execute.InputFrame;
/*    */ 
/*    */ 
/*    */ public class In
/*    */   extends Compiling.Command
/*    */ {
/*  9 */   public In(String c, int i, int num) { super(c, i, num); }
/*    */   
/*    */   public boolean Execute() {
/* 12 */     if (!Start.Startcreation.input.input.isVisible()) {
/* 13 */       Start.Startcreation.input.input.setVisible(true);
/*    */     }
/* 15 */     return false;
/*    */   }
/*    */ }


/* Location:              /home/artem/Downloads/VM@.jar!/Commands/In.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */