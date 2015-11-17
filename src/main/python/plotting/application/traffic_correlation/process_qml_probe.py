from common.helper import HMS_to_secs
from plotting import plot_histogram

DATA_DIR = 'E:/workspace/java/TrafficQualityCorrelatinAnalysis/data/res/v5/'


# QML_SOURCE_DIR='C:/Users/shuoma/Desktop/QML_Arterial_Parser_ListTMCs_AllProbes/'

def read_pairs_of_interest(table_id):
  input_file_path = DATA_DIR + 'v5_20140320_' + table_id + '.txt'
  pairs = []
  with open(input_file_path) as file:
    for line in file:
      fields = line[:-1].split(',')
      pair = {}
      pair['time'] = int(fields[1]) * 180
      pair['tmc'] = fields[2]
      pair['density'] = float(fields[5])
      pair['groundtruth_spd'] = float(fields[-4])
      pair['predicted_spd'] = pair['groundtruth_spd'] - float(fields[-3])
      pair['qml_probes'] = []
      pair['raw_probes'] = []
      pair['qml_probe_ids'] = []
      pair['probe_time_interval'] = (HMS_to_secs(fields[-2]), HMS_to_secs(fields[-1]))
      pairs.append(pair)
      # print pairs
  return pairs


def extract_qml_probes(table_id, pairs):
  input_file_path = DATA_DIR + 'qml-' + table_id + '-probes.txt'
  # print pairs
  with open(input_file_path) as file:
    for line in file:
      if 'ProbePath' in line:
        continue
      for pair in pairs:
        if pair['tmc'] in line:
          fields = line.split(',')
          hms = fields[13].split(' ')[1]
          probe_timestamp = (HMS_to_secs(hms))
          if probe_timestamp >= pair['probe_time_interval'][0] and probe_timestamp <= pair['probe_time_interval'][1]:
            probe = {}
            probe['id'] = hms + '-' + fields[15] + '-' + fields[17]
            if probe['id'] in pair['qml_probe_ids']:
              continue
            probe['speed'] = round(float(fields[14]) * 1.60934, 1)
            pair['qml_probes'].append(probe)
            pair['qml_probe_ids'].append(probe['id'])

            # 106N05298 312
            if pair['tmc'] == '106N05298' and abs(probe['speed'] - 9) < 1:
              a = True
              # print line, '\n program exits',
              # sys.exit()
          break
  print
  pairs
  return pairs


def extract_raw_probes(pairs):
  density = [[1, 10], [20, 40], [40, 200]]
  for d in density:
    raw_probe = DATA_DIR + 'v5_20140320_US_density-[' + str(d[0]) + ',' + str(d[1]) + ']_wProbes.txt'
    with open(raw_probe) as file:
      matched_pair = None
      for line in file:
        if not '[' in line:  # not probe line
          matched_pair = None
          for pair in pairs:
            if pair['tmc'] in line and str(pair['time'] / 180) in line:
              matched_pair = pair
              break
        else:
          if matched_pair:
            for ele in line[1:-2].split(','):
              probe = {}
              probe['speed'] = float(ele.split('-')[-1])
              # print ele.split('-'), probe['speed']
              probe['time'] = ele.split(' ')[1].split('-')[0]
              matched_pair['raw_probes'].append(probe)
  return pairs


if __name__ == '__main__':
  date = '20140320'

  tables = ['105', '106']
  for table in tables:
    for pair in extract_raw_probes(extract_qml_probes(table, read_pairs_of_interest(table))):
      if not pair['qml_probes']:
        continue
      qml_probe_spd = []
      for probe in pair['qml_probes']:
        qml_probe_spd.append(probe['speed'])
      qml_probe_spd.sort()
      raw_probe_spd = []
      for probe in pair['raw_probes']:
        raw_probe_spd.append(probe['speed'])
      raw_probe_spd.sort()

      print
      pair['tmc'], pair['time'] / 180, pair['density'], '\n', qml_probe_spd, '\n', raw_probe_spd
      dl = ''
      dh = ''
      if pair['density'] <= 10:
        dl = '1'
        dh = '10'
      else:
        if pair['density'] <= 40:
          dl = '20'
          dh = '40'
        else:
          dl = '40'
          dh = '200'

      plot_histogram(qml_probe_spd, pair['groundtruth_spd'], pair['predicted_spd'], date + '-' + dl + '-' + dh,
                     DATA_DIR + 'figs/spd_distr/' + date + '/qml/' + date + '-' + dl + '-' + dh + '_' + str(
                       pair['tmc']) + '_' + str(pair['time'] / 180) + '.png'
                     , raw_probe_spd)
