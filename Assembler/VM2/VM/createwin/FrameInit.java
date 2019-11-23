package createwin;
import java.io.*;
import javax.swing.JFileChooser;
import Start.Startcreation;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FrameInit
{
    private JFrame frame = new JFrame("Frame");
    private JPanel panel = new JPanel(new java.awt.FlowLayout());
    private JTable Program;
    private JTable regTable;
    private JTable memory;
    private JTextArea code = new JTextArea(0, 0);
    private JTextField mistake = new JTextField();
    private JTextArea output = new JTextArea();
    private int LinesInOutput = 0;

    public FrameInit() { javax.swing.JLabel outLabel = new javax.swing.JLabel("Output");
        javax.swing.JMenuBar mb = new javax.swing.JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem exit = new JMenuItem("Exit");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save as..");
        JButton stepbystep = new JButton("Step");
        JButton run = new JButton("Run");
        JButton compile = new JButton("Compile");
        JButton resetVM = new JButton("Reset VM");
        JScrollPane scrollcode = new JScrollPane(this.code);
        ArrayList<registers> reg = new ArrayList();
        this.frame.setJMenuBar(mb);
        mb.add(file);
        file.add(open);
        file.add(save);
        file.add(exit);
        compile.addActionListener(new Compile());
        save.addActionListener(new MBSave());
        open.addActionListener(new MBOpen());
        exit.addActionListener(new MBExit());
        run.addActionListener(new Run());
        stepbystep.addActionListener(new Step());
        resetVM.addActionListener(new Reset());
        this.frame.setSize(800, 590);
        this.frame.add(this.panel);
        this.frame.setResizable(false);
        this.frame.setDefaultCloseOperation(0);
        this.frame.setTitle("VMSuper1.1");
        this.frame.setVisible(true);
        this.frame.addWindowListener(new java.awt.event.WindowListener() { public void windowDeactivated(WindowEvent event) {}

            public void windowDeiconified(WindowEvent event) {}

            public void windowIconified(WindowEvent event) {}

            public void windowOpened(WindowEvent event) {}
            public void windowClosing(WindowEvent event) { Object[] options = { "Yes", "No!" };
                int n = javax.swing.JOptionPane.showOptionDialog(FrameInit.this.frame, "Are you sure you want to quit?", "Exit", 0, 3, null, options, options[0]);


                if (n == 0) {
                    event.getWindow().setVisible(false);
                    System.exit(0);
                } }

            public void windowActivated(WindowEvent e) {}

            public void windowClosed(WindowEvent e) {} });
        this.Program = new JTable(new TableModelProgram());
        JScrollPane scrolltable = new JScrollPane(this.Program);
        this.memory = new JTable(new TableModelMemory());
        this.Program.getColumnModel().getColumn(0).setMaxWidth(50);
        this.Program.setRowSelectionInterval(100, 100);
        this.Program.repaint();
        reg.add(new registers("A=", Integer.valueOf(0)));
        reg.add(new registers("B=", Integer.valueOf(0)));
        reg.add(new registers("C=", Integer.valueOf(0)));
        reg.add(new registers("L=", Integer.valueOf(0)));
        reg.add(new registers("D=", Integer.valueOf(0)));
        javax.swing.table.TableModel regModel = new TabModelRegisters(reg);
        this.regTable = new JTable(regModel);
        this.regTable.getColumnModel().getColumn(0).setMaxWidth(40);
        this.mistake.setText("Mistake field");
        this.frame.getRootPane().setDefaultButton(stepbystep);
        this.panel.setLayout(null);
        this.panel.add(scrolltable);
        this.panel.add(stepbystep);
        this.panel.add(compile);
        this.panel.add(run);
        this.panel.add(resetVM);
        this.panel.add(this.mistake);
        this.panel.add(this.output);
        this.panel.add(this.regTable);
        scrolltable.createVerticalScrollBar();
        JScrollPane scrollmemory = new JScrollPane(this.memory);
        this.panel.add(scrollmemory);
        scrollmemory.createVerticalScrollBar();
        scrollmemory.createHorizontalScrollBar();
        this.panel.add(scrollcode);
        this.panel.add(outLabel);
        for (int i = 0; i < 4; i++)
            this.regTable.setRowHeight(i, 20);
        this.code.setTabSize(3);
        this.regTable.setBounds(240, 50, 260, 100);
        scrollmemory.setBounds(20, 430, 760, 90);
        scrollcode.setBounds(520, 20, 260, 370);
        scrolltable.setBounds(20, 20, 200, 400);
        stepbystep.setBounds(320, 250, 100, 30);
        compile.setBounds(320, 290, 100, 30);
        run.setBounds(320, 210, 100, 30);
        resetVM.setBounds(320, 170, 100, 30);
        this.mistake.setBounds(520, 395, 260, 25);
        this.output.setBounds(230, 340, 280, 80);
        outLabel.setBounds(230, 320, 280, 20);
        this.mistake.setEditable(false);
        this.output.setEditable(false);
        scrollmemory.repaint();
    }

    public void SelectCode(int address) {
        if (address < 0)
            return;
        this.Program.removeRowSelectionInterval(0, 8191);
        for (int i = 0; i < 8191; i++)
            if ((address >= ((Integer)this.Program.getValueAt(i, 0)).intValue()) && (address < ((Integer)this.Program.getValueAt(i + 1, 0)).intValue())) {
                this.Program.setRowSelectionInterval(i, i);
                this.Program.scrollRectToVisible(this.Program.getCellRect(i, 0, true));
                this.Program.repaint();
                return;
            }
    }

    public void SetProgramValue(int Row, String value, int address) {
        this.Program.setValueAt(Integer.valueOf(address), Row, 0);
        this.Program.setValueAt(value, Row, 1);
        this.Program.repaint();
    }

    public void SetMemoryTableValue(int address, int value) { if (address > 65535)
        return;
        int col = address % 16 + 1;
        int row = address / 16;
        this.memory.setValueAt(Integer.valueOf(value), row, col);
        this.memory.repaint();
    }

    public Integer GetMemoryTableValue(int address) {
        if ((address > 65535) || (address < 0))
            return Integer.valueOf(-1);
        int column = address % 16 + 1;
        int row = address / 16;
        return (Integer)this.memory.getValueAt(row, column);
    }

    public void SetMemoryTableDoubleValue(int address, int value) { if ((address > -6) && (address <= -1)) {
        int register = address + 5;
        SetRegisterValue(register, value);
        return;
    }
    short val = (short)value;
    int num = val;
    if (val < 0)
        num = 65536 + val;
    SetMemoryTableValue(address, num / 256);
    SetMemoryTableValue(address + 1, num % 256);
    }

    public int GetMemoryTableDoubleValue(int address) { if ((address > -6) && (address <= -1)) {
        int register = address + 5;
        return GetRegisterValue(register);
    }
    int value = GetMemoryTableValue(address).intValue() * 256 + GetMemoryTableValue(address + 1).intValue();
    short val = (short)value;
    return val;
    }

    public void SetRegisterValue(int register, int value) {
        this.regTable.setValueAt(Integer.valueOf(value), register, 1);
        this.regTable.repaint();
    }

    public int GetRegisterValue(int register) { Integer value = (Integer)this.regTable.getValueAt(register, 1);
        return value.intValue();
    }

    public void SetmistakeValue(int misNumber, int Line) { 
        String text;
        switch (misNumber) {
            case -1:  text = "Mistake field"; break;
            case 1:  text = "No start Point founded"; break;
            case 2:  text = "No end Point founded"; break;
            case 3:  text = "Program hasn't compiled yet"; break;
            case 4:  text = "Wrong pointer declaration"; break;
            case 5:  text = "No command found"; break;
            case 6:  text = "Unknown command"; break;
            case 7:  text = "Invalid Argument"; break;
            case 8:  text = "Ilegal pointer name"; break;
            case 9:  text = "Invalid macro"; break;
            case 10:  text = "Wrong variable declaration"; break;
            case 11:  text = "Successfully compiled"; break;
            case 12:  text = "End point haven't found"; break;
            case 13:  text = "Successfully finished"; break;
            case 14:  text = "Zero DIV"; break;
            case 15:  text = "Loop error"; break;
            case 0: default:  text = "Unknown mistake";
        }
        if ((misNumber == -1) || (misNumber == 11) || (misNumber == 12) || (misNumber == 13) || (misNumber == 14) || (misNumber == 15)) {
            this.mistake.setText(text);
            return;
        }
        this.mistake.setText(text + " in " + Line + " line.");
    }

    public String GetCode() { return this.code.getText(); }

    public void OutputVal(int value) {
        if (this.output.getText().length() - this.LinesInOutput >= 40) {
            this.output.setText(this.output.getText() + "Enter");
            this.LinesInOutput = this.output.getText().length();
        }

        this.output.setText(this.output.getText() + value + " ");
    }

    public void clearOutput() { this.output.setText("");
        this.LinesInOutput = 0;
    }

    public int GetTwoByteValue() { return 0; }

    public void SetTwoByteValue(int val) {}

    class MBExit implements ActionListener {
        MBExit() {}

        public void actionPerformed(ActionEvent e) {
            Object[] options = { "YES", "NO!" };

            int n = javax.swing.JOptionPane.showOptionDialog(FrameInit.this.frame, "Are you sure you want to quit?", "Exit", 0, 3, null, options, options[0]);



            if (n == 0) {
                FrameInit.this.frame.setVisible(false);
                System.exit(0);
            }
        }
    }

    class MBOpen implements ActionListener { MBOpen() {}

        public void actionPerformed(ActionEvent e) { 
            JFileChooser fileChooser = new JFileChooser();
            int retval = fileChooser.showDialog(null, "Open file");
            if (retval == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                try{
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String _line;
                    String s = "";
                    while((_line = reader.readLine()) != null) {
                        s += _line + "\n";
                    }
                    reader.close();
                    FrameInit.this.code.setText(s);
                } catch(FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch(IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    class MBSave implements ActionListener {
        MBSave() {}

        public void actionPerformed(ActionEvent e) { 
            JFileChooser fileChooser = new JFileChooser();
            int retval = fileChooser.showDialog(null, "Open file");
            if (retval == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                try{
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    String text = FrameInit.this.code.getText();
                    writer.write(text);
                    writer.flush();
                    writer.close();
                } catch(FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch(IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    class Compile implements ActionListener {
        Compile() {}

        public void actionPerformed(ActionEvent e) { new Startcreation().compiling(); }
    }

    class Run implements ActionListener {
        Run() {}

        public void actionPerformed(ActionEvent e) {
            new Startcreation().run();
        }
    }

    class Step implements ActionListener {
        Step() {}

        public void actionPerformed(ActionEvent e) { new Startcreation().Step(); }
    }

    class Reset implements ActionListener {
        Reset() {}

        public void actionPerformed(ActionEvent e) {
            new Startcreation().reset();
        }
    }
}


/* Location:              /home/artem/Downloads/VM@.jar!/createwin/FrameInit.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */
