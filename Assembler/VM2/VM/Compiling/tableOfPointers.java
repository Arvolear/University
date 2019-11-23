/*    */ package Compiling;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class tableOfPointers { private String PointerName;
/*    */   private int address;
/*    */   
/*  8 */   public tableOfPointers(String Name) { this.PointerName = Name;
/*  9 */     this.address = -1;
/*    */   }
/*    */   
/*    */   public tableOfPointers(String Name, int address) {
/* 13 */     this.PointerName = Name;
/* 14 */     this.address = address;
/*    */   }
/*    */   
/*    */   public void SetAddress(int add) {
/* 18 */     this.address = add;
/*    */   }
/*    */   
/* 21 */   public String GetName() { return this.PointerName; }
/*    */   
/*    */   public int GetAddress() {
/* 24 */     return this.address;
/*    */   }
/*    */   
/* 27 */   public void Print() { System.out.print(this.PointerName + this.address); }
/*    */ }


/* Location:              /home/artem/Downloads/VM@.jar!/Compiling/tableOfPointers.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */