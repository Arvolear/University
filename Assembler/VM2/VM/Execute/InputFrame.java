/*    */ package Execute;
/*    */ 
/*    */ import Start.Startcreation;
/*    */ import createwin.FrameInit;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ public class InputFrame
/*    */ {
/*    */   public javax.swing.JTextField value;
/*    */   public JFrame input;
/*    */   public javax.swing.JButton set;
/*    */   
/*    */   public InputFrame()
/*    */   {
/* 16 */     JPanel panel = new JPanel(new java.awt.FlowLayout());
/* 17 */     this.input = new JFrame();
/* 18 */     this.set = new javax.swing.JButton("Set");
/* 19 */     this.value = new javax.swing.JTextField();
/* 20 */     this.set.addActionListener(new SetValueButton());
/* 21 */     this.input.setSize(300, 200);
/* 22 */     this.input.setResizable(false);
/* 23 */     this.input.setVisible(false);
/* 24 */     this.input.setTitle("VMSuper1.1");
/* 25 */     this.input.add(panel);
/* 26 */     panel.setLayout(null);
/* 27 */     panel.add(this.set);
/* 28 */     panel.add(this.value);
/* 29 */     this.value.setBounds(20, 20, 200, 25);
/* 30 */     this.set.setBounds(100, 100, 100, 30);
/*    */   }
/*    */   
/*    */   class SetValueButton implements java.awt.event.ActionListener { SetValueButton() {}
/*    */     
/* 35 */     public void actionPerformed(java.awt.event.ActionEvent e) { String line = InputFrame.this.value.getText().trim();
/* 36 */       if (line == "")
/* 37 */         return;
/* 38 */       int num = 0;
/* 39 */       int start = 0;
/* 40 */       if (line.charAt(0) == '-') {
/* 41 */         start = 1;
/*    */       }
/* 43 */       for (int i = start; i < line.length(); i++) {
/* 44 */         if ((line.charAt(i) <= '9') && (line.charAt(i) >= '0')) {
/* 45 */           Character c = Character.valueOf(line.charAt(i));
/* 46 */           num = Integer.parseInt(c.toString()) + num * 10;
/* 47 */           if (num > 214748363) {
/* 48 */             return;
/*    */           }
/*    */         } else {
/* 51 */           return;
/*    */         }
/*    */       }
/* 54 */       if (start == 1)
/* 55 */         num *= -1;
/* 56 */       int arg = Startcreation.frame.GetMemoryTableDoubleValue(Startcreation.PointerMemory + 2);
/* 57 */       int argval = Startcreation.frame.GetMemoryTableDoubleValue(arg);
/* 58 */       int prefix = Startcreation.frame.GetMemoryTableValue(Startcreation.PointerMemory + 1).intValue();
/* 59 */       switch (prefix) {
/* 60 */       case 0:  Startcreation.frame.SetMemoryTableDoubleValue(arg, num); break;
/* 61 */       case 2:  Startcreation.frame.SetMemoryTableDoubleValue(argval, num); break;
/* 62 */       default:  Startcreation.PointerMemory -= 2;
/*    */       }
/* 64 */       Startcreation.PointerMemory += 4;
/* 65 */       InputFrame.this.input.setVisible(false);
/* 66 */       if (Startcreation.running == true) {
/* 67 */         new Startcreation().run();
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/artem/Downloads/VM@.jar!/Execute/InputFrame.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */