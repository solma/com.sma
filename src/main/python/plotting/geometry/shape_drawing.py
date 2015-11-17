"""
Created on Jan 1, 2015

@author: solma
"""

from pylab import *


def setup_figure():
  figure(figsize=(8, 8))
  bound = 20
  xlim(-bound, bound)
  ylim(-bound, bound)
  return plt.gcf()


if __name__ == "__main__":
  fig = setup_figure()

  # draw a circle
  circle = plt.Circle((0., 0.), 1, color='r')
  fig.gca().add_artist(circle)

  # draw a line
  l = plt.Line2D([2, .19], [2, -2])
  fig.gca().add_artist(l)

  # draw a polygon
  polygon = plt.Polygon([[0, 0], [0, 10], [10, 10], [6, 6], [10, 0]], fill=None, edgecolor='b')
  fig.gca().add_artist(polygon)

  plt.scatter([5, 10, 5, 10], [10, 15, 5, 5], s=[10], c=['r'], alpha=0.5)

  plt.grid()
  fig.savefig('/Users/solma/Desktop/geometry.png')
