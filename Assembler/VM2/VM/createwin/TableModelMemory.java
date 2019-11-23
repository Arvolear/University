/*    */ package createwin;
/*    */ 
/*    */ import javax.swing.table.AbstractTableModel;
/*    */ 
/*    */ public class TableModelMemory extends AbstractTableModel { private static final long serialVersionUID = -844351058454666356L;
/*  6 */   private String[] headers = new String[17];
/*  7 */   private Object[][] mem = new Object['က'][17];
/*    */   
/*  9 */   public TableModelMemory() { this.headers[0] = "";
/* 10 */     Integer localInteger1; Integer localInteger2; for (Integer i = Integer.valueOf(0); i.intValue() < 16; localInteger2 = i = Integer.valueOf(i.intValue() + 1)) {
/* 11 */       this.headers[(i.intValue() + 1)] = i.toString();localInteger1 = i;
/*    */     }
/* 13 */     for (int i = 0; i < 4096; i++) {
/* 14 */       this.mem[i][0] = Integer.valueOf(i * 16);
/*    */     }
/* 16 */     for (int i = 1; i < 17; i++) {
/* 17 */       for (int j = 0; j < 4096; j++)
/*    */       {
/* 19 */         this.mem[j][i] = Integer.valueOf(0);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public String getColumnName(int col)
/*    */   {
/* 26 */     return this.headers[col];
/*    */   }
/*    */   
/* 29 */   public Class<?> getColumnClass(int c) { return getValueAt(0, c).getClass(); }
/*    */   
/*    */   public int getColumnCount()
/*    */   {
/* 33 */     return 17;
/*    */   }
/*    */   
/*    */   public int getRowCount()
/*    */   {
/* 38 */     return 4096;
/*    */   }
/*    */   
/*    */   public Object getValueAt(int row, int col)
/*    */   {
/* 43 */     return this.mem[row][col];
/*    */   }
/*    */   
/* 46 */   public boolean isCellEditable(int row, int col) { return false; }
/*    */   
/*    */   public void setValueAt(Object value, int row, int col)
/*    */   {
/* 50 */     this.mem[row][col] = value;
/*    */   }
/*    */ }


/* Location:              /home/artem/Downloads/VM@.jar!/createwin/TableModelMemory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */