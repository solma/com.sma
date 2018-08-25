import numpy as np
import pylab
import cos_module_np

x = np.arange(0, 2 * np.pi, 0.1)
y = np.empty_like(x)

cos_module_np.cos_np_func(x, y)
pylab.plot(x, y)
pylab.show()
