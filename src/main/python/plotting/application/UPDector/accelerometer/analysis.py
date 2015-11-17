activity_log_file_name = 'E:/workspace/android/ActivityRecognition/logs/activityrecognition2013_08_1737.log'

"""
Helper Function
"""


def HMS_to_secs(time_in_HMS):
  hour_min_sec = time_in_HMS.split(':')
  return int(hour_min_sec[0]) * 3600 + int(hour_min_sec[1]) * 60 + int(hour_min_sec[2])


confidence_threshold = 0  # throw away all states with a confidence below the threshold

activity_type = ['in_vehicle', 'on_foot', 'on_bicycle', 'still']

ACTIVITY_FIELD_IDX = 4
CONFIDENCE_FIELD_IDX = 6

"""
read the log, build the arrays
"""
NUM_ACTIVITY_TYPE = len(activity_type)

timestamps = [[] for i in range(NUM_ACTIVITY_TYPE)]
confidences = [[] for i in range(NUM_ACTIVITY_TYPE)]

# ground truth parameters
ground_truth_time_duration = [[] for i in range(NUM_ACTIVITY_TYPE)]

time_span = [3600 * 1000 * 24, 0, 0]  # time span in the log
ground_truth_set = False
with open(activity_log_file_name) as activity_log_file:
  geton_prev_line = ""
  prev_type = ""
  getoff_prev_cnt = ""
  getoff_prev_lines = ""
  for line in activity_log_file:
    if line[0] == '#':  # a ground truth line
      ground_truth_set = True
      for i in range(NUM_ACTIVITY_TYPE):
        if activity_type[i] in line:  # in_vehicle
          periods = line[:-1].split(' ')[2:]
          for period in periods:
            start_time, end_time = period.split('~')
            ground_truth_time_duration[i].append(
              (HMS_to_secs(start_time + ":00") * 1000, HMS_to_secs(end_time + ":00") * 1000))
          break
    else:  # a state line
      fields = line[:-1].split(' ')
      if (int(fields[CONFIDENCE_FIELD_IDX]) < confidence_threshold or fields[ACTIVITY_FIELD_IDX] not in activity_type):
        continue;
      # fields[5] is the type
      for i in range(len(activity_type)):
        if fields[ACTIVITY_FIELD_IDX] == activity_type[i]:
          idx = i
          if fields[ACTIVITY_FIELD_IDX] == 'in_vehicle' and prev_type == 'on_foot':
            print
            "get on: \n" + geton_prev_line + line + "\n"
          if fields[ACTIVITY_FIELD_IDX] == 'on_foot' and getoff_prev_cnt == 3:
            print
            "get off: \n" + getoff_prev_lines
          if "09:01:06" in line:
            a = 1;
          break

      # print idx,  fields[7], line

      confidences[idx].append(int(fields[CONFIDENCE_FIELD_IDX]))

      tmp = fields[1].split(';;')  # fields[1] is time
      millisec = HMS_to_secs(tmp[0]) * 1000
      timestamps[idx].append(millisec)
      # update time span
      time_span[1] = max(time_span[1], millisec)
      time_span[0] = min(time_span[0], millisec)

      """
      detect get off
      """
      if fields[ACTIVITY_FIELD_IDX] == 'on_foot' and prev_type == 'in_vehicle':
        getoff_prev_cnt = 1
        getoff_prev_lines = line
      else:
        if fields[ACTIVITY_FIELD_IDX] == 'on_foot' and prev_type == 'on_foot':
          getoff_prev_cnt += 1
          getoff_prev_lines += line
        else:
          getoff_prev_cnt = 0
          geoff_prev_lines = ""

      """
      detect get on
      """
      geton_prev_line = line
      prev_type = fields[ACTIVITY_FIELD_IDX]



# print timestamps[0][:10], confidences[0][:10]
print
ground_truth_time_duration
