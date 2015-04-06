'''
Created on Jan 1, 2015

@author: solma
'''

import matplotlib.pyplot as plt
from pylab import *
circle2 = plt.Circle((0., 0.), 1, color='r')
l = plt.Line2D([2, .19], [2, -2])
figure(figsize=(8,8))
fig = plt.gcf()
fig.gca().add_artist(circle2)
fig.gca().add_artist(l)

bound = 3
xlim(-bound, bound)
ylim(-bound, bound)
plt.show()
# fig.savefig('plotcircles.png')



