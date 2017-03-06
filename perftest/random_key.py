#coding=utf-8
'''
Created on 2017-3-6

@author: kemin.yu
'''
import random
import string

fname = "keys.txt"

def generate_keys():
    chars = string.lowercase + string.uppercase
    keys = []
    for i in range(1, 10):
        for j in range(20): # key number
            word = ''
            for k in range(i): # char number
                word = word + random.choice(chars)
            keys.append(word)
    random.shuffle(keys)
    print keys
    
    try:
        fobj = open(fname, 'w')
        for k in keys:
            fobj.write(k+'\n') 
        fobj.close()
    except IOError, e:
        print 'file open error:', e

if __name__ == '__main__':
    generate_keys()
    