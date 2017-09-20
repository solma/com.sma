'''
Created on Sep 18, 2013

@author: Shuo
'''
from pylab import *
from scipy.io.wavfile import read
from mathproblems import *


def plotSpectrogram(data, Fs):
  """
  Plots a Single-Sided Amplitude Spectrum of y(t)
  """
  n = len(data)

  k = arange(n)
  T = n / Fs  # span
  frq = k / T  # two sides frequency range
  # frq =  fft.fftfreq(n) *Fs #equivalent to the above three lines

  frq = frq[range(n / 2)]  # one side frequency range
  # print max(frq)

  """
  same result as Java.Jtransform.DoubleFFT_1D.realForward
  the only difference lies in the output:
  jtransform first outputs the first and last element, and then middle elements
  fft.rfft first outputs the first element, then middle elements and the last element
  """
  Y = fft.rfft(data) / n  # fft computing and normalization
  print
  n, len(Y), Y[:10], Y[-10:]
  Y = Y[range(n / 2)]

  plot(frq, abs(Y), 'r')  # plotting the spectrum
  xlabel('Freq (Hz)')
  ylabel('Amplitude')


def read_recording(f_name):
  signal = []
  with open(f_name, 'r') as lines:
    for line in lines:
      signal.append(int(line[:-1]))
  return signal


if __name__ == '__main__':
  Fs = 16000;  # SAMPLING RATE

  # """
  # http://glowingpython.blogspot.ro/2011/08/how-to-plot-frequency-spectrum-with.html
  # """
  # 'snoring'
  wav_file_name = 'doorOpenAndClosed5'
  rate, data = read(
    'E:/workspace/android/ActivityRecognition/data/audio/raw/' + wav_file_name + '.wav')  # equivalent to the  WaveData class
  span = len(data) / Fs  # duration
  t = linspace(0, span, len(data))
  print
  data[-10:]

  # normalization by absolute amplitude
  data_array = array(data, dtype=float)
  data_array = abs(data_array)
  data_array /= abs(max(data))

  # draw the amplitude over time-bin
  fig = plt.figure()
  fig.add_subplot(211)

  plot(t, data_array)
  xlabel('Time')
  ylabel('Amplitude')

  subplot(2, 1, 2)
  plotSpectrogram(data, Fs)

  plt.savefig('E:/workspace/android/ActivityRecognition/data/audio/figs/' + wav_file_name)
  """
  Draw Hamming Window
  """
#     N=200
#     n=array(range(1, N+1), dtype=float)
#     alpha=0.53836
#     n=alpha-(1-alpha)*ncos(2*math.pi*n/(N-1))
#
#     fig=plt.figure()
#     fig.add_subplot(211)
#     plot(range(N), n)
#     xlabel('samples')
#     ylabel('amplitude')


# subplot(212)


# show()
