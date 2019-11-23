/*     */ package Start;
/*     */ 
/*     */ import Commands.Add;
/*     */ import Commands.Halt;
/*     */ import Commands.Jne;
/*     */ import Commands.Org;
/*     */ import Compiling.Command;
/*     */ import Compiling.MainCompile;
/*     */ import Compiling.tableOfPointers;
/*     */ import Execute.InputFrame;
/*     */ import createwin.FrameInit;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class Startcreation
/*     */ {
/*  16 */   public static int actionsCounter = 0;
/*     */   public static InputFrame input;
/*     */   public static FrameInit frame;
/*  19 */   public static ArrayList<tableOfPointers> PointersAndVars = new ArrayList();
/*  20 */   public static int PointerMemory = -1;
/*  21 */   public static Command[] command = { new Commands.Nop("NOP", 0, 0), new Halt("STOP", 0, 1), new Commands.Inva("INVA", 1, 2), new Commands.Shr("SHR", 2, 3), new Commands.Shl("SHL", 2, 4), new Commands.Call("CALL", 1, 5), new Commands.Ret("RET", 0, 6), new Commands.Jmp("JMP", 1, 7), new Commands.In("IN", 1, 8), new Commands.Out("OUT", 1, 9), new Commands.Or("OR", 2, 10), new Commands.And("AND", 2, 11), new Commands.Xor("XOR", 2, 12), new Add("ADD", 2, 13), new Commands.Sub("SUB", 2, 14), new Commands.Mul("MUL", 2, 15), new Commands.Div("DIV", 2, 16), new Commands.Je("JE", 3, 17), new Commands.Jg("JG", 3, 18), new Commands.Jl("JL", 3, 19), new Jne("JNE", 3, 20), new Commands.Mov("MOV", 2, 21), new Org(".START", 1, 22), new Commands.Dw(".DW", 1, 23), new Commands.End(".END", 1, 24) };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean running;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void Start()
/*     */   {
/*  34 */     frame = new FrameInit();
/*  35 */     input = new InputFrame();
/*     */   }
/*     */   
/*     */   public void compiling() {
/*  39 */     reset();
/*  40 */     PointersAndVars.add(new tableOfPointers("%A", -5));
/*  41 */     PointersAndVars.add(new tableOfPointers("%B", -4));
/*  42 */     PointersAndVars.add(new tableOfPointers("%C", -3));
/*  43 */     PointersAndVars.add(new tableOfPointers("%L", -2));
/*  44 */     PointersAndVars.add(new tableOfPointers("%D", -1));
/*  45 */     boolean b = new MainCompile().Compile(frame, command, PointersAndVars);
/*  46 */     if (b == true) {
/*  47 */       frame.SetmistakeValue(11, 0);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void reset()
/*     */   {
/*  55 */     actionsCounter = 0;
/*  56 */     Halt.isHalt = false;
/*  57 */     running = false;
/*  58 */     input.input.setVisible(false);
/*  59 */     PointerMemory = -1;
/*  60 */     MainCompile.SetPointAtMemory(0);
/*  61 */     PointersAndVars = null;
/*  62 */     PointersAndVars = new ArrayList();
/*  63 */     frame.SetmistakeValue(-1, 0);
/*  64 */     frame.clearOutput();
/*  65 */     for (int i = 0; i < 5; i++) {
/*  66 */       frame.SetRegisterValue(i, 0);
/*     */     }
/*  68 */     for (int i = 0; i < 65536; i++) {
/*  69 */       frame.SetMemoryTableValue(i, 0);
/*     */     }
/*  71 */     for (int i = 0; i < 4095; i++)
/*  72 */       frame.SetProgramValue(i, "NOP", i * 2);
/*     */   }
/*     */   
/*     */   public void run() {
/*  76 */     if ((PointerMemory < 0) || (PointerMemory > 65526)) {
/*  77 */       frame.SetmistakeValue(3, 0);
/*     */     }
/*  79 */     running = true;
/*  80 */     while ((PointerMemory >= 0) && (PointerMemory < 65536)) {
/*  81 */       actionsCounter += 1;
/*  82 */       if (actionsCounter > 10000) {
/*  83 */         frame.SetmistakeValue(15, 0);
/*     */ 
/*     */       }
/*  86 */       else if (!Step())
/*     */         break;
/*     */     }
/*     */   }
/*     */   
/*  91 */   public boolean Step() { if ((PointerMemory < 0) || (PointerMemory > 65526)) {
/*  92 */       return false;
/*     */     }
/*  94 */     frame.SelectCode(PointerMemory);
/*  95 */     if (!Execute.MainExecution.Execute()) {
/*  96 */       if (Halt.isHalt)
/*  97 */         PointerMemory = -1;
/*  98 */       return false;
/*     */     }
/* 100 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/artem/Downloads/VM@.jar!/Start/Startcreation.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */