'''
Created on Mar 11, 2014

@author: shuoma
'''
import sys

from matplotlib import pyplot as plt
import numpy as np
from scipy.optimize import curve_fit


def get_dir(version):
  return 'E:/workspace/java/TrafficQualityCorrelatinAnalysis/data/res/' + version + '/figs/'


def read_data(paras, lane_based=False, express_or_hov_lane=False, tmc=None):
  version = paras[0]
  dichotomy = paras[1]
  plotting_error = paras[2]

  file_path = get_dir(version) + version
  file_path += '_final'
  if not dichotomy:
    file_path += "_speed"
  if lane_based:
    file_path += '_laneBased'
  if express_or_hov_lane:
    file_path += '_specialLane'
  if tmc:
    file_path += '_' + tmc
  file_path += '.txt'

  xAxis = []
  yAxis = []
  yError = []
  yProbeSpdStd = []
  title = []

  with open(file_path) as data:
    for line in data:
      if '---' in line:  # condition line
        curCond = line.replace('---', '').replace('\n', '')
        if dichotomy:
          if lane_based or express_or_hov_lane:
            fields = curCond.split('-')
            if lane_based:
              prefix = ': # of lanes '
            else:
              prefix = ': '
            title.append(fields[0] + prefix + fields[1])
          else:
            title.append(curCond)
        else:
          if len(title) > 0:
            title[-1] += curCond
          title.append('Speed : ' + curCond + '~')
        print
        title[-1]
        if xAxis:  # xAxis not empty
          # print paras, [len(s) for s in xAxis],[len(s) for s in yAxis],[len(s) for s in yError]
          del xAxis[-1][-1]  # remove the last bucket of each line
          del yAxis[-1][-1]
          del yProbeSpdStd[-1][-1]
          if plotting_error:
            del yError[-1][-1]
        xAxis.append([])
        yAxis.append([])
        yError.append([])
        yProbeSpdStd.append([])
      else:
        fields = line[:-1].split(',')
        print
        fields
        xAxis[-1].append(float(fields[-5]))
        if plotting_error:
          yAxis[-1].append(float(fields[-4]))
          yError[-1].append(float(fields[-3]))
        else:  # qs
          yAxis[-1].append(float(fields[-2][:-1]))  # -1 remove character %
        yProbeSpdStd[-1].append(float(fields[-7]))

    del xAxis[-1][-1]  # remove the last bucket of each line
    del yAxis[-1][-1]
    del yProbeSpdStd[-1][-1]
    if plotting_error:
      del yError[-1][-1]
  return xAxis, yAxis, yError, yProbeSpdStd, title


def fit_func(x, a, b, c, d):
  return a * x ** 3 + b * x ** 2 + c * x + d


def plot_multiple_line_for_each_of_the_dichotomy(paras, xAxis, yAxis, yError, yProbeSpdStd, title, lane_based=False,
                                                 express_or_hov_lane=False, tmc=None):
  fitting = True  # True False
  version = paras[0]
  plotting_error = paras[2]

  dichotomy = paras[1]
  if not dichotomy:
    print
    'dichotomy has to be True'
    return

  print
  '\n', paras, [len(s) for s in xAxis], [len(s) for s in yAxis], [len(s) for s in yError], title, "\n"

  mid = len(title) / 2
  free = range(mid)
  congestion = [i + mid for i in range(mid)]

  for cond in [free, congestion]:
    fig = plt.figure(figsize=(36, 20))  # first width, then height
    vax = fig.add_subplot(1, 1, 1)
    print
    cond
    for idx in cond:
      fields = title[idx].split(':')
      # print fields
      if plotting_error:
        vax.errorbar(xAxis[idx], yAxis[idx], yError[idx], label='(Error) ' + fields[1], fmt='-o')
      else:
        if fitting:
          # linear fit
          # fit = np.polyfit(xAxis[idx], yAxis[idx], 1)
          # fit_func=np.poly1d(fit)
          # vax.plot(xAxis[idx], fit_func(xAxis[idx]), '.-', label='(QS) Fitted'+fields[1])

          popt, pcov = curve_fit(fit_func, np.array(xAxis[idx]), np.array(yAxis[idx]))
          vax.plot(np.array(xAxis[idx]), fit_func(np.array(xAxis[idx]), *popt), '.-', label='(QS) Fitted' + fields[1])
        else:
          vax.plot(xAxis[idx], yAxis[idx], '-o', label='(QS) ' + fields[1])
          # vax.plot(xAxis[idx], yProbeSpdStd[idx],  '-ob', label='(SV)  '+fields[1] )   #plot probe speed variance std

    # plt.xlabel('Probe Density')
    plt.legend(fontsize=25)

    vax.grid(True)
    vax.tick_params(axis='both', which='major', labelsize=20)

    if cond == free:
      traf_condi = "Free"
    else:
      traf_condi = "Congestion"
    plt.title(traf_condi, fontsize=30)
    plt.suptitle('Probe Counting:  ' + condition[version], fontsize=40)
    plt.savefig(get_dir(paras[0])
                + version + '_' + condition[version]
                + ('_error' if plotting_error else ('_fitted' if fitting else ''))
                + '_' + traf_condi
                + ('_laneBased' if lane_based else '')
                + ('_specialLane' if express_or_hov_lane else '')
                + ('_' + tmc if tmc else '')
                + '.png'
                )
    # plt.show()


