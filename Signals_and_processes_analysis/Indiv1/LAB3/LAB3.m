clc
clear

N=1024
t=linspace (-1,10,N);
A0=1;
a=1;
f0=1;
figure
xlim([-1 10])
hold on 
for i=0:2
    A0=A0+2*i;
    f=A0*exp(-a*t).*sin(2*pi*f0*t).*heaviside(t);
    plot(t,f)
end
A=1;
hold off
figure
xlim([-1 10]);
hold on 
for i=  0:2
    a=a+2*i;
    f=A0*exp(-a*t).*sin(2*pi*f0*t).*heaviside(t);
    plot(t,f)
end
a=1;
hold off
figure
xlim([-1 10]);
hold on 
for i=0:2
    f0=f0+2*i;
    f=A0*exp(-a*t).*sin(2*pi*f0*t).*heaviside(t);
    plot(t,f)
end


