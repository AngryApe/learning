#!usr/bin/python
#coding=UTF-8
import math

def execute(function_name):
    # global funcObj
    # if funcObj is None:
    #     funcObj = script.python.test.Function()
    # print funcObj
    # # print script.python.test.Function()
    # if "reload"==function_name:
    #     test = reload(script.python.test)
    #     funcObj = test.Function()
    # else:
    return eval('testFunction(2,2)')

def testFunction( a, b):
    return testInstance(a, b)

def testInstance( a, b):
    return math.sqrt(math.pow(a, 2) + math.pow(b, 2)) + 11
