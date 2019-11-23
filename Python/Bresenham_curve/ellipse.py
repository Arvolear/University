import cv2 as cv
import numpy as np
import math

size = 1000
scale = 10

def drawGrid(image):
    for i in range(round(size / scale)):
        for j in range(round(size / scale)):
            # vertical
            image[i * scale : (i + 1) * scale, j * scale] = [255, 255, 255]
            # horizontal
            image[i * scale, j * scale : (j + 1) * scale] = [255, 255, 255]

    # right border
    image[: size - 1, size - 1] = [255, 255, 255]
    # bottom border
    image[size - 1, : size - 1] = [255, 255, 255]

def putPixel(image, py, px, color):
    py = int(py)
    px = int(px)

    # square
    image[px * scale : (px + 1) * scale, py * scale : (py + 1) * scale] = color
    # right border
    image[px * scale : (px + 1) * scale, (py + 1) * scale] = color
    # bottom border
    image[(px + 1) * scale, py * scale : (py + 1) * scale] = color

def drawBresenhamEllipse(image, centerx, centery, a, b, color):
    x, y = -a, 0
    e2, err = 0, x * (2 * b * b + x) + b * b

    while x <= 0:
        putPixel(image, centerx - x, centery + y, color)
        putPixel(image, centerx + x, centery + y, color)
        putPixel(image, centerx + x, centery - y, color)
        putPixel(image, centerx - x, centery - y, color)

        e2 = 2 * err

        if e2 >= (x * 2 + 1) * b * b:
            x += 1
            err += (x * 2 + 1) * b * b

        if e2 <= (y * 2 + 1) * a * a:
            y += 1
            err += (y * 2 + 1) * a * a

    while y < b:
        putPixel(image, centerx, centery + y, color)
        putPixel(image, centerx, centery - y, color)
        y += 1

def drawEquationEllipse(image, centerx, centery, a, b, color):
    j = float(centerx - a)

    while (j <= centerx + a):
        x = round(j)
        y1 = round(math.sqrt(b * b * (1 - ((j - centerx) * (j - centerx)) / (a * a)))) + centery
        y2 = -round(math.sqrt(b * b * (1 - ((j - centerx) * (j - centerx)) / (a * a)))) + centery

        putPixel(image, x, y1, color)
        putPixel(image, x, y2, color)

        j += 0.01

# create image
image = np.zeros((size, size, 3), np.uint8)

#drawGrid(image)

# centerx, centery, a, b, color

print("Please enter Bresenham ellipse center (x;y) and axis (a;b)")

x = int(input())
y = int(input())
a = int(input())
b = int(input())

drawBresenhamEllipse(image, x, y, a, b, [100, 126, 145])


print("Please enter Equation ellipse center (x;y) and axis (a;b)")

x = int(input())
y = int(input())
a = int(input())
b = int(input())

drawEquationEllipse(image, x, y, a, b, [145, 126, 100])

cv.imshow("image", image)

cv.waitKey(0)
cv.destroyAllWindows()
