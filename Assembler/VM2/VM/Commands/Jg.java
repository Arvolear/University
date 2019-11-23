/*    */ package Commands;
/*    */ 
/*    */ import Start.Startcreation;
/*    */ 
/*    */ public class Jg
/*    */   extends Compiling.Command {
/*  7 */   public Jg(String c, int i, int num) { super(c, i, num); }
/*    */   
/*    */   public boolean Execute() {
/* 10 */     int prefix = Startcreation.frame.GetMemoryTableValue(Startcreation.PointerMemory + 1).intValue();
/* 11 */     int arg1 = Startcreation.frame.GetMemoryTableDoubleValue(Startcreation.PointerMemory + 2);
/* 12 */     int arg2 = Startcreation.frame.GetMemoryTableDoubleValue(Startcreation.PointerMemory + 4);
/* 13 */     int arg3 = Startcreation.frame.GetMemoryTableDoubleValue(Startcreation.PointerMemory + 6);
/* 14 */     int arg1val = Startcreation.frame.GetMemoryTableDoubleValue(arg1);
/* 15 */     int arg2val = Startcreation.frame.GetMemoryTableDoubleValue(arg2);
/* 16 */     int arg1dog = Startcreation.frame.GetMemoryTableDoubleValue(arg1val);
/* 17 */     int arg2dog = Startcreation.frame.GetMemoryTableDoubleValue(arg2val);
/* 18 */     switch (prefix) {
/* 19 */     case 0:  if (arg1val > arg2val) {
/* 20 */         Startcreation.PointerMemory = arg3;
/* 21 */         return true;
/*    */       }
/*    */       break;
/* 24 */     case 1:  if (arg1 > arg2val) {
/* 25 */         Startcreation.PointerMemory = arg3;
/* 26 */         return true;
/*    */       }
/*    */       break;
/* 29 */     case 2:  if (arg1val > arg2) {
/* 30 */         Startcreation.PointerMemory = arg3;
/* 31 */         return true;
/*    */       }
/*    */       break;
/* 34 */     case 3:  if (arg1 > arg2) {
/* 35 */         Startcreation.PointerMemory = arg3;
/* 36 */         return true;
/*    */       }
/*    */       break;
/* 39 */     case 4:  if (arg1dog > arg2val) {
/* 40 */         Startcreation.PointerMemory = arg3;
/*    */         
/* 42 */         return true;
/*    */       }
/*    */       break;
/* 45 */     case 5:  if (arg1val > arg2dog) {
/* 46 */         Startcreation.PointerMemory = arg3;
/* 47 */         return true;
/*    */       }
/*    */       break;
/* 50 */     case 6:  if (arg1dog > arg2dog) {
/* 51 */         Startcreation.PointerMemory = arg3;
/* 52 */         return true;
/*    */       }
/*    */       break;
/* 55 */     case 7:  if (arg1 > arg2dog) {
/* 56 */         Startcreation.PointerMemory = arg3;
/* 57 */         return true;
/*    */       }
/*    */       break;
/* 60 */     case 8:  if (arg1dog > arg2) {
/* 61 */         Startcreation.PointerMemory = arg3;
/* 62 */         return true;
/*    */       }
/*    */       break;
/* 65 */     default:  Startcreation.PointerMemory -= 6;
/*    */     }
/* 67 */     Startcreation.PointerMemory += 8;
/* 68 */     return true;
/*    */   }
/*    */ }


/* Location:              /home/artem/Downloads/VM@.jar!/Commands/Jg.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */