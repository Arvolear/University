import matplotlib.pyplot as plt
from matplotlib import cm
from matplotlib.ticker import LinearLocator
import numpy as np

#########################################

# x = np.array([-2, 5, -4, -2])
# y = np.array([6, 4, -2, 6])

# ax = plt.gca()

# ax.plot(x, y, 'r')
# ax.spines['left'].set_position('zero')
# ax.spines['right'].set_color('none')
# ax.spines['bottom'].set_position('zero')
# ax.spines['top'].set_color('none')

# plt.xlim(-5, 6)
# plt.ylim(-3, 7)

# plt.show()

#########################################

# x = np.linspace(-5.0, 6.0, num=100)
# y = np.linspace(-2.0, 7.0, num=100)

# X, Y = np.meshgrid(x, y)

# z = []

# def getZ(xx, yy):
#     ax = 2 * xx + 7 * yy - 38
#     bx = 6 * xx - 9 * yy + 6
#     cx = -8 * xx + 2 * yy - 28

#     if (ax > 0 or bx > 0 or cx > 0):
#         return 0

#     return abs(ax * bx * cx / 108000)


# for i in range(len(x)):
#     tmp = []
#     for j in range(len(y)):
#         tmp.append(getZ(x[j], y[i]))
#     z.append(tmp)


# ax = plt.axes(projection="3d")
# ax.plot_surface(X, Y, np.array(z), rstride=1, cstride=1,
#                 cmap='turbo', edgecolor='none')

# # plt.pcolor(X, Y, np.array(z),
# #                        cmap='turbo', edgecolor='none')

# plt.show()

#########################################

# x1 = np.linspace(-4.0, -2.0, num=100)
# x2 = np.linspace(-2.0, 5.0, num=100)

# def calc1(xx1):
#     return abs((5 * ((xx1 + 4) ** 3) * (11 * xx1 + 8)) / 2916)

# def calc2(xx2):
#     return abs((20 * ((xx2 - 5) ** 3) * (8 * xx2 + 23)) / 250047)

# ax = plt.gca()

# ax.plot(x1, calc1(x1), 'r')
# ax.plot(x2, calc2(x2), 'b')

# ax.spines['left'].set_position('zero')
# ax.spines['right'].set_color('none')
# ax.spines['bottom'].set_position('zero')
# ax.spines['top'].set_color('none')

# plt.xlim(-5, 6)
# plt.ylim(0, 0.3)

# plt.show()

#########################################

# x1 = np.linspace(-4.0, -2.0, num=100)
# x2 = np.linspace(-2.0, 5.0, num=100)

# def calc1(t):
#     return -(5 * (11 * (t ** 5) / 5 + 35 * (t ** 4) + 208 * (t ** 3) + 544 * (t ** 2) + 512 * t) / 2916) + 0.086

# def calc2(t):
#     return -(20 * (8 * (t ** 5) / 5 - 97 * (t ** 4) / 4 + 85 * (t ** 3) + 725 * (t ** 2) / 2 - 2875 * t) / 250047) + 0.61

# ax = plt.gca()

# ax.plot(x1, calc1(x1), 'r')
# ax.plot(x2, calc2(x2), 'b')

# ax.spines['left'].set_position('zero')
# ax.spines['right'].set_color('none')
# ax.spines['bottom'].set_position('zero')
# ax.spines['top'].set_color('none')

# plt.xlim(-5, 6)
# plt.ylim(0, 1)

# plt.show()

#########################################

# x = np.linspace(-4.0, 5.0, num=25)
# y = []

# def calc1(t):
#     return -(5 * (11 * (t ** 5) / 5 + 35 * (t ** 4) + 208 * (t ** 3) + 544 * (t ** 2) + 512 * t) / 2916) + 0.087

# def calc2(t):
#     return -(20 * (8 * (t ** 5) / 5 - 97 * (t ** 4) / 4 + 85 * (t ** 3) + 725 * (t ** 2) / 2 - 2875 * t) / 250047) + 0.611

# def calc(t):
#     if (t < -4):
#         return 0
    
#     if (t < -2):
#         return calc1(t)

#     if (t < 5):
#         return calc2(t)

#     return 1

# print(x)

# for xx in x:
#     yy = calc(xx)
#     y.append(yy)
#     print(yy)

# ax = plt.gca()

# ax.plot(y, x, 'r')

# ax.spines['left'].set_position('zero')
# ax.spines['right'].set_color('none')
# ax.spines['bottom'].set_position('zero')
# ax.spines['top'].set_color('none')

# plt.xlim(0, 1)
# plt.ylim(-5, 6)

# plt.show()

#########################################

x = np.linspace(-1.0, 10.0, num=100)

def calc(xx1):
    return (-37 + 7 * xx1) * (9 - 9 * xx1) * (-32 + 2 * xx1) / -21276

ax = plt.gca()

ax.plot(x, calc(x), 'r')

ax.spines['left'].set_position('zero')
ax.spines['right'].set_color('none')
ax.spines['bottom'].set_position('zero')
ax.spines['top'].set_color('none')

plt.xlim(0, 6)
plt.ylim(0, 0.4)

plt.show()
