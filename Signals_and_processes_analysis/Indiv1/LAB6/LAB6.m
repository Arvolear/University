clc
clear

syms t a b

A=1;
w0=1;
u0=0;

signal=A*cos(w0*t-u0);

haar=piecewise(-1<=((t-b)/a)<0, -1, 0<=((t-b)/a)<=1, 1, (abs((t-b)/a))>1, 0);
gauss=((t-b)/a)*exp(-(((t-b)/a)^2)/2);
f1=signal*gauss;

fi1=(1/sqrt(a))*int(f1, t, -inf, inf)
fsurf(fi1, [0 15 -5 5])
