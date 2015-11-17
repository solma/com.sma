from pylab import *

x = linspace(-5, 5, 300)
y = x
X, Y = meshgrid(x, y)
Z = bivariate_normal(X, Y)

for cmap in ['Blues']:
  fig = figure()
  ax = fig.add_subplot(111, projection='3d')

  # make grids invisible
  ax.grid(False)
  for a in (ax.w_xaxis, ax.w_yaxis, ax.w_zaxis):
    for t in a.get_ticklines() + a.get_ticklabels():
      t.set_visible(False)
    a.line.set_visible(False)
    a.pane.set_visible(False)

  ax.plot_surface(X, Y, Z, cmap=cmap, linewidth=0, alpha=0.7)
  # title(cmap)

plt.show()
