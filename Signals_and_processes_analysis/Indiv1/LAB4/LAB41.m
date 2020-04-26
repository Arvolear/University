clc
clear

syms t w
A0=1;
f0=1;
a=1;

f=A0*exp(-a*t)*sin(2*pi*f0*t)*heaviside(t);
ff=fourier(f);

figure
xlim([-15 15])
ylim([0 0.6])
hold on   
fplot(abs(ff))
figure
fplot(angle(ff))
xlim([-15 15])
ylim([-5 5])
