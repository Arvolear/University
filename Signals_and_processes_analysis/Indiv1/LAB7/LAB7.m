clc
clear

syms t a b

A0=1;
f0=1;
A=1;

signal=A0*exp(-A*t)*sin(2*pi*f0*t)*heaviside(t);

haar=piecewise(-1<=((t-b)/a)<0, -1, 0<=((t-b)/a)<=1, 1, (abs((t-b)/a))>1, 0);
gauss=((t-b)/a)*exp(-(((t-b)/a)^2)/2);
f1=signal*gauss;

fi1=(1/sqrt(abs(a)))*int(f1, t, -inf, inf);
fsurf(fi1, [0 4 -2 2])
