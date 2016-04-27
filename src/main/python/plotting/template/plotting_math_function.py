from mpl_toolkits.mplot3d import axes3d
from matplotlib import cm
from matplotlib import pyplot as plt
import numpy as np
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

def contour3d():
  fig = plt.figure()
  ax = fig.gca(projection='3d')
  X, Y, Z = axes3d.get_test_data(0.05)
  ax.plot_surface(X, Y, Z, rstride=8, cstride=8, alpha=0.3)
  ax.contour(X, Y, Z, zdir='z', offset=-100, cmap=cm.coolwarm)
  ax.contour(X, Y, Z, zdir='x', offset=-40, cmap=cm.coolwarm)
  ax.contour(X, Y, Z, zdir='y', offset=40, cmap=cm.coolwarm)

  ax.set_xlabel('X')
  ax.set_xlim(-40, 40)
  ax.set_ylabel('Y')
  ax.set_ylim(-40, 40)
  ax.set_zlabel('Z')
  ax.set_zlim(-100, 100)

  plt.show()

if __name__ == '__main__':
  # linear()
  # surface()
  # contour()
  contour3d()
