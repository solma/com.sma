'''
Created on Mar 11, 2014

@author: shuoma
'''




"""
Unit Conversion Help Functions
"""
def HMS_to_secs(time_in_HMS):
    hour_min_sec=time_in_HMS.split(':')
    ret=int(hour_min_sec[0])*3600+int(hour_min_sec[1])*60
    if len(hour_min_sec)>2:
        ret+=int(hour_min_sec[2])
    return ret

def HMSS_to_millisecs(time_in_HMSS):
    hour_min_sec_millisec=time_in_HMSS.split(':')
    ret=(int(hour_min_sec_millisec[0])*3600+int(hour_min_sec_millisec[1])*60)*1000
    if len(hour_min_sec_millisec)>2:
        ret+=int(hour_min_sec_millisec[2])*1000
    if len(hour_min_sec_millisec)>3:
        ret+=int(hour_min_sec_millisec[3])
    return ret


"""
NOTE: x is in millisecond
"""
def millisecs_to_HMS(time_in_millisecs, pos=0, show_sec=True): # only able to show at minute level
    'The two args are the value and tick position'
    hour=int(time_in_millisecs/1000/3600)
    minute=int( (time_in_millisecs-hour*3600*1000)/1000/60)
    second=int( (time_in_millisecs-hour*3600*1000-minute*60*1000)/1000)
    if minute<10:
        min_str='0'+str(minute)
    else:
        min_str=str(minute)
    if second<10:
        sec_str='0'+str(second)
    else:
        sec_str=str(second)
    #print time_in_millisecs, hour, min_str
    if show_sec:
        return '%d:%s:%s' %(hour, min_str, sec_str)
    else:
        return '%d:%s' %(hour, min_str)