from matplotlib import pyplot as plt
from matplotlib.ticker import FuncFormatter
from pylab import *
from numpy import *
from os.path import *


from common.helper import *


"""
Draw activity histograms
"""
def draw_activity_plot():
    """
    Parameters for log file
    """
    activity_log_file_name='E:/workspace/android/UPDetector/data/accelerometer/raw/activityrecognition2013_08_18234.log'
    
    confidence_threshold=0 #throw away all states with a confidence below the threshold
    
    ACTIVITY_FIELD_IDX=4
    CONFIDENCE_FIELD_IDX=6
    
    """
    read the log, build the arrays
    """
    NUM_ACTIVITY_TYPE=len(activity_type)
    
    timestamps=[[] for i in range(NUM_ACTIVITY_TYPE) ]
    confidences=[[] for i in range(NUM_ACTIVITY_TYPE) ]

    # ground truth parameters
    ground_truth_time_duration=[[] for i in range(NUM_ACTIVITY_TYPE)]
    
    
    time_span=[3600*1000*24,0, 0] #time span in the log
    ground_truth_set=False
    with open(activity_log_file_name) as activity_log_file:
        for line in activity_log_file:
            if line[0]=='#': #a ground truth line
                ground_truth_set=True
                for i in range(NUM_ACTIVITY_TYPE):
                    if activity_type[i] in line: # in_vehicle
                        periods=line[:-1].split(' ')[2:]
                        for period in periods:
                            #print periods
                            start_time, end_time=period.split('~')
                            ground_truth_time_duration[i].append( (HMS_to_secs(start_time)*1000,HMS_to_secs(end_time)*1000) )
                        break            
            else:# a state line
                fields=line[:-1].split(' ')
                if int(fields[CONFIDENCE_FIELD_IDX])<confidence_threshold or fields[ACTIVITY_FIELD_IDX] not in activity_type:
                    continue;
                #fields[5] is the type
                for i in range(len(activity_type)):
                    if fields[ACTIVITY_FIELD_IDX]==activity_type[i]:
                        idx=i
                        break
                #print idx,  fields[7], line
                
                confidences[idx].append(int(fields[CONFIDENCE_FIELD_IDX])) 
                
                tmp=fields[1].split(';;')  #fields[1] is time
                millisec=HMS_to_secs(tmp[0])*1000
                timestamps[idx].append(millisec)
                # update time span
                time_span[1]= max(time_span[1], millisec)
                time_span[0]=min(time_span[0], millisec)
      
    #print timestamps[0][:10], confidences[0][:10]
    print ground_truth_time_duration
    
    
    """
    draw the graph
    """
    fig = plt.figure(figsize=(12, 6))
    vax = fig.add_subplot(111)
      
    vlines=[]
    for i in range(NUM_ACTIVITY_TYPE):
        vlines.append(vax.vlines(timestamps[i] ,[confidence_threshold], confidences[i], colors=colors[i])) #linestyles=line_styles[i])
    
    
    ybottom_extra=(100-confidence_threshold)/10
    bars=[]
    #draw the bars of the ground truth
    if ground_truth_set:
        for i in range(NUM_ACTIVITY_TYPE):
            for period in ground_truth_time_duration[i]: # not empty
                bars.append(plt.bar(period[0], 100-(confidence_threshold-ybottom_extra), width=period[1]-period[0], bottom=(confidence_threshold-ybottom_extra), color=ground_truth_bg_color[i]) )
                vax.annotate('', xy=( period[0]+(period[1]-period[0])/2, confidence_threshold-ybottom_extra/2))
      
     
    """
    Format part
    """
    vax.set_xlabel('time', fontsize=20)
    vax.set_ylabel('confidence %',  fontsize=20)
    vax.set_title('Activities', fontdict={'fontsize': 20})
    vax.set_ylim(bottom=confidence_threshold-ybottom_extra)
      
      
    time_span[2]=time_span[1]-time_span[0] #duration of the whole log     
    offset=0.02
    vax.set_xlim(left=time_span[0]-time_span[2]*offset, right=time_span[1]+time_span[2]*offset)
      
    #generate legends
    classfied=legend(vlines, activity_type, title='', fontsize=20)
    classfied.draggable(state=True)
    
    #===============================================================================
    # ground_truth=legend(bars, activity_type, title='Activity: Ground Truth')
    # ground_truth.draggable(state=True)
    #===============================================================================
    
    # modify the tickers of x axis  
      
    vax.xaxis.set_major_locator( LinearLocator(numticks=10) )
      
    vax.xaxis.set_major_formatter(FuncFormatter(millisecs_to_HMS))
    
    for tick in vax.xaxis.get_majorticklabels():
        tick.set_fontsize(20)
    for tick in vax.yaxis.get_majorticklabels():
        tick.set_fontsize(20)
    
    plt.show()
    


"""
determine if timestamp is an time at which the activity is changing; if so, if it is an ending/starting time of an activity
"""
def is_activity_changing(ground_truth_time_duration, timestamp):
    ret=[]
    # i is the index to activities
    for i in range(len(ground_truth_time_duration)):
        for period in ground_truth_time_duration[i]:
            if period[0]==timestamp:
                ret.append( (i, True)  ) #true means the activity starts
            else:
                if period[1]==timestamp:
                    ret.append( (i, False) )
    return ret

"""
Draw data
@param: idx_of_ploting_fields: index of all fields to be plotted
@param: name_of_ploting_fields: names of all fields to be plotted
@param: plot_type_of_plotting_fields:
        DOT: draw a dot at each value on X axis
      VLINE: draw a vertical line at each value on X axis
        
@param: index_of_time_field: index of field that represents time

@param: x_axis_type: 
        NUM:  index of x axis is natural number, i.e. 1,2,3,...
        SEC:  index of x axis is time in second
   MILLISEC:  index of x axis is time in millisecond

@param: idx_of_time_field: index of the time field
"""
def plot_selected_fields(file_name
                      ,idx_of_plotting_fields=[2]
                      ,name_of_plotting_fields=['orientation of acceleration']
                      ,plot_type_of_plotting_fields=['DOT']
                      ,idx_of_time_field=1 
                      ,x_axis_type='SEC'
                      ,field_delimeter=' '
    ):
    """
    Parameters for log file
    """
    log_file_path=file_name
        
    """
    read the log, build the arrays
    """
    # ground truth parameters
    ground_truth_idx_range=[[] for i in range(len(activity_type))] 
    ground_truth_set=False

    vline_top_value=[[] for i in range(len(idx_of_plotting_fields))] #maximum value of this field during a time unit (e.g. a second)
    vline_bottom_value=[[] for i in range(len(idx_of_plotting_fields))] #minimum value of this field during a time unit (e.g. a second)
 
    x_axis_span=[sys.maxint, 0, 0] #min, max, span
    x_axis_idx=[]
    
    last_time=-1
    cur_time=0
    cnt=0
    with open(log_file_path) as log_file:
        for line in log_file:
            
            if line[0]=='#':#a comment line 
                continue  
            
            if line[0]=='@': #a ground truth line
                for i in range(len(activity_type)):
                    if activity_type[i] in line: 
                        ground_truth_set=True
                        periods=line[:-1].strip().split(' ')[2:]
                        for period in periods:
                            #print periods
                            start_idx, end_idx=period.split('~')
                            if x_axis_type=='SEC':
                                ground_truth_idx_range[i].append( (HMS_to_secs(start_idx)*1000,HMS_to_secs(end_idx)*1000) )
                            else:
                                if x_axis_type=='MILLISEC':
                                    ground_truth_idx_range[i].append( (HMSS_to_millisecs(start_idx),HMSS_to_millisecs(end_idx)) )
                                else:
                                    ground_truth_idx_range[i].append( (int(start_idx), int(end_idx) ) )
                        break
                
            else:                
                # a sensor reading line
                fields=line[:-1].split(field_delimeter)
                #print fields
                if len(fields)<2: continue
                
                if x_axis_type!='MILLISEC':
                    if ":" in fields[idx_of_time_field]: #time in HMS format
                        cur_time=HMS_to_secs(fields[idx_of_time_field])*1000
                    else: # time in secs
                        cur_time=int(fields[idx_of_time_field])*1000
                else:
                    if ":" in fields[idx_of_time_field]: #time in HMS format
                        cur_time=HMSS_to_millisecs(fields[idx_of_time_field])

                
                if cur_time!=last_time: #a new time
                    if x_axis_type!='NUM':
                        x_axis_idx.append(cur_time)
                    else:
                        cnt+=1
                        x_axis_idx.append(cnt)
                    
                    last_time=cur_time
                    
                    # update x axis time span
                    x_axis_span[0]=min(x_axis_span[0], x_axis_idx[-1])
                    x_axis_span[1]= max(x_axis_span[1],x_axis_idx[-1])
                        
                    for i in range(len(idx_of_plotting_fields)):
                        val=double(fields[idx_of_plotting_fields[i]])
                        vline_top_value[i].append(val)
                        vline_bottom_value[i].append(val)
                else:
                    for i in range(len(idx_of_plotting_fields)):
                        val=double(fields[idx_of_plotting_fields[i]])
                        
#                         for ploting orientation only
#                         if ploting_field_is_orientation:
#                             if(val<10):
#                                 val=360-val 
                        if val>vline_top_value[i][-1]: 
                            vline_top_value[i][-1]=val
                        if val<vline_bottom_value[i][-1]: 
                            vline_bottom_value[i][-1]=val

    
    #print map(millisecs_to_HMS, time_span), ground_truth_idx_range
    print ground_truth_idx_range
    print "# of values on x axis= ", len(x_axis_idx), " len of vline_top_value= ", len(vline_top_value[-1]) 
    #print '\n'.join([str(vline_top_value[-1][i])+","+str(vline_bottom_value[-1][i]) for i in range(10)])

    """
    draw the graph
    """
    for i in range(len(idx_of_plotting_fields)):
        
        field_idx=idx_of_plotting_fields[i]
        
        x_axis_span[2]=x_axis_span[1]-x_axis_span[0] #duration of the whole log     

        y_axis_span=[min(vline_bottom_value[i]), max(vline_top_value[i]), 0]
        y_axis_span[2]=y_axis_span[1]-y_axis_span[0]
        y_axis_extra_padding=y_axis_span[2]/10
        
        fig = plt.figure(figsize=(12, 6))
        vax = fig.add_subplot(111)
          
        """
        Format the graph part
        """
        # X axis
        if x_axis_type!='NUM':
            vax.set_xlabel('time', fontsize=20)
       
        offset=0.02
        vax.set_xlim(left=x_axis_span[0]-x_axis_span[2]*offset, right=x_axis_span[1]+x_axis_span[2]*offset)
        
        if x_axis_type!='NUM':
            vax.xaxis.set_major_locator( LinearLocator(numticks=10) )
              
            vax.xaxis.set_major_formatter(FuncFormatter(millisecs_to_HMS))
            
            for tick in vax.xaxis.get_majorticklabels():
                tick.set_fontsize(10)    

        
        # Y axis
        vax.set_ylabel(name_of_plotting_fields[i],  fontsize=30)
        vax.set_ylim(top=y_axis_span[1]+y_axis_extra_padding,  bottom=y_axis_span[0]-y_axis_extra_padding)
        
        for tick in vax.yaxis.get_majorticklabels():
            tick.set_fontsize(20)
        
        # title
        #vax.set_title('Acceleration Readings of '+ axes[field_idx]+' Axis', fontdict={'fontsize': 20})
        
        
        """
        draw the field data
        """
        if plot_type_of_plotting_fields[i]=='DOT': 
            vax.plot(x_axis_idx, vline_bottom_value[i], '.-k', ms=3)
        else:
            if plot_type_of_plotting_fields[i]=='VLINE':
                vax.vlines(x_axis_idx ,vline_bottom_value[i], vline_top_value[i], colors=['k']) #linestyles=line_styles[i])
        
        
        """
        draw the ground_truth
        """
        bars=[]        
        #draw the bars of the ground truth
        if ground_truth_set:
            for gt_i in range(len(ground_truth_idx_range)):
                for period in ground_truth_idx_range[gt_i]: # not empty
                    bars.append(plt.bar(period[0], height=y_axis_span[2]+2*y_axis_extra_padding, width=period[1]-period[0], bottom=y_axis_span[0]-y_axis_extra_padding, color=ground_truth_bg_color[gt_i]) )
                    if period[1]-period[0] > x_axis_span[2]/10: # only if the period is wide enough to accommodate the label
                        vax.annotate(activity_type[gt_i].replace("_","-"), xy=( period[0]+(period[1]-period[0])/10, y_axis_span[0]-y_axis_extra_padding), fontsize=10)

        # save the figure    
        if '.' in file_name:
            outputfile_name=basename(file_name).split('.')[0]
        else:
            outputfile_name=file_name
        plt.savefig('E:/workspace/android/UPDetector/data/accelerometer/figs/'+outputfile_name+'_'+str(field_idx))
        
        """
        generate legends
        """
        # accl_type=['x', 'y', 'z', 'acceleration']
        #classfied=legend(vlines,  title=accl_type[3], fontsize=20)
        #classfied.draggable(state=True)
        
        #===============================================================================
        # ground_truth=legend(bars, activity_type, title='Activity: Ground Truth')
        # ground_truth.draggable(state=True)
        #===============================================================================
        
        # modify the tickers of x idx_of_ploting_fields  
          
        
    
"""
Plot detected and ground-truth events on one time axis
"""
def plot_detected_events(date):
    fig = plt.figure(figsize=(12, 6))
    vax = fig.add_subplot(111)
    
    
    li=[67077,67536,67806,68542,69085]#09-11
    #li=[67601,68358]  #08-2311
    groundtruth_parking_x =multiply(1000,   sort(list(set(li) )) )    
    #li=[67535,67536,67537,67806,68542,68543,68544,68545,69080,69081,69082,69083] #09-11-feature
    li=[66538,66538,66602,66650,66651,66651,66651,66651,66651,66652,66717,66719,66719,66719,66719,66719,66720,66720,66720,66724,66724,66724,66725,66725,66725,66725,66725,66726,66726,66726,66753,66753,66754,66755,66755,66755,66755,66755,66755,66755,66756,66758,66758,66758,66758,66758,66758,66758,66758,66759,66761,66761,66768,66768,66771,66771,66778,66778,66778,66778,66778,66778,66779,66779,66783,66793,66793,66793,66794,66794,66794,66794,66795,66795,66795,66795,66795,66795,66795,66796,66796,66796,66796,66797,66798,66798,66799,66799,66815,66816,66817,66824,66824,66824,66824,66824,66824,66824,66825,66825,66826,66826,66847,66848,66848,66848,66848,66849,66850,66850,66850,66851,66851,66851,66851,66851,66851,66852,66852,66852,66852,66852,66853,66853,66853,66853,66853,66853,66853,66854,66854,66855,66855,66855,66855,66856,66856,66856,66856,66856,66858,66858,66858,66858,66858,66859,66859,66859,66859,66859,66859,66861,66862,66862,66862,66863,66863,66865,66866,66866,66866,66866,66866,66867,66867,66868,66868,66869,66869,66869,66869,66870,66870,66871,66873,66873,66874,66874,66874,66875,66931,66931,66932,66932,66932,66932,66933,66933,66942,66942,66978,66978,66978,66978,67018,67018,67018,67019,67020,67021,67021,67021,67021,67021,67040,67041,67349,67350,67350,67350,67351,67351,67351,67392,67394,67394,67395,67396,67396,67396,67396,67398,67398,67401,67401,67403,67420,67424,67426,67426,67471,67481,67482,67484,67484,67515,67515,67515,67515,67515,67518,67524,67527,67533,67533,67534,67536,67678,67679,67679,67679,67679,67679,67686,67686,67725,67726,67726,67726,67726,67727,67727,67727,67727,67727,67727,67728,67728,67728,67729,67731,67731,67733,67733,67734,67734,67734,67734,67735,67735,67735,67735,67736,67739,67739,67739,67740,67740,67741,67741,67741,67741,67741,67741,67742,67745,67745,67745,67745,67766,67766,67769,67769,67769,67776,67778,67778,67780,67789,67800,67800,67800,67800,67801,67850,67851,67869,67917,68119,68126,68127,68145,68162,68167,68342,68364,68397,68399,68407,68409,68413,68432,68433,68433,68434,68434,68434,68434,68434,68435,68480,68485,68485,68493,68493,68493,68493,68494,68494,68494,68509,68520,68520,68520,68521,68521,68521,68522,68523,68524,68539,68539,68542,68542,68542,68560,68688,68768,68769,68775,68843,68846,68846,68851,68878,68884,68884,68893,68894,68894,68898,68898,68919,68923,68930,68930,68933,68933,68952,68954,68955,68955,68956,68956,68956,68958,68958,68958,68962,68978,68983,68983,68986,68986,68986,69002,69002,69002,69004,69008,69008,69008,69008,69008,69144,69146,69146,69148,69148,69210,69214] #09-11-accel-weka
    #li=[67598,67599,67600,68357] #08-2311-feature
    detected_parking_x=multiply(1000, sort(list(set(li))))
    
    li=[66650,67348,67678,68432,68687]#09-11
    #li=[67307,67756]  #08-2311
    groundtruth_unparking_x=multiply(1000, sort(list(set(li))))
    #li=[66650,66651,67348,67349,67350,67351,67678,67681,67682,68432,68433,68434,68435,68687] #09-11-feature
    li=[66538,66538,66602,66650,66650,66650,66651,66651,66651,66651,66651,66717,66719,66719,66719,66719,66719,66720,66720,66720,66724,66724,66724,66725,66725,66725,66725,66725,66725,66725,66726,66726,66726,66753,66753,66754,66754,66755,66755,66755,66755,66755,66755,66756,66758,66758,66758,66758,66758,66758,66758,66758,66759,66761,66761,66768,66768,66768,66771,66777,66778,66778,66778,66778,66778,66778,66778,66779,66779,66783,66786,66793,66793,66793,66793,66794,66794,66794,66794,66795,66795,66795,66795,66795,66795,66795,66795,66795,66796,66796,66796,66796,66796,66797,66798,66798,66799,66799,66815,66816,66817,66824,66824,66824,66824,66824,66824,66824,66824,66824,66824,66825,66826,66826,66844,66848,66848,66848,66848,66848,66849,66849,66850,66850,66850,66851,66851,66851,66851,66851,66851,66851,66852,66852,66852,66852,66852,66852,66853,66853,66853,66853,66853,66853,66854,66854,66854,66854,66855,66855,66855,66855,66855,66855,66855,66855,66856,66856,66856,66856,66856,66856,66856,66858,66858,66858,66858,66858,66858,66859,66859,66859,66859,66859,66861,66862,66862,66862,66863,66863,66865,66866,66866,66866,66866,66866,66867,66867,66868,66868,66869,66869,66869,66869,66870,66870,66871,66873,66873,66874,66874,66874,66875,66931,66931,66932,66932,66932,66932,66933,66933,66942,66942,66978,66978,66978,66978,67018,67018,67018,67018,67020,67021,67021,67021,67021,67021,67040,67041,67349,67349,67349,67350,67350,67350,67351,67351,67351,67392,67394,67394,67394,67395,67396,67396,67396,67396,67398,67398,67401,67401,67402,67420,67421,67421,67422,67424,67426,67471,67481,67482,67482,67484,67515,67515,67515,67515,67515,67518,67524,67527,67533,67533,67534,67535,67678,67679,67679,67679,67679,67679,67686,67686,67725,67726,67726,67726,67726,67727,67727,67727,67727,67727,67727,67727,67728,67728,67728,67729,67729,67729,67730,67731,67731,67732,67733,67733,67733,67733,67734,67734,67734,67735,67735,67735,67735,67736,67739,67739,67739,67740,67740,67740,67741,67741,67741,67741,67741,67741,67742,67742,67744,67745,67745,67745,67766,67766,67766,67766,67766,67768,67769,67769,67769,67776,67778,67778,67780,67789,67800,67800,67800,67800,67800,67800,67800,67801,67801,67850,67851,67869,67917,68119,68126,68127,68145,68162,68167,68342,68364,68397,68399,68407,68409,68413,68432,68433,68433,68433,68434,68434,68434,68434,68434,68434,68435,68480,68485,68485,68493,68493,68493,68493,68493,68493,68494,68494,68509,68520,68520,68520,68520,68521,68521,68521,68521,68522,68524,68539,68539,68541,68542,68542,68560,68688,68768,68768,68769,68769,68843,68846,68846,68851,68878,68884,68884,68893,68894,68894,68898,68898,68919,68923,68930,68930,68933,68933,68951,68952,68952,68952,68954,68955,68956,68956,68956,68958,68958,68958,68961,68978,68983,68983,68983,68986,68986,68986,69001,69002,69002,69004,69008,69008,69008,69008,69008,69144,69146,69146,69147,69148,69210,69214] #09-11-accel-weka
    #li=[67309,67310,67311,67312,67755] #08-2311-feature
    detected_unparking_x=multiply(1000, sort(list(set(li))) )
    
    groundtruth_parking_y=[1]*len(groundtruth_parking_x)
    detected_parking_y=[2]*len(detected_parking_x)
    groundtruth_unparking_y=[1]*len(groundtruth_unparking_x)
    detected_unparking_y=[2]*len(detected_unparking_x)
    
    vax.plot(groundtruth_parking_x, groundtruth_parking_y, 'ok', ms=9)
    vax.plot(groundtruth_unparking_x, groundtruth_unparking_y, 'xk', ms=9)
    vax.plot(detected_parking_x, detected_parking_y, 'ok', ms=9)
    vax.plot(detected_unparking_x, detected_unparking_y, 'xk', ms=9)
    
    
    """
    Format part
    """
    vax.set_xlabel('time', fontsize=20)
    
    #xaxis_span[2]=xaxis_span[1]-xaxis_span[0] #duration of the whole log     
    #offset=0.02
    #vax.set_xlim(left=xaxis_span[0]-xaxis_span[2]*offset, right=xaxis_span[1]+xaxis_span[2]*offset)
    
    vax.yaxis.set_major_locator( LinearLocator(numticks=4) )
    vax.yaxis.set_major_formatter(FuncFormatter(mapping))
    for tick in vax.yaxis.get_majorticklabels():
        tick.set_fontsize(15)   

    vax.set_title('Groundtruth and Detected Events', fontdict={'fontsize': 20})
    vax.set_ylim(top=3,  bottom=0)
    
    vax.xaxis.set_major_locator( LinearLocator(numticks=10) )          
    vax.xaxis.set_major_formatter(FuncFormatter(millisecs_to_HMS))        
    for tick in vax.xaxis.get_majorticklabels():
        tick.set_fontsize(15)   
    
    plt.savefig('E:/workspace/android/UPDetector/data/accelerometer/figs/'+date)
    plt.show()


def to_percent(y, position):
    # Ignore the passed in position. This has the effect of scaling the default
    # tick locations.
    s = str(100 * y)

    # The percent symbol needs escaping in latex
    if matplotlib.rcParams['text.usetex'] == True:
        return s + r'$\%$'
    else:
        return s + '%'

def draw_car_backing_histogram(date):
    types=['forward', 'backward']
    data={}
    for type in types:
        value={}
        value['cnt']=0
        value['period']=[]
        data[type]=value
    
    cnt=[0, 0]
    folder='E:/workspace/android/UPDetector/data/accelerometer/figs/data/car_backing/'
    with open(folder+date+'/'+date+'_output.txt') as input:
        for line in input:
            fields=line[:-1].split(',')
            for type in types:
                if type in fields[0]:
                    data[type]['cnt']+=1 
                    data[type]['period'].append([fields[1]+'~'+fields[2],float(fields[3]),float(fields[4])] )
                    break
                
    
    print data
    plt.figure(figsize=(25,9))
    no_of_cols=max(data['forward']['cnt'],data['backward']['cnt'])
    bins=[-1, 1]
    names = ['', 'negative','', 'positive', '']
    for i in range(len(types)):
        type=types[i]
        for j in range(no_of_cols):
            percents = [data[type]['period'][j][1], data[type]['period'][j][2]]
            print percents, type, i, j
            
            seq=i*no_of_cols+j+1
            ax = plt.subplot(2,no_of_cols,seq)
            ax.bar(bins,percents,width=0.3)
            ax.set_xticks(range(-2,3,1))
            ax.set_xticklabels(names,rotation=0)
            if j==0:
                ax.set_ylabel(('first ' if i==0 else 'second ')+'line: \n'+type+' periods', rotation='horizontal', fontsize=20)
            ax.yaxis.set_major_formatter(FuncFormatter(to_percent))
            plt.title('fig '+str(seq)+': '+data[type]['period'][j][0])
   
    plt.suptitle('histograms of car backing movements', fontsize=20)
#     left  = 0.5  # the left side of the subplots of the figure
#     right = 1    # the right side of the subplots of the figure
#     bottom = 0.1   # the bottom of the subplots of the figure
#     top = 3     # the top of the subplots of the figure
#     wspace = 0.5  # the amount of width reserved for blank space between subplots
#     hspace = 0.5 # the amount of height reserved for white space between subplots
#     plt.subplots_adjust(left=left, bottom=bottom, right=right, top=top, wspace=wspace, hspace=hspace)

#    plt.tight_layout(pad=3,w_pad=0,  h_pad=0.5)
    plt.savefig(folder+'/'+date+'_histogram.png')
    #plt.show()
    
    

def mapping(y, pos=0, show_sec=True):
    if y==1:
        return "Ground Truth"
    else:
        if y==2:
            return "Detected"
        else:
            return "" 

def draw_accel_raw_files():
    """
    Drawing accelerometer raw data file
    """
    #first list: unlabeled logs ; second list: labeled logs
    file_names=[[  '08_2355', '08_2462', '08_2486' ], ['08_2311','08_2710', '08_295', '09_211', '09_191', '08_283', '10_241', '10_242', '10_251', '10_245']] 
    file_labeled=1
    
    for n in file_names[file_labeled][-1:]:
        file_name='E:/workspace/android/UPDetector/data/accelerometer/raw/handbag_indoor/'
        if file_labeled==0:
            file_name+='unlabeled/'
        file_name+='ACCELEROMETER_RAW_2013_'+n+'.log'
        
        plot_selected_fields(file_name
                      ,idx_of_plotting_fields=[2,3,4,5]
                      ,name_of_plotting_fields=['acceleration of X Axis', 'acceleration of Y Axis', 'acceleration of Z Axis', 'acceleration']
                      ,plot_type_of_plotting_fields=['VLINE','VLINE','VLINE','VLINE']
        )
        #plt.show()


