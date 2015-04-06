'''
Created on Mar 14, 2014

@author: Shuo
'''
from matplotlib import pyplot as plt
import numpy as np
from mpl_toolkits.mplot3d import Axes3D
import pylab as pl


def surface():
    x = np.arange(-5, 5, 0.25)
    y = np.arange(-5, 5, 0.25)
    X, Y = np.meshgrid(x, y)
    F = 3 + 2 * X + X * Y + 5 * X * X

    fig = plt.figure()
    ax = fig.add_subplot(111, projection='3d')
    ax.plot_surface(X, Y, F)
    plt.show()


def linear():
    x = np.linspace(-1, 1, 100)
    pl.plot(x, 1 - x, x, 2 * x, x, (x - 1) / 2)
    pl.axvline(1. / 3, color='purple')

    pl.axhline(0, color='black')
    pl.axvline(0, color='black')

    pl.grid()
    pl.show()


def contour():
    xvec = np.linspace(-5., 5., 100)
    x, y = np.meshgrid(xvec, xvec)
    z = -np.hypot(x, y)
    plt.contourf(x, y, z, 100)
    plt.colorbar()
    plt.show()


if __name__ == '__main__':
    # linear()
    # surface()
    contour()
