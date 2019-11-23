/*    */ package Commands;
/*    */ 
/*    */ import Compiling.Command;
/*    */ import Start.Startcreation;
/*    */ import createwin.FrameInit;
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Mov
/*    */   extends Command
/*    */ {
/*    */   public Mov(String c, int i, int num)
/*    */   {
/* 33 */     super(c, i, num);
/*    */   }
/*    */   
/*    */   public boolean Execute()
/*    */   {
/* 38 */     int prefix = Startcreation.frame.GetMemoryTableValue(Startcreation.PointerMemory + 1).intValue();
/*    */     
/*    */ 
/* 41 */     int addressOfFirstArg = Startcreation.frame.GetMemoryTableDoubleValue(Startcreation.PointerMemory + 2);
/* 42 */     int addressOfSecondArg = Startcreation.frame.GetMemoryTableDoubleValue(Startcreation.PointerMemory + 4);
/*    */     
/*    */ 
/* 45 */     int valueOnFirstAddress = Startcreation.frame.GetMemoryTableDoubleValue(addressOfFirstArg);
/* 46 */     int valueOnSecondAddress = Startcreation.frame.GetMemoryTableDoubleValue(addressOfSecondArg);
/*    */     
/*    */ 
/* 49 */     int valueForFirstAddressPointer = Startcreation.frame.GetMemoryTableDoubleValue(valueOnFirstAddress);
/* 50 */     int valueForSecondAddressPointer = Startcreation.frame.GetMemoryTableDoubleValue(valueOnSecondAddress);
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 56 */     int arg1 = Startcreation.frame.GetMemoryTableDoubleValue(Startcreation.PointerMemory + 2);
/* 57 */     int arg2 = Startcreation.frame.GetMemoryTableDoubleValue(Startcreation.PointerMemory + 4);
/*    */     
/* 59 */     int arg1val = Startcreation.frame.GetMemoryTableDoubleValue(arg1);
/* 60 */     int arg2val = Startcreation.frame.GetMemoryTableDoubleValue(arg2);
/*    */     
/* 62 */     int arg1point = Startcreation.frame.GetMemoryTableDoubleValue(arg1val);
/* 63 */     int arg2point = Startcreation.frame.GetMemoryTableDoubleValue(arg2val);
/*    */     
/* 65 */     System.out.println("prefix" + Integer.toString(prefix));
/*    */     
/*    */ 
/*    */ 
/* 69 */     switch (prefix) {
/*    */     case 0: 
/*    */     case 1: 
/* 72 */       Startcreation.frame.SetMemoryTableDoubleValue(addressOfFirstArg, valueOnSecondAddress);
/* 73 */       break;
/*    */     case 2: 
/*    */     case 3: 
/* 76 */       Startcreation.frame.SetMemoryTableDoubleValue(addressOfFirstArg, addressOfSecondArg);
/* 77 */       break;
/*    */     case 4: 
/* 79 */       Startcreation.frame.SetMemoryTableDoubleValue(valueOnFirstAddress, valueOnSecondAddress);
/*    */       
/* 81 */       break;
/*    */     case 5: 
/* 83 */       Startcreation.frame.SetMemoryTableDoubleValue(addressOfFirstArg, valueForSecondAddressPointer);
/* 84 */       break;
/* 85 */     case 6:  Startcreation.frame.SetMemoryTableDoubleValue(valueOnFirstAddress, valueForSecondAddressPointer);
/* 86 */       break;
/* 87 */     case 7:  Startcreation.frame.SetMemoryTableDoubleValue(addressOfFirstArg, valueForSecondAddressPointer);
/* 88 */       break;
/* 89 */     case 8:  Startcreation.frame.SetMemoryTableDoubleValue(valueOnFirstAddress, addressOfSecondArg);
/*    */       
/* 91 */       break;
/* 92 */     default:  Startcreation.PointerMemory -= 4;
/*    */     }
/* 94 */     Startcreation.PointerMemory += 6;
/* 95 */     return true;
/*    */   }
/*    */ }


/* Location:              /home/artem/Downloads/VM@.jar!/Commands/Mov.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */