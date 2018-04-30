import numpy as np
import random as rand
import matplotlib.pyplot as plt
import matplotlib.gridspec as gridspec

def PlotScatterPlotAndHistogram(plot_path, ops, indices, y_values, joint_y_label,
                                margin_x_label):
  x, y = indices, y_values
  fig = plt.figure()
  gs = gridspec.GridSpec(6, 6)
  ax_joint = fig.add_subplot(gs[1:6, 1:4])
  # set limits for the newly added subplot, assuming all(y>0)
  plt.ylim(-min(y) * 1.05, max(y) * 1.05)
  ax_marg_y = fig.add_subplot(gs[1:6, 4:6])

  ax_joint.scatter(x, y, s=y)
  for i in range(6, len(ops)):
    ax_joint.annotate(ops[i], xy=(x[i], y[i]))
  ax_marg_y.hist(y, orientation='horizontal')

  # Turn off tick labels on marginals
  plt.setp(ax_marg_y.get_yticklabels(), visible=False)

  # Set labels on joint
  ax_joint.set_ylabel(joint_y_label)

  # Set labels on marginals
  ax_marg_y.set_xlabel(margin_x_label)
  ax_joint.set_title('')

  plt.show()
  fig.savefig(plot_path)

N = 10
ops = ['op' + str(i) for i in range(N)]
ys = np.array([rand.randint(0, 100) for _ in range(N)])
xs = np.array(range(len(ys)))
PlotScatterPlotAndHistogram('/home/solma/Desktop/scatter.png', ops, xs,
                            ys, 'Compatibility Percent',
                            '# of models')
