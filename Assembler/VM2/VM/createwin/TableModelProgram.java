/*    */ package createwin;
/*    */ 
/*    */ import javax.swing.table.AbstractTableModel;
/*    */ 
/*    */ public class TableModelProgram extends AbstractTableModel { private static final long serialVersionUID = 951207324976731996L;
/*  6 */   String[] commandHeader = { "Address", "Command" };
/*  7 */   Object[][] commands = new Object[' '][2];
/*    */   
/*  9 */   public TableModelProgram() { for (int i = 0; i < 8192; i++) {
/* 10 */       this.commands[i][0] = Integer.valueOf(i * 2);
/* 11 */       this.commands[i][1] = "NOP";
/*    */     }
/*    */   }
/*    */   
/*    */   public String getColumnName(int col) {
/* 16 */     return this.commandHeader[col];
/*    */   }
/*    */   
/* 19 */   public Class<?> getColumnClass(int c) { return getValueAt(0, c).getClass(); }
/*    */   
/*    */   public int getColumnCount()
/*    */   {
/* 23 */     return 2;
/*    */   }
/*    */   
/*    */   public int getRowCount()
/*    */   {
/* 28 */     return 8192;
/*    */   }
/*    */   
/*    */   public Object getValueAt(int row, int col)
/*    */   {
/* 33 */     return this.commands[row][col];
/*    */   }
/*    */   
/* 36 */   public boolean isCellEditable(int row, int col) { return false; }
/*    */   
/*    */   public void setValueAt(Object value, int row, int col)
/*    */   {
/* 40 */     this.commands[row][col] = value;
/*    */   }
/*    */ }


/* Location:              /home/artem/Downloads/VM@.jar!/createwin/TableModelProgram.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */