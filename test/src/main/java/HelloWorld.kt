//⽬录与包的结构⽆需匹配： 源代码可以在⽂件系统的任意位置
package com.ape.kt.learn

import java.awt.Rectangle

/**
 * @auther qiys@hzzh.com
 * @date 2018-01-12
 */

fun main(args: Array<String>) {
    println("Hello Kotlin")
    strings()
    nullCheck(null, 1, 2.45F)
    whenTest(null)
    rangeTest()
    testIntExtend()
}

/**
 * 普通方法
 * 1. 返回类型定义在参数后，形式为：":TYPE"
 * 2. 返回可以为空时，在返回类型后加"?"
 * 3. 参数可以为空时，在参数类型后加"?"
 * 4. 参数可以有默认值，在参数类型后加" = xxx" 表示默认值
 */
fun sum1(a: Int = 0, b: Int): Int {
    return a + b
}

//自动识别返回类型
fun sum2(a: Int, b: Int) = a + b

public fun sum3(a: Int, b: Int) = a * b

//无返回值
fun void1(a: Int, b: Int): Unit {
    print(a + b)
}

//无返回值Unit可省略
fun void2(a: Int, b: Int) {
    print(a + b)
}

//变参函数
fun varfun(vararg arr: Int) {
    for (a in arr) {
        println(a)
    }
}

//lambda 表达式
val sumlambda: (Int, Int) -> Int = { x, y -> x + y }

/**
 * 变量
 */
val PI = 3.1415926 //全局变量
var count = 0
fun variables() {
    /**
     * 只读变量定义，相当于final
     */
    val a: Int = 5
    val b = 5 //自动判断类型
    val c: Int //不初始化必须指定类型
    c = 3

    /**
     * 可变变量
     */
    var aa = 5  //自动判断类型
    aa = 6
    var bb: Int = 5
    bb = 6
    var cc: Int //不初始化必须指定类型
    cc = 5
    cc = 6
}

/**
 * 字符串相关
 */
fun strings() {
    var a = 1
    var str = "a is $a" //模板中的简单名称
    println(str)
    a = 2
    var str2 = "${str.replace("is", "was")}, but now is $a" //模板中的任意表达式
    println(str2)
}

/**
 * 条件表达式
 */
fun maxOf(a: Int, b: Int): Int {
    //1. if
    /*if (a>b){
        return a
    }else {
        return b
    }*/
    //2. when
    return when {
        a > b -> a
        else -> b
    }
}

/**
 * 三目运算
 */
fun maxOf2(a: Int, b: Int) = if (a > b) a else b

/**
 * 空值检测
 * 在定义时，?可以跟到类型后表示可以为空
 * 在使用时，?可以跟到变量后，若运行时为空，则直接返回null
 */
fun nullCheck(a: String?, b: Int, obj: Any?) {
    val length = a?.length

    println("$a length=$length, obj.toString().length=${obj?.toString()?.length}")
}

/**
 * is 运算符检测⼀个表达式是否某类型的⼀个实例
 */
fun typeCheck(obj: Any?): Int? {
    return when (obj) {
        is String -> obj.length
        else -> null
    }
}

/**
 * 循环
 */
fun loopRun() {
    val list = listOf("11", "22", "33", "44", "55", "66")
    /**
     * for 元素
     */
    for (item in list) {
        println(item)
    }
    /**
     * for 下标
     */
    for (index in list.indices) {
        println("FOR:item at $index is ${list[index]}")
    }

    /**
     * while 循环
     */
    var index = 0
    while (index < list.size) {
        println("WHILE:item at $index is ${list[index]}")
    }
}

/**
 * when 表达式(java: switch case)
 */
fun whenTest(obj: Any?) {
    println("when test :" + when (obj) {
        null -> "null"
        "hello" -> "Greeting"
        is Long -> "Long"
        !is String -> "not String"
        else -> "UnKnown"
    })
}

/**
 * 序列区间
 */
fun rangeTest() {
    val a = 5
    val b = 9
    /**
     * 1. "x..y" :x到y 的闭区间
     * 2. "x until y" :x到y的半开区间（x..y-1）
     * 3. "x..y step z":x到y步长为z的区间（x,x+z,x+2z...）
     * 4. "x downTo y":(x>y),从大(x)到小(y)的区间
     * 5. "x downTo y step z"
     */
    if (a in 1..b) {
        println("$a is not greater than $b")
    }

    val list = listOf("11", "22", "33", "44", "55", "66")
    println("list size:${list.size}, last index of list is ${list.lastIndex}")//6, 5

    /**
     * 在循环中表示一个区间
     */
    var sum = 0
    for (i in 1..10) {
        sum += i
    } //sum = (1..10).sum()
    println("1 to 10 sum is $sum") //55

    /**
     * 固定步长区间
     */
    sum = 0
    for (i in 1..10 step 3) {
        sum += i
    } //sum = (1..10 step 3).sum()
    println("1 step 3 to 10 sum is $sum") //22
}

/**
 * 集合的使用
 */
fun listTest() {
    val list = listOf("11", "22", "33", "44", "55", "66")
    //遍历
    for (item in list) {
        print("$item ")
    }
    //判断是否包含
    if ("11" in list) {
        print("11 is in list")
    }
    //lambda 链式操作
    list.filter { item -> item.length > 2 }
            .sortedByDescending { it }//如果lambda表达式只有一个参数，其名称默认为it
            .map { it.toUpperCase() }
            .forEach { print(it) }
}

/**
 * 对象的创建
 * 与java 不同，kotlin 创建对象不需要 new 关键字
 */
val rectangle = Rectangle(5, 2)

/**
 * 扩展函数（动态语言的特点之一）
 */
fun Int.Increment(step: Int = 1) {
    this.and(step)
}//为Int类型增加一个Increment函数，之后就可以使用该函数了

fun testIntExtend() {
    var sum = 0
    for (i in 1..10) {
        sum.Increment(2)
    }
    println(sum)
}

/**
 * 空检查
 */
fun nullCheck(obj: Any?) {
    var a = obj?.toString()// ? = if not null
    var b = obj ?: "" //?: = if null = if not null and else
    var map = mapOf(Pair(1,"aaa"), Pair(2,"bbb"),"ccc" to 3)
    val c = map[1]?:throw Exception("key 1 is not exist.") // if null execute a statement

}






