import pylab

from alg.label import *
from plotting.geometry.shape_drawing import setup_figure

Label(Label.MonotonicSequence, Label.Stack, Label.DaiZhiGuiZhong)


class LargestRectangleIn2DHistogram(object):
  def draw_histogram(self, heights, start_idx, n, rightmost):
    bounds = [-2, rightmost + 2, -2, max(heights) + 2]
    fig = setup_figure(bounds)
    for i in range(n):
      end_idx = start_idx[i + 1] if i < n - 1 else rightmost
      corners = [[start_idx[i], 0], [start_idx[i], heights[i]], [end_idx, heights[i]], [end_idx, 0]]
      polygon = pylab.plt.Polygon(corners, fill=None, edgecolor='b')
      fig.gca().add_artist(polygon)
    pylab.plt.grid()
    pylab.plt.show()

  def largest_rectangle(self, heights, widths):
    assert len(heights) == len(widths)
    n = len(heights)

    max_rectangle = 0
    stck = []
    start_idx = [0] + widths[:-1]
    for i in range(1, n):
      start_idx[i] += start_idx[i - 1]
    rightmost = start_idx[n - 1] + widths[n - 1]
    print(start_idx, widths)

    for column in zip(heights, start_idx):
      if not stck or stck[-1][0] <= column[0]:
        stck.append(column)
      else:
        last_popped_column = stck[-1]
        while stck and stck[-1][0] > column[0]:
          max_rectangle = max(max_rectangle, column[0] * (column[1] - stck[-1][1]))
          last_popped_column = stck.pop()
        stck.append((column[0], last_popped_column[1]))

    while stck:
      last_popped_column = stck.pop()
      max_rectangle = max(max_rectangle,
                          last_popped_column[0] * (rightmost - last_popped_column[1]))
    self.draw_histogram(heights, start_idx, n, rightmost)
    return max_rectangle


ins = LargestRectangleIn2DHistogram()
print(ins.largest_rectangle([1, 2, 3, 2, 1], [2, 1, 2, 1, 2]))