def  draw_raw_data_single_file():
    #C:/Users/Shuo/Desktop/       E:/workspace/android/UPDetector/data/accelerometer/raw/
    
    date='2013_11_23'
#     raw_file_path='E:/workspace/android/UPDetector/data/accelerometer/figs/data/back_and_forth_movement/';
#     raw_file_path+=date+'/'
#     #raw_file_path+='coatpocket/'
#     raw_file_path+=date+'1_Linear_Acceleration_Sensor.log'
    raw_file_path='E:/workspace/android/UPDetector/data/accelerometer/04202014/ACCELEROMETER_RAW_2014_04_200.log'
    
    """
    Plot the example_figure log
    """
#     plot_selected_fields('E:/workspace/android/UPDetector/data/accelerometer/figs/data/all_activities/example_figure.log'
#                    ,idx_of_plotting_fields=[5]
#                    ,name_of_plotting_fields=['acceleration']
#                    ,plot_type_of_plotting_fields=['VLINE']
#                    ,x_axis_type='NUM'
#      )
    #'E:/workspace/android/UPDetector/data/accelerometer/figs/data/all_activities/example_figure.log'
    plot_selected_fields(
                'E:/workspace/android/UPDetector/data/accelerometer/raw/all_position/ACCELEROMETER_RAW_2014_05_227.log'
                   ,idx_of_plotting_fields=[5]
                   ,name_of_plotting_fields=['acceleration']
                   ,plot_type_of_plotting_fields=['VLINE']
                   #,x_axis_type='NUM'
                   ,x_axis_type='SEC'
     )
    plt.show()

"""
# Drawing features files
"""
def draw_feature_data():
    date='2013_10_251' #is file used in the paper (pos=handbag)
    pos='handbag'   
    
    #'E:\workspace\android\UPDetector\data\accelerometer\change_in_variance\handbag\all'
    plot_selected_fields('E:/workspace/android/UPDetector/data/accelerometer/change_in_variance/'+pos+'/all/ACCELEROMETER_FEATURE_'+date+'.arff'
                  ,idx_of_plotting_fields=[5]
                  ,name_of_plotting_fields=['change in variance']
                  ,idx_of_time_field=0 
                  ,field_delimeter=','
    )

if __name__=="__main__":
    
    #activity_type=['driving', 'walking', 'sit', 'still', 'stand', 'forward', 'backward', 'normal_drive']
    activity_type=['in_vehicle', 'on_foot', 'sit', 'still', 'stand', 'forward', 'backward', 'normal_drive']
    #line_styles=['solid', 'dashed', 'dashdot' , 'dotted']
    colors=['b', 'g', 'r', 'k', 'y'] 
    ground_truth_bg_color=['#AAD4FF', '#66FF66', '#FF7373', '#6E6E6E', '#FFFF66', '#AAD4FF', '#66FF66', '#FF7373']
    
    #draw_accel_raw_files()
    
    draw_raw_data_single_file()
    
    #draw_feature_data()
    
    
    #draw_car_backing_histogram('2013_11_20')

     
 
    """
    # Draw the detected and ground truth events
    """
    #plot_detected_events("08-2311")

