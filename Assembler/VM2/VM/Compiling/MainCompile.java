/*    */ package Compiling;
/*    */ 
/*    */ import Start.Startcreation;
/*    */ import createwin.FrameInit;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class MainCompile
/*    */ {
/* 10 */   private static int PointAtmemory = 0;
/* 11 */   int PointAtProgramTable = 0;
/* 12 */   public List<String> lines = new ArrayList();
/*    */   
/*    */   public boolean Compile(FrameInit frame, Command[] commands, ArrayList<tableOfPointers> PointersAndVars) {
/* 15 */     String newCode = FirstStage.DelComments(Startcreation.frame.GetCode()).toUpperCase();
/* 16 */     FirstStage.formLines(newCode, this.lines);
/* 17 */     boolean mistakeLine = FirstStage.getPointers(this.lines);
/* 18 */     if (!mistakeLine) {
/* 19 */       return false;
/*    */     }
/* 21 */     boolean b = SecondStage.MainSecondStage(this.lines);
/* 22 */     if (!b)
/* 23 */       return false;
/* 24 */     if (Startcreation.PointerMemory == -1) {
/* 25 */       frame.SetmistakeValue(12, 0);
/* 26 */       return false;
/*    */     }
/* 28 */     PointAtmemory = 0;
/* 29 */     boolean third = ThirdStage.MainThirdStage(this.lines);
/* 30 */     if (!third)
/* 31 */       return false;
/* 32 */     ForthStage.MainForthStage();
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 38 */     return true;
/*    */   }
/*    */   
/* 41 */   public static void SetPointAtMemory(int point) { PointAtmemory = point; }
/*    */   
/*    */   public static int GetPointAtMemory() {
/* 44 */     return PointAtmemory;
/*    */   }
/*    */ }


/* Location:              /home/artem/Downloads/VM@.jar!/Compiling/MainCompile.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */