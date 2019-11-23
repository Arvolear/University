/*    */ package Commands;
/*    */ 
/*    */ import Start.Startcreation;
/*    */ 
/*    */ public class Out extends Compiling.Command
/*    */ {
/*    */   public Out(String c, int i, int num)
/*    */   {
/*  9 */     super(c, i, num);
/*    */   }
/*    */   
/*    */   public boolean Execute() {
/* 13 */     int prefix = Startcreation.frame.GetMemoryTableValue(Startcreation.PointerMemory + 1).intValue();
/* 14 */     int arg = Startcreation.frame.GetMemoryTableDoubleValue(Startcreation.PointerMemory + 2);
/* 15 */     int argval = Startcreation.frame.GetMemoryTableDoubleValue(arg);
/* 16 */     System.out.print(argval + " " + arg);
/* 17 */     int argdog = Startcreation.frame.GetMemoryTableDoubleValue(argval);
/* 18 */     switch (prefix) {
/* 19 */     case 0:  Startcreation.frame.OutputVal(argval);
/* 20 */       break;
/* 21 */     case 1:  Startcreation.frame.OutputVal(arg);
/* 22 */       break;
/* 23 */     case 2:  Startcreation.frame.OutputVal(argdog);
/* 24 */       break;
/* 25 */     default:  Startcreation.PointerMemory -= 2;
/*    */     }
/* 27 */     Startcreation.PointerMemory += 4;
/* 28 */     return true;
/*    */   }
/*    */ }


/* Location:              /home/artem/Downloads/VM@.jar!/Commands/Out.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */