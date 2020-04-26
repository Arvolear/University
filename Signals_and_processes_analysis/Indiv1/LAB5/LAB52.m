clc
clear

syms w T t

A0=1;
a=1;
f0=1;

len=20.0;

f=A0*exp(-a*t)*sin(2*pi*f0*t)*heaviside(t);

coef=1/((pi*(len^2))^(1/4));
win=exp(-((t-T)^2)/(2*(len^2)));
fint=f*win*exp(-1i*w*t);

F=angle(coef*int(fint,t,-inf,inf));

fsurf(F,[-10 10 -15 15])