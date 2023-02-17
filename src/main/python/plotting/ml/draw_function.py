import matplotlib.pyplot as plt
import numpy as np

# 100 linearly spaced numbers
x = np.linspace(-5, 5, 100)

# setting the axes at the centre
fig = plt.figure()
ax = fig.add_subplot(1, 1, 1)
ax.spines['left'].set_position('center')
ax.spines['bottom'].set_position('center')
ax.spines['right'].set_color('none')
ax.spines['top'].set_color('none')
ax.xaxis.set_ticks_position('bottom')
ax.yaxis.set_ticks_position('left')

# plot the functions
plt.plot(x, x, label='y=x')
plt.plot(x, 1 / (1 + np.exp(-x)), 'c', label='y=sigmod(x)')
plt.plot(x, np.maximum(x, 0), 'r', label='y=ReLu(x)')
plt.plot(x, np.tanh(x), 'y', label='y=Tanh(x)')

plt.legend(loc='upper left')

# show the plot
plt.show()
