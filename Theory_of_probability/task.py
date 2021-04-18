import matplotlib.pyplot as plt
from matplotlib import cm
from matplotlib.ticker import LinearLocator
import numpy as np
import math
import random

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
#     return -(5 * (11 * (t ** 5) / 5 + 35 * (t ** 4) + 208 * (t ** 3) + 544 * (t ** 2) + 512 * t) / 2916) + 0.0878

# def calc2(t):
#     return -(20 * (8 * (t ** 5) / 5 - 97 * (t ** 4) / 4 + 85 * (t ** 3) + 725 * (t ** 2) / 2 - 2875 * t) / 250047) + 0.612

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

# x = np.linspace(-1.0, 10.0, num=100)

# def calc(xx1):
#     return (-37 + 7 * xx1) * (9 - 9 * xx1) * (-32 + 2 * xx1) / -21276

# ax = plt.gca()

# ax.plot(x, calc(x), 'r')

# ax.spines['left'].set_position('zero')
# ax.spines['right'].set_color('none')
# ax.spines['bottom'].set_position('zero')
# ax.spines['top'].set_color('none')

# plt.xlim(0, 6)
# plt.ylim(0, 0.4)

# plt.show()

#########################################

# x1 = np.linspace(-4.0, 5.0, num=100)

# def calc(xx1):
#     return 1 / 63 * (92 * xx1 - 10 * np.sqrt(67 * (xx1 ** 2) + (338 * xx1) + 604) + 422)

# ax = plt.gca()

# ax.plot(x1, calc(x1), 'r')

# ax.spines['left'].set_position('zero')
# ax.spines['right'].set_color('none')
# ax.spines['bottom'].set_position('zero')
# ax.spines['top'].set_color('none')

# plt.xlim(-5, 6)
# plt.ylim(-3, 7)

# plt.show()

#########################################

# x1 = np.linspace(-3.9, -2, num=1000)
# x2 = np.linspace(-2, 4.9, num=1000)

# def calc1(x):
#     return 2 * \
#         (-46 - np.sqrt(604 + 67 * x ** 2 + 338 * x) - 16 * x) * \
#         (-38 + np.sqrt(604 + 67 * x ** 2 + 338 * x) - 5 * x) * \
#         (8 - np.sqrt(604 + 67 * x ** 2 + 338 * x) + 11 * x) / \
#         (735 * (4 + x) ** 3 * (8 + 11 * x))

# def calc2(x):
#     return 7 * \
#         (-46 - np.sqrt(604 + 67 * x ** 2 + 338 * x) - 16 * x) * \
#         (-38 + np.sqrt(604 + 67 * x ** 2 + 338 * x) - 5 * x) * \
#         (8 - np.sqrt(604 + 67 * x ** 2 + 338 * x) + 11 * x) / \
#         (120 * (-5 + x) ** 3 * (23 + 8 * x))

# ax = plt.gca()

# ax.plot(x1, calc1(x1), 'r')
# ax.plot(x2, calc2(x2), 'r')

# ax.spines['left'].set_position('zero')
# ax.spines['right'].set_color('none')
# ax.spines['bottom'].set_position('zero')
# ax.spines['top'].set_color('none')

# plt.xlim(-5, 6)
# plt.ylim(-1, 10)

# plt.show()

#########################################

# def w1(x, y):
#     return 2 * x + 7 * y - 38

# def w2(x, y):
#     return 6 * x - 9 * y + 6

# def w3(x, y):
#     return -8 * x + 2 * y - 28

# def within(x, y):
#     w1Val = w1(x, y)
#     w2Val = w2(x, y)
#     w3Val = w3(x, y)

#     if w1Val < 0 and w2Val < 0 and w3Val < 0:
#         return w1Val * w2Val * w3Val / 108000
#     else:
#         return 0

# count = 0
# limit = 1000

# while (count < limit):
#     x = random.uniform(-4, 5)
#     y = random.uniform(-2, 4)

#     if (within(x, y)):
#         plt.scatter([x], [y])
#         count += 1

# ax = plt.gca()

# ax.spines['left'].set_position('zero')
# ax.spines['right'].set_color('none')
# ax.spines['bottom'].set_position('zero')
# ax.spines['top'].set_color('none')

# plt.xlim(-5, 6)
# plt.ylim(-3, 7)

# plt.show()

#########################################

x = np.linspace(-4.0, 5.0, num=100)
y = []

def upper(t):
    if (t >= -2):
        return -2 / 7 * t + 38 / 7
    else:
        return 4 * t + 14

def lower(t):
    return 6 / 9 * t + 6 / 9

def inverse1(t):
    return -(5 * (11 * (t ** 5) / 5 + 35 * (t ** 4) + 208 * (t ** 3) + 544 * (t ** 2) + 512 * t) / 2916) + 0.0878

def inverse2(t):
    return -(20 * (8 * (t ** 5) / 5 - 97 * (t ** 4) / 4 + 85 * (t ** 3) + 725 * (t ** 2) / 2 - 2875 * t) / 250047) + 0.612

def max1(t):
        return 2 * \
            (-46 - np.sqrt(604 + 67 * t ** 2 + 338 * t) - 16 * t) * \
            (-38 + np.sqrt(604 + 67 * t ** 2 + 338 * t) - 5 * t) * \
            (8 - np.sqrt(604 + 67 * t ** 2 + 338 * t) + 11 * t) / \
            (735 * (4 + t) ** 3 * (8 + 11 * t))

def max2(t):
    return 7 * \
        (-46 - np.sqrt(604 + 67 * t ** 2 + 338 * t) - 16 * t) * \
        (-38 + np.sqrt(604 + 67 * t ** 2 + 338 * t) - 5 * t) * \
        (8 - np.sqrt(604 + 67 * t ** 2 + 338 * t) + 11 * t) / \
        (120 * (-5 + t) ** 3 * (23 + 8 * t))

def marginal1(t):
    return abs((5 * ((t + 4) ** 3) * (11 * t + 8)) / 2916)

def marginal2(t):
    return abs((20 * ((t - 5) ** 3) * (8 * t + 23)) / 250047)

def inverse(t):
    if (t < -4):
        return 0

    if (t < -2):
        return inverse1(t)

    if (t < 5):
        return inverse2(t)

    return 1

def max(t):
    if (t < -3.9):
        return 0

    if (t < -2):
        return max1(t)

    if (t < 4.9):
        return max2(t)

    return 0

def marginal(t):
    if (t < -2):
        return marginal1(t)
    else:
        return marginal2(t)

for xx in x:
    yy = inverse(xx)
    y.append((xx, yy))

count = 0
limit = 1000

while (count < limit):
    num = random.uniform(0, 1)
    xxx = 0

    for i in range(0, len(y)):
        if (y[i][1] >= num):
            xxx = y[i][0]
            break
    
    maxBound = max(xxx)    
    bound = random.uniform(0, maxBound)   

    if (bound <= marginal(xxx)):
        yyy = random.uniform(lower(xxx), upper(xxx))
        plt.scatter([xxx], [yyy])
        count += 1

ax = plt.gca()

ax.spines['left'].set_position('zero')
ax.spines['right'].set_color('none')
ax.spines['bottom'].set_position('zero')
ax.spines['top'].set_color('none')

plt.xlim(-5, 6)
plt.ylim(-3, 7)

plt.show()
