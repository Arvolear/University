clc
clear
A0=1;
f0=1;
a=1;
N=1024;
t=linspace(-1,10,N);
f=A0*exp(-a*t).*sin(2*pi*f0*t).*heaviside(t);
ff=fft(f);

figure
hold on  
plot(t,f)

for k=1:2
    cut=10^k;
    for i=N-cut:N
        ff(i)=0;
    end
    fn=real(ifft(ff));
    plot(t,fn)
end
