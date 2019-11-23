/*     */ package Compiling;
/*     */ 
/*     */ import Start.Startcreation;
/*     */ 
/*     */ public class GetArgs {
/*     */   public static int GetOrgArg(String line) {
/*   7 */     boolean NumFound = false;
/*   8 */     int num = 0;
/*   9 */     if ((line.indexOf(".START") + 6 >= line.length() - 1) && ((line.charAt(line.indexOf(".START") + 4) != ' ') || (line.charAt(line.indexOf(".START") + 6) != '\t'))) {
/*  10 */       return -1;
/*     */     }
/*  12 */     for (int i = line.indexOf(".START") + 6; i < line.length(); i++) {
/*  13 */       if (((line.charAt(i) != ' ') && (line.charAt(i) != '\t')) || (NumFound))
/*     */       {
/*  15 */         if ((line.charAt(i) != ' ') && (line.charAt(i) != '\t'))
/*  16 */           NumFound = true;
/*  17 */         if ((line.charAt(i) <= '9') && (line.charAt(i) >= '0')) {
/*  18 */           Character c = Character.valueOf(line.charAt(i));
/*  19 */           num = Integer.parseInt(c.toString()) + num * 10;
/*  20 */           if (num > 214748363) {
/*  21 */             return -1;
/*     */           }
/*     */         }
/*     */         else {
/*  25 */           return -1;
/*     */         }
/*     */       }
/*     */     }
/*  29 */     MainCompile.SetPointAtMemory(num % 65534);
/*  30 */     return num;
/*     */   }
/*     */   
/*  33 */   public static boolean GetDwordArg(String line) { if ((line.indexOf(".DW") + 3 >= line.length() - 1) && ((line.charAt(line.indexOf(".DW") + 3) != ' ') || (line.charAt(line.indexOf(".DW") + 3) != '\t'))) {
/*  34 */       return false;
/*     */     }
/*     */     
/*     */ 
/*  38 */     return DwordRecurtion(line, line.indexOf(".DW") + 3);
/*     */   }
/*     */   
/*     */   public static boolean DwordRecurtion(String line, int pointer) {
/*  42 */     boolean Positive = true;boolean Numfound = false;boolean NumCreated = false;
/*  43 */     int num = 0;
/*  44 */     for (int i = pointer; i < line.length(); i++)
/*  45 */       if (((line.charAt(i) != ' ') && (line.charAt(i) != '\t')) || (Numfound))
/*     */       {
/*     */ 
/*  48 */         if ((line.charAt(i) == '-') && (!Numfound)) {
/*  49 */           Positive = false;
/*  50 */           Numfound = true;
/*     */         }
/*     */         else {
/*  53 */           if ((line.charAt(i) != ' ') && (line.charAt(i) != '\t')) {
/*  54 */             Numfound = true;
/*     */           }
/*  56 */           if (((line.charAt(i) == ' ') || (line.charAt(i) == ',') || (line.charAt(i) == '\t')) && (Numfound == true) && (!NumCreated)) {
/*  57 */             if (!Positive)
/*  58 */               num = -1 * num;
/*  59 */             Startcreation.frame.SetMemoryTableDoubleValue(MainCompile.GetPointAtMemory(), num);
/*  60 */             MainCompile.SetPointAtMemory(MainCompile.GetPointAtMemory() + 2);
/*  61 */             NumCreated = true;
/*     */           }
/*  63 */           if (((line.charAt(i) != ' ') && (line.charAt(i) != '\t')) || (NumCreated != true))
/*     */           {
/*     */ 
/*  66 */             if ((line.charAt(i) == ',') && (NumCreated == true) && (i != line.length() - 1)) {
/*  67 */               return DwordRecurtion(line, i + 1);
/*     */             }
/*  69 */             if ((line.charAt(i) <= '9') && (line.charAt(i) >= '0')) {
/*  70 */               Character c = Character.valueOf(line.charAt(i));
/*  71 */               num = Integer.parseInt(c.toString()) + num * 10;
/*  72 */               if (num > 214748363) {
/*  73 */                 return false;
/*     */               }
/*     */             } else {
/*  76 */               return false;
/*     */             }
/*  78 */             if (i == line.length() - 1) {
/*  79 */               if (!Positive)
/*  80 */                 num = -1 * num;
/*  81 */               Startcreation.frame.SetMemoryTableDoubleValue(MainCompile.GetPointAtMemory(), num);
/*  82 */               MainCompile.SetPointAtMemory(MainCompile.GetPointAtMemory() + 2);
/*  83 */               NumCreated = true;
/*     */             }
/*     */           } } }
/*  86 */     return true;
/*     */   }
/*     */   
/*  89 */   public static boolean GetEndArg(String line) { boolean EndFound = false;
/*  90 */     int Start = -1;
/*  91 */     if (Startcreation.PointerMemory != -1)
/*  92 */       return false;
/*  93 */     if ((line.indexOf(".END") + 4 >= line.length() - 1) && ((line.charAt(line.indexOf(".END") + 4) != ' ') || (line.charAt(line.indexOf(".END") + 4) != '\t'))) {
/*  94 */       return false;
/*     */     }
/*  96 */     for (int i = line.indexOf(".END") + 4; i < line.length(); i++)
/*  97 */       if (((line.charAt(i) != ' ') && (line.charAt(i) != '\t')) || (EndFound))
/*     */       {
/*  99 */         if (((line.charAt(i) != ' ') || (line.charAt(i) != '\t')) && (!EndFound)) {
/* 100 */           EndFound = true;
/* 101 */           Start = i;
/*     */         }
/* 103 */         if ((line.charAt(i) == ' ') || (line.charAt(i) == '\t'))
/* 104 */           return false;
/*     */       }
/* 106 */     String s = line.substring(Start);
/* 107 */     for (int i = 0; i < Startcreation.PointersAndVars.size(); i++) {
/* 108 */       if (s.equals(((tableOfPointers)Startcreation.PointersAndVars.get(i)).GetName())) {
/* 109 */         if (((tableOfPointers)Startcreation.PointersAndVars.get(i)).GetAddress() == -1) {
/* 110 */           return false;
/*     */         }
/* 112 */         Startcreation.PointerMemory = ((tableOfPointers)Startcreation.PointersAndVars.get(i)).GetAddress();
/* 113 */         return true;
/*     */       }
/*     */     }
/*     */     
/* 117 */     return false;
/*     */   }
/*     */   
/* 120 */   public static boolean GetSTAArgs(String line, Command com) { int start = line.indexOf(com.getCommandName()) + com.getCommandName().length();
/*     */     
/* 122 */     if (start == line.length())
/* 123 */       return false;
/* 124 */     for (int i = start; i < line.length() - 1; i++)
/* 125 */       if ((line.charAt(i) != ' ') && (line.charAt(i) != '\t'))
/*     */       {
/* 127 */         start = i;
/* 128 */         break;
/*     */       }
/* 130 */     String arg = line.substring(start);
/* 131 */     int a = AnalizeSTAArg(arg.trim());
/* 132 */     if (a == -1)
/* 133 */       return false;
/* 134 */     int valPrefix = Math.abs(a % 10);
/* 135 */     int val = a / 10;
/* 136 */     Startcreation.frame.SetMemoryTableValue(MainCompile.GetPointAtMemory(), com.GetCommandNum());
/* 137 */     Startcreation.frame.SetMemoryTableValue(MainCompile.GetPointAtMemory() + 1, valPrefix);
/* 138 */     Startcreation.frame.SetMemoryTableDoubleValue(MainCompile.GetPointAtMemory() + 2, val);
/* 139 */     MainCompile.SetPointAtMemory(MainCompile.GetPointAtMemory() + 4);
/* 140 */     return true;
/*     */   }
/*     */   
/* 143 */   public static boolean GetOneArg(String line, Command com) { int start = line.indexOf(com.getCommandName()) + com.getCommandName().length();
/*     */     
/* 145 */     if (start == line.length())
/* 146 */       return false;
/* 147 */     for (int i = start; i < line.length() - 1; i++)
/* 148 */       if ((line.charAt(i) != ' ') && (line.charAt(i) != '\t'))
/*     */       {
/* 150 */         start = i;
/* 151 */         break;
/*     */       }
/* 153 */     String arg = line.substring(start);
/* 154 */     int a = AnalyzeArgument(arg.trim());
/* 155 */     if (a == -1) {
/* 156 */       return false;
/*     */     }
/* 158 */     int valPrefix = Math.abs(a % 10);
/* 159 */     int val = a / 10;
/*     */     
/*     */ 
/* 162 */     Startcreation.frame.SetMemoryTableValue(MainCompile.GetPointAtMemory(), com.GetCommandNum());
/* 163 */     Startcreation.frame.SetMemoryTableValue(MainCompile.GetPointAtMemory() + 1, valPrefix);
/* 164 */     Startcreation.frame.SetMemoryTableDoubleValue(MainCompile.GetPointAtMemory() + 2, val);
/* 165 */     MainCompile.SetPointAtMemory(MainCompile.GetPointAtMemory() + 4);
/* 166 */     return true;
/*     */   }
/*     */   
/* 169 */   public static boolean GetTwoArg(String line, Command com) { int start = line.indexOf(com.getCommandName()) + com.getCommandName().length();
/*     */     
/* 171 */     if (start == line.length())
/* 172 */       return false;
/* 173 */     int endFirst = -1;
/* 174 */     for (int i = start; i < line.length(); i++) {
/* 175 */       if (line.charAt(i) == ',') {
/* 176 */         endFirst = i;
/* 177 */         break;
/*     */       }
/*     */     }
/* 180 */     if (endFirst == -1)
/* 181 */       return false;
/* 182 */     if (endFirst == line.length() - 1)
/* 183 */       return false;
/* 184 */     String arg1 = line.substring(start, endFirst);
/* 185 */     int a1 = AnalyzeArgument(arg1.trim());
/* 186 */     if (a1 == -1)
/* 187 */       return false;
/* 188 */     int valPrefix1 = Math.abs(a1 % 10);
/* 189 */     int val1 = a1 / 10;
/* 190 */     Startcreation.frame.SetMemoryTableValue(MainCompile.GetPointAtMemory(), com.GetCommandNum());
/* 191 */     Startcreation.frame.SetMemoryTableDoubleValue(MainCompile.GetPointAtMemory() + 2, val1);
/* 192 */     String arg2 = line.substring(endFirst + 1);
/* 193 */     int a2 = AnalyzeArgument(arg2.trim());
/* 194 */     if (a2 == -1)
/* 195 */       return false;
/* 196 */     int valPrefix2 = Math.abs(a2 % 10);
/* 197 */     int val2 = a2 / 10;
/* 198 */     Startcreation.frame.SetMemoryTableDoubleValue(MainCompile.GetPointAtMemory() + 4, val2);
/* 199 */     Startcreation.frame.SetMemoryTableValue(MainCompile.GetPointAtMemory() + 1, FormPrefix(valPrefix1, valPrefix2));
/* 200 */     MainCompile.SetPointAtMemory(MainCompile.GetPointAtMemory() + 6);
/* 201 */     return true;
/*     */   }
/*     */   
/* 204 */   public static boolean GetThreeArg(String line, Command com) { int start = line.indexOf(com.getCommandName()) + com.getCommandName().length();
/*     */     
/* 206 */     if (start == line.length())
/* 207 */       return false;
/* 208 */     int endFirst = -1;int endSecond = -1;
/* 209 */     for (int i = start; i < line.length(); i++) {
/* 210 */       if (line.charAt(i) == ',') {
/* 211 */         endFirst = i;
/* 212 */         break;
/*     */       }
/*     */     }
/* 215 */     if (endFirst == -1) {
/* 216 */       return false;
/*     */     }
/* 218 */     if (endFirst == line.length() - 1)
/* 219 */       return false;
/* 220 */     String arg1 = line.substring(start, endFirst);
/* 221 */     int a1 = AnalyzeArgument(arg1.trim());
/* 222 */     if (a1 == -1)
/* 223 */       return false;
/* 224 */     int valPrefix1 = Math.abs(a1 % 10);
/* 225 */     int val1 = a1 / 10;
/* 226 */     Startcreation.frame.SetMemoryTableValue(MainCompile.GetPointAtMemory(), com.GetCommandNum());
/* 227 */     Startcreation.frame.SetMemoryTableDoubleValue(MainCompile.GetPointAtMemory() + 2, val1);
/* 228 */     for (int i = endFirst + 1; i < line.length(); i++) {
/* 229 */       if (line.charAt(i) == ',') {
/* 230 */         endSecond = i;
/* 231 */         break;
/*     */       }
/*     */     }
/* 234 */     if (endSecond == -1)
/* 235 */       return false;
/* 236 */     if (endSecond == line.length() - 1)
/* 237 */       return false;
/* 238 */     String arg2 = line.substring(endFirst + 1, endSecond);
/* 239 */     int a2 = AnalyzeArgument(arg2.trim());
/* 240 */     if (a2 == -1)
/* 241 */       return false;
/* 242 */     int valPrefix2 = Math.abs(a2 % 10);
/* 243 */     int val2 = a2 / 10;
/* 244 */     String arg3 = line.substring(endSecond + 1).trim();
/* 245 */     int a3 = AnalizeSTAArg(arg3);
/* 246 */     if (a3 == -1)
/* 247 */       return false;
/* 248 */     int val3 = a3 / 10;
/* 249 */     Startcreation.frame.SetMemoryTableDoubleValue(MainCompile.GetPointAtMemory() + 4, val2);
/* 250 */     Startcreation.frame.SetMemoryTableDoubleValue(MainCompile.GetPointAtMemory() + 6, val3);
/* 251 */     Startcreation.frame.SetMemoryTableValue(MainCompile.GetPointAtMemory() + 1, FormPrefix(valPrefix1, valPrefix2));
/* 252 */     MainCompile.SetPointAtMemory(MainCompile.GetPointAtMemory() + 8);
/* 253 */     return true;
/*     */   }
/*     */   
/* 256 */   public static int FormPrefix(int a1, int a2) { if ((a1 == 0) && (a2 == 0)) {
/* 257 */       return 0;
/*     */     }
/* 259 */     if ((a1 == 1) && (a2 == 0)) {
/* 260 */       return 1;
/*     */     }
/* 262 */     if ((a1 == 0) && (a2 == 1)) {
/* 263 */       return 2;
/*     */     }
/* 265 */     if ((a1 == 1) && (a2 == 1)) {
/* 266 */       return 3;
/*     */     }
/* 268 */     if ((a1 == 2) && (a2 == 0)) {
/* 269 */       return 4;
/*     */     }
/* 271 */     if ((a1 == 0) && (a2 == 2)) {
/* 272 */       return 5;
/*     */     }
/* 274 */     if ((a1 == 2) && (a2 == 2)) {
/* 275 */       return 6;
/*     */     }
/* 277 */     if ((a1 == 1) && (a2 == 2)) {
/* 278 */       return 7;
/*     */     }
/* 280 */     if ((a1 == 2) && (a2 == 1)) {
/* 281 */       return 8;
/*     */     }
/* 283 */     return -1;
/*     */   }
/*     */   
/* 286 */   public static int AnalyzeArgument(String line) { int num = 0;
/* 287 */     if (line.charAt(0) == '&') {
/* 288 */       if (line.length() == 1) {
/* 289 */         return -1;
/*     */       }
/* 291 */       if (((line.charAt(1) > '9') || (line.charAt(1) < '0')) && (line.charAt(1) != '-')) {
/* 292 */         String Value = line.substring(1);
/* 293 */         for (int i = 0; i < Startcreation.PointersAndVars.size(); i++) {
/* 294 */           if (Value.equals(((tableOfPointers)Startcreation.PointersAndVars.get(i)).GetName())) {
/* 295 */             return ((tableOfPointers)Startcreation.PointersAndVars.get(i)).GetAddress() * 10 + 1;
/*     */           }
/*     */         }
/*     */       }
/*     */       else {
/* 300 */         int start = 1;int plus = 1;
/* 301 */         if (line.charAt(1) == '-') {
/* 302 */           if (line.length() == 2) {
/* 303 */             return -1;
/*     */           }
/* 305 */           start = 2;
/* 306 */           plus = -1;
/*     */         }
/* 308 */         for (int i = start; i < line.length(); i++) {
/* 309 */           if ((line.charAt(i) <= '9') && (line.charAt(i) >= '0')) {
/* 310 */             Character c = Character.valueOf(line.charAt(i));
/* 311 */             num = Integer.parseInt(c.toString()) + num * 10;
/* 312 */             if (num > 214748363) {
/* 313 */               return -1;
/*     */             }
/*     */           } else {
/* 316 */             return -1;
/*     */           }
/*     */         }
/* 319 */         return (num * 10 + 1) * plus;
/*     */       }
/*     */       
/*     */     }
/* 323 */     else if (line.charAt(0) == '*') {
/* 324 */       if (line.length() == 1)
/* 325 */         return -1;
/* 326 */       String arg = line.substring(1);
/* 327 */       if (((line.charAt(1) > '9') || (line.charAt(1) < '0')) && (line.charAt(1) != '-')) {
/* 328 */         for (int i = 0; i < Startcreation.PointersAndVars.size(); i++) {
/* 329 */           if (arg.equals(((tableOfPointers)Startcreation.PointersAndVars.get(i)).GetName())) {
/* 330 */             return ((tableOfPointers)Startcreation.PointersAndVars.get(i)).GetAddress() * 10 + 2;
/*     */           }
/*     */         }
/*     */       }
/*     */       else {
/* 335 */         int start = 1;int plus = 1;
/* 336 */         if (line.charAt(1) == '-') {
/* 337 */           if (line.length() == 2) {
/* 338 */             return -1;
/*     */           }
/* 340 */           start = 2;
/* 341 */           plus = -1;
/*     */         }
/* 343 */         for (int i = start; i < line.length(); i++) {
/* 344 */           if ((line.charAt(i) <= '9') && (line.charAt(i) >= '0')) {
/* 345 */             Character c = Character.valueOf(line.charAt(i));
/* 346 */             num = Integer.parseInt(c.toString()) + num * 10;
/* 347 */             if (num > 214748363) {
/* 348 */               return -1;
/*     */             }
/*     */           } else {
/* 351 */             return -1;
/*     */           }
/*     */         }
/* 354 */         return (num * 10 + 2) * plus;
/*     */       }
/*     */     }
/*     */     else {
/* 358 */       if ((line.charAt(0) <= '9') && (line.charAt(0) >= '0')) {
/* 359 */         for (int i = 0; i < line.length(); i++) {
/* 360 */           if ((line.charAt(i) <= '9') && (line.charAt(i) >= '0')) {
/* 361 */             Character c = Character.valueOf(line.charAt(i));
/* 362 */             num = Integer.parseInt(c.toString()) + num * 10;
/* 363 */             if (num > 214748363) {
/* 364 */               return -1;
/*     */             }
/*     */           } else {
/* 367 */             return -1;
/*     */           }
/*     */         }
/* 370 */         return num * 10;
/*     */       }
/*     */       
/*     */ 
/* 374 */       for (int i = 0; i < Startcreation.PointersAndVars.size(); i++) {
/* 375 */         if (line.equals(((tableOfPointers)Startcreation.PointersAndVars.get(i)).GetName())) {
/* 376 */           return ((tableOfPointers)Startcreation.PointersAndVars.get(i)).GetAddress() * 10;
/*     */         }
/*     */       }
/*     */     }
/* 380 */     return -1;
/*     */   }
/*     */   
/* 383 */   public static int AnalizeSTAArg(String line) { if (line.charAt(0) == '*') {
/* 384 */       if (line.length() == 1)
/* 385 */         return -1;
/* 386 */       String arg = line.substring(1);
/* 387 */       for (int i = 0; i < Startcreation.PointersAndVars.size(); i++) {
/* 388 */         if (arg.equals(((tableOfPointers)Startcreation.PointersAndVars.get(i)).GetName())) {
/* 389 */           return ((tableOfPointers)Startcreation.PointersAndVars.get(i)).GetAddress() * 10 + 2;
/*     */         }
/*     */       }
/*     */     } else {
/* 393 */       if ((line.charAt(0) <= '9') && (line.charAt(0) >= '0')) {
/* 394 */         int num = 0;
/* 395 */         for (int i = 0; i < line.length(); i++) {
/* 396 */           if ((line.charAt(i) <= '9') && (line.charAt(i) >= '0')) {
/* 397 */             Character c = Character.valueOf(line.charAt(i));
/* 398 */             num = Integer.parseInt(c.toString()) + num * 10;
/* 399 */             if (num > 214748363) {
/* 400 */               return -1;
/*     */             }
/*     */           } else {
/* 403 */             return -1;
/*     */           }
/*     */         }
/* 406 */         return num * 10;
/*     */       }
/*     */       
/* 409 */       for (int i = 0; i < Startcreation.PointersAndVars.size(); i++) {
/* 410 */         if (line.equals(((tableOfPointers)Startcreation.PointersAndVars.get(i)).GetName())) {
/* 411 */           return ((tableOfPointers)Startcreation.PointersAndVars.get(i)).GetAddress() * 10;
/*     */         }
/*     */       }
/*     */     }
/* 415 */     return -1;
/*     */   }
/*     */   
/*     */   public static boolean AnalizeMOVArg(String line, Command com) {
/* 419 */     int start = line.indexOf("MOV") + 3;
/*     */     
/* 421 */     if (start == line.length())
/* 422 */       return false;
/* 423 */     int endFirst = -1;
/* 424 */     for (int i = start; i < line.length(); i++) {
/* 425 */       if (line.charAt(i) == ',') {
/* 426 */         endFirst = i;
/* 427 */         break;
/*     */       }
/*     */     }
/* 430 */     if (endFirst == -1)
/* 431 */       return false;
/* 432 */     if (endFirst == line.length() - 1)
/* 433 */       return false;
/* 434 */     String arg1 = line.substring(start, endFirst);
/* 435 */     int a1 = AnalyzeArgument(arg1.trim());
/* 436 */     if (a1 == -1)
/* 437 */       return false;
/* 438 */     int valPrefix1 = Math.abs(a1 % 10);
/* 439 */     int val1 = a1 / 10;
/* 440 */     Startcreation.frame.SetMemoryTableValue(MainCompile.GetPointAtMemory(), com.GetCommandNum());
/* 441 */     Startcreation.frame.SetMemoryTableDoubleValue(MainCompile.GetPointAtMemory() + 2, val1);
/* 442 */     String arg2 = line.substring(endFirst + 1);
/* 443 */     int a2 = AnalyzeArgument(arg2.trim());
/* 444 */     System.out.println(a2);
/* 445 */     if (a2 == -1)
/* 446 */       return false;
/* 447 */     int valPrefix2 = Math.abs(a2 % 10);
/* 448 */     int val2 = a2 / 10;
/* 449 */     int pref = FormPrefix(valPrefix1, valPrefix2);
/*     */     
/* 451 */     System.out.println(pref);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 456 */     Startcreation.frame.SetMemoryTableDoubleValue(MainCompile.GetPointAtMemory() + 4, val2);
/* 457 */     Startcreation.frame.SetMemoryTableValue(MainCompile.GetPointAtMemory() + 1, pref);
/*     */     
/* 459 */     MainCompile.SetPointAtMemory(MainCompile.GetPointAtMemory() + 6);
/* 460 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/artem/Downloads/VM@.jar!/Compiling/GetArgs.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */