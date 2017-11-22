#!usr/bin/python
# coding=UTF-8
import math


class Function:
    def __init__(self):
        pass

    def adder(self, a, b):
        return a + b

    def testFunction(self, a, b):
        return self.testInstance(a, b)

    def testInstance(self, a, b):
        return math.sqrt(math.pow(a, 2) + math.pow(b, 2)) + 22

    def setVar(self, a):
        varaa = a

    def call(self, args):
        return eval('self.' + args[0] + '(2,2)')