def plot_scatter(paras, xAxis, yAxis, yError, yProbeSpdStd, title):
  version = paras[0]
  dichotomy = paras[1]
  plotting_error = paras[2]
  lane_based = paras[3]

  print
  paras, [len(s) for s in xAxis], [len(s) for s in yAxis], [len(s) for s in yError], title, "\n"

  fig = plt.figure(figsize=(36, 20))  # first width, then height
  for idx in range(len(title)):
    if not dichotomy:
      vax = fig.add_subplot(2, len(title) / 2, idx + 1)
    else:
      if lane_based:
        vax = fig.add_subplot(2, len(title) / 2, idx + 1)
      else:
        vax = fig.add_subplot(1, 2, idx + 1)
    if plotting_error:
      vax.errorbar(xAxis[idx], yAxis[idx], yError[idx], label='prediction error', fmt='-ok')
    else:
      vax.plot(xAxis[idx], yAxis[idx], '-ok', label='standard quality score')
    vax.plot(xAxis[idx], yProbeSpdStd[idx], '-ob', label='std of probe speed')  # plot probe speed variance std

    # plt.xlabel('Probe Density')

    # plt.legend(fontsize=27)
    vax.grid(True)
    vax.tick_params(axis='both', which='major', labelsize=20)
    plt.title(title[idx], fontsize=30)

  plt.suptitle('Probe Counting:  ' + condition[version], fontsize=40)
  plt.savefig(get_dir(paras[0])
              + version + '_' + condition[version]
              + ('_error' if plotting_error else '')
              + ('_speedY' if not dichotomy else '')
              + ('_laneBased' if lane_based else '')
              )
  # plt.show()


def scatter_plot():
  plotting_error = [True, False]
  dichotomy = [True]  # , False] #true if congestion/free-flow dichotomy else  speed based condition
  lane_based = [True]  # True, False

  tmc_value = ''  # ''
  use_no_of_lane = False
  use_express_and_hov_lane = True

  for dich in dichotomy:
    for yAxisChoice in plotting_error:
      for ver in condition.keys():
        paras = [ver, dich, yAxisChoice]
        xAxis, yAxis, yError, yProbeSpdStd, title = read_data(paras, lane_based=use_no_of_lane,
                                                              express_or_hov_lane=use_express_and_hov_lane,
                                                              tmc=tmc_value)
        # plot_scatter(paras, xAxis, yAxis, yError, yProbeSpdStd, title)
        plot_multiple_line_for_each_of_the_dichotomy(paras, xAxis, yAxis, yError, yProbeSpdStd, title,
                                                     lane_based=use_no_of_lane,
                                                     express_or_hov_lane=use_express_and_hov_lane, tmc=tmc_value)


def plot_histogram(data, ground_truth, file, line):
  spd = map(float, data[:-1].split(','))
  ground_truth_spd = float(ground_truth)
  # print spd, '\n', ground_truth_spd, '\n'

  col = 'b'
  dl = file.split('-')[1]
  if "1" in dl:
    col = 'g'
  else:
    if "20" in dl:
      col = 'b'
    else:
      col = 'k'
  n, bins, patches = plt.hist(spd, bins=(max(spd) - min(spd)) / 5, color=col)
  # print n
  plt.vlines(ground_truth_spd, 0, max(n), 'r')
  plt.savefig('figs/spd_distr/' + file.split('-')[0] + '/' + file + '_' + line + '_' + str(ground_truth_spd) + '.png')


if __name__ == '__main__':
  # print str(sys.argv)
  plot_histogram(sys.argv[1], sys.argv[2], sys.argv[3], sys.argv[4])

  condition = {}
  # condition['v7']='filterY_shiftN'
  # condition['v6']='filterN_shiftY'
  condition['v5'] = 'filterY_shiftY'
  # condition['v4']='filterN_shiftN'
  # plot_scatter()
