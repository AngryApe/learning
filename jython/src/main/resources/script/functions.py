#! /usr/bin/env python
# -*- coding: utf-8 -*-
# 双下划线开头的方法表示私有方法，供内部调用

import math


def execute(fun_name=None, parameters=None):
    """
    外部调用统一调用该方法
    :param fun_name: 方法名称
    :param parameters: 参数
    :return: 计算结果
    """
    # 保证传进去的参数不为None,且类型为list
    if fun_name is None or parameters is None or not isinstance(parameters, list):
        return None
    # 构造参数
    print '调用方法[' + fun_name + '],参数', parameters
    exec_str = fun_name + '(' + repr(parameters) + ')'
    # 调用方法
    return eval(exec_str)


def __is_num(parameters=None):
    """
    数字参数有效性验证
    :param parameters: 参数列表,None 不验证
    :return: True:有效 False:无效
    """
    if parameters is None:
        return False
    try:
        for para in parameters:
            if para is not None:
                float(para)
    except Exception, e:
        print 'parameter type error:', e
        return False
    return True


def __all_none(parameters=None):
    """
    None验证
    全部为None则返回True
    :param parameters:
    :return:
    """
    if parameters is None:
        return True
    for param in parameters:
        if param is not None:
            return False
    return True


def __parse_num(param=None):
    """
    将参数转换为数字
    当参数为None的时候转换会出现异常，通过次方法对None进行特殊处理
    :param param: 需要转换的参数
    :return: 转换后的数字
    """
    if param is None:
        return param
    return float(param)


def __none2zero(parameters=None):
    if parameters is None:
        return None
    convert_para = []
    for param in parameters:
        param = 0.0 if param is None else __parse_num(param)
        convert_para.append(param)
    return convert_para


def __clean(parameters=None, size=None):
    """
    数据清洗
    :return 清洗完的数组，长度不变，None转为0.0
    """
    if size is not None and len(parameters) != size:  # 长度验证
        return None
    if __all_none(parameters) or not __is_num(parameters):  # 数字验证 非空验证
        return None
    return __none2zero(parameters)  # 空参数处理


def add(parameters=None):
    """
    加法运算,None值以0.0参与运算
    """
    parameters = __clean(parameters)
    if parameters is None:
        return None
    result = 0.0
    for param in parameters:
        result += param
    return result


def appearpower(parameters=None):
    """
    视在功率计算
    :param parameters: 长度为2的数组，第一个表示有功，第二个表示无功
    :return: 视在功率
    """
    parameters = __clean(parameters, size=2)
    if parameters is None:
        return None
    return math.sqrt(math.pow(parameters[0], 2) + math.pow(parameters[1], 2))


def avg_load(parameters=None):
    """
    日平均负荷
    :param parameters: 日电量
    :return: 负荷
    """
    parameters = __clean(parameters, size=1)
    if parameters is None:
        return None
    return __parse_num(parameters[0]) / 24.0


def loadrate(parameters=None):
    """
    变压器负载率
    :param parameters: 长度为2的数组，第一个表示视在功率，第二个表示变压器容量
    :return: 负载率
    """
    parameters = __clean(parameters, size=2)
    if parameters is None:
        return None
    return parameters[0] / parameters[1] * 100.0


def peak_valley_diff(parameters=None):
    """
    峰谷差
    :param parameters: 长度为2的数组，第一个为峰值，第二个为谷值
    :return:
    """
    parameters = __clean(parameters, size=2)
    if parameters is None:
        return None
    return abs(parameters[0] - parameters[1])


def powerfactor(parameters=None):
    """
    功率因数
    :param parameters: 长度为2的数组，第一个表示有功电量，第二个表示无功电量
    :return: 功率因数
    """
    if len(parameters) != 2 or __all_none(parameters) or not __is_num(parameters):
        return None
    if parameters[0] is None:
        return None
    parameters = __none2zero(parameters)
    appear_power = math.sqrt(math.pow(parameters[0], 2) + math.pow(parameters[1], 2))
    if appear_power is None or appear_power == 0.0:
        return None
    return parameters[0] / appear_power


def unbalance(parameters=None):
    """
    三相不平衡度
    :param parameters: 长度为3的数组,依次为ABC相
    :return:
    """
    parameters = __clean(parameters, size=3)
    if parameters is None:
        return None
    # 过滤None 和 0 值
    calc_arr = []
    for param in parameters:
        if param != 0.0:
            calc_arr.append(param)
    if len(calc_arr) == 0:
        return 0.0
    min_value = min(calc_arr)
    max_value = max(calc_arr)
    return (max_value - min_value) / max_value * 100


# 入口函数，相当于java的main方法
if __name__ == "__main__":
    """
    add
    appearpower
    avg_load
    loadrate
    peak_valley_diff
    powerfactor
    unbalance
    """
    print 'add: ', execute('add', ['1', 2, 3, 4, 5])
    print 'appearpower: ', execute('appearpower', ['2', 2])
    print 'avg_load: ', execute('avg_load', [1200])
    print 'loadrate: ', execute('loadrate', [1200, 3000])
    print 'peak_valley_diff: ', execute('peak_valley_diff', [100, 300])
    print 'powerfactor: ', execute('powerfactor', [100, 200])
    print 'unbalance: ', execute('unbalance', [100, 200, 300])
    print 'unbalance: ', execute('unbalance', [0, 0, 300])
