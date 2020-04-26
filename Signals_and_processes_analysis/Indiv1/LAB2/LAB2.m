clc
clear

t=linspace(-2*pi, 2*pi, 1000);

A=1;
w0=5;
u0=0;

signal = A*cos(w0*t-u0);

f1 = fft(signal);

hold on
xlim([-8 8])
ylim([-5 5])

plot(t, signal)
%plot(t, angle(f1))