clc
clear

syms w T t

A0=1;
a=1;
f0=1;

len=20.0;

f=A0*exp(-a*t)*sin(2*pi*f0*t)*heaviside(t);
win=piecewise((-(len-1)/2<=(t-T)<=(len-1)/2), 1, 0);
fint=f*win*exp(-1i*w*t);

F=angle(int(fint,t,-inf,inf));

fsurf(F,[-10 10 -15 15])