/*    */ package createwin;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import javax.swing.event.TableModelListener;
/*    */ import javax.swing.table.TableModel;
/*    */ 
/*    */ public class TabModelRegisters
/*    */   implements TableModel
/*    */ {
/* 12 */   private Set<TableModelListener> listeners = new HashSet();
/*    */   private List<registers> register;
/*    */   
/* 15 */   public TabModelRegisters(List<registers> reg) { this.register = reg; }
/*    */   
/*    */   public void addTableModelListener(TableModelListener arg0)
/*    */   {
/* 19 */     this.listeners.add(arg0);
/*    */   }
/*    */   
/*    */   public Class<?> getColumnClass(int arg0) {
/* 23 */     return String.class;
/*    */   }
/*    */   
/*    */   public int getColumnCount() {
/* 27 */     return 2;
/*    */   }
/*    */   
/*    */   public String getColumnName(int arg0) {
/* 31 */     return null;
/*    */   }
/*    */   
/*    */   public int getRowCount() {
/* 35 */     return this.register.size();
/*    */   }
/*    */   
/*    */   public Object getValueAt(int rowIndex, int columnIndex)
/*    */   {
/* 40 */     registers mem = (registers)this.register.get(rowIndex);
/* 41 */     switch (columnIndex) {
/*    */     case 0: 
/* 43 */       return mem.Getname();
/*    */     case 1: 
/* 45 */       return mem.Getvalue();
/*    */     }
/* 47 */     return Integer.valueOf(-1);
/*    */   }
/*    */   
/*    */   public boolean isCellEditable(int arg0, int arg1) {
/* 51 */     return false;
/*    */   }
/*    */   
/*    */   public void removeTableModelListener(TableModelListener arg0) {
/* 55 */     this.listeners.remove(arg0);
/*    */   }
/*    */   
/*    */   public void setValueAt(Object aValue, int rowIndex, int columnIndex)
/*    */   {
/* 60 */     registers mem = (registers)this.register.get(rowIndex);
/* 61 */     switch (columnIndex) {
/*    */     case 0: 
/* 63 */       mem.Setname(aValue);
/* 64 */       return;
/*    */     case 1: 
/* 66 */       mem.Setvalue((Integer)aValue);
/* 67 */       return;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/artem/Downloads/VM@.jar!/createwin/TabModelRegisters.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */