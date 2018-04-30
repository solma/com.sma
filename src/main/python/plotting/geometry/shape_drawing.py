import pylab

def setup_figure(bounds=[20] * 4):
  pylab.figure(figsize=(10, 10))
  pylab.xlim(bounds[0], bounds[1])
  pylab.ylim(bounds[2], bounds[3])
  return pylab.plt.gcf()

if __name__ == "__main__":
  fig = setup_figure()

  # draw a circle
  circle = pylab.plt.Circle((0., 0.), 1, color='r')
  fig.gca().add_artist(circle)

  # draw a line
  l = pylab.plt.Line2D([2, .19], [2, -2])
  fig.gca().add_artist(l)

  # draw a polygon
  polygon = pylab.plt.Polygon([[0, 0], [0, 10], [10, 10], [6, 6], [10, 0]], fill=None, edgecolor='b')
  fig.gca().add_artist(polygon)

  pylab.plt.scatter([5, 10, 5, 10], [10, 15, 5, 5], s=[10], c=['r'], alpha=0.5)

  pylab.plt.grid()
  pylab.plt.show()
  fig.savefig('/home/solma/geometry.png')
