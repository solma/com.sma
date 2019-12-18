import numpy as np
import matplotlib.pyplot as plt

def mandelbrot(h, w, max_it=50):
  """Returns an image of the Mandelbrot fractal of size (h,w)."""
  y, x = np.ogrid[-1.4:1.4:h * 1j, -2:0.8:w * 1j]
  c = x + y * 1j
  z = c  # f(z) = z^2 + c:  f(0) => c
  div_time = max_it + np.zeros(z.shape, dtype=int)

  for i in range(max_it):
    z = z ** 2 + c
    diverge = (z * np.conj(z)) > (2 ** 2)  # who is diverging
    div_now = diverge & (div_time == max_it)  # who is diverging now
    div_time[div_now] = i  # note when
    z[diverge] = 2  # avoid diverging too much

  return div_time

plt.imshow(mandelbrot(1000, 1000))
plt.show()
