'''
Created on Dec 27, 2013

@author: Shuo
'''
import scipy.stats as stats
if __name__=='__main__':
    print stats.norm(0.7, 0.4).pdf(0.4),\
        stats.norm(0.5, 1).pdf(0.4),\
    stats.norm(0.8, 0.4).pdf(0.7),\
    stats.norm(.05, 0.6).pdf(0.7),\
    stats.norm(.1, 0.4).pdf(0.7)