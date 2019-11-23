/*    */ package Commands;
/*    */ 
/*    */ import Start.Startcreation;
/*    */ 
/*    */ public class Jl extends Compiling.Command {
/*    */   public Jl(String c, int i, int num) {
/*  7 */     super(c, i, num);
/*    */   }
/*    */   
/*    */   public boolean Execute() {
/* 11 */     int prefix = Startcreation.frame.GetMemoryTableValue(Startcreation.PointerMemory + 1).intValue();
/* 12 */     int arg1 = Startcreation.frame.GetMemoryTableDoubleValue(Startcreation.PointerMemory + 2);
/* 13 */     int arg2 = Startcreation.frame.GetMemoryTableDoubleValue(Startcreation.PointerMemory + 4);
/* 14 */     int arg3 = Startcreation.frame.GetMemoryTableDoubleValue(Startcreation.PointerMemory + 6);
/* 15 */     int arg1val = Startcreation.frame.GetMemoryTableDoubleValue(arg1);
/* 16 */     int arg2val = Startcreation.frame.GetMemoryTableDoubleValue(arg2);
/* 17 */     int arg1dog = Startcreation.frame.GetMemoryTableDoubleValue(arg1val);
/* 18 */     int arg2dog = Startcreation.frame.GetMemoryTableDoubleValue(arg2val);
/* 19 */     switch (prefix) {
/* 20 */     case 0:  if (arg1val < arg2val) {
/* 21 */         Startcreation.PointerMemory = arg3;
/* 22 */         return true;
/*    */       }
/*    */       break;
/* 25 */     case 1:  if (arg1 < arg2val) {
/* 26 */         Startcreation.PointerMemory = arg3;
/* 27 */         return true;
/*    */       }
/*    */       break;
/* 30 */     case 2:  if (arg1val < arg2) {
/* 31 */         Startcreation.PointerMemory = arg3;
/* 32 */         return true;
/*    */       }
/*    */       break;
/* 35 */     case 3:  if (arg1 < arg2) {
/* 36 */         Startcreation.PointerMemory = arg3;
/* 37 */         return true;
/*    */       }
/*    */       break;
/* 40 */     case 4:  if (arg1dog < arg2val) {
/* 41 */         Startcreation.PointerMemory = arg3;
/*    */         
/* 43 */         return true;
/*    */       }
/*    */       break;
/* 46 */     case 5:  if (arg1val < arg2dog) {
/* 47 */         Startcreation.PointerMemory = arg3;
/* 48 */         return true;
/*    */       }
/*    */       break;
/* 51 */     case 6:  if (arg1dog < arg2dog) {
/* 52 */         Startcreation.PointerMemory = arg3;
/* 53 */         return true;
/*    */       }
/*    */       break;
/* 56 */     case 7:  if (arg1 < arg2dog) {
/* 57 */         Startcreation.PointerMemory = arg3;
/* 58 */         return true;
/*    */       }
/*    */       break;
/* 61 */     case 8:  if (arg1dog < arg2) {
/* 62 */         Startcreation.PointerMemory = arg3;
/* 63 */         return true;
/*    */       }
/*    */       break;
/* 66 */     default:  Startcreation.PointerMemory -= 6;
/*    */     }
/* 68 */     Startcreation.PointerMemory += 8;
/* 69 */     return true;
/*    */   }
/*    */ }


/* Location:              /home/artem/Downloads/VM@.jar!/Commands/Jl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */