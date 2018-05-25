/**
 * Copyright (C), 杭州中恒云能源互联网技术有限公司，保留所有权利
 */
package com.ape.pattern.factory;

import java.util.Properties;

/**
 * 工厂方法模式<br/>
 * <p>
 * 工厂方法模式的主要优点如下：
 * (1) 在工厂方法模式中，工厂方法用来创建客户所需要的产品，同时还向客户隐藏了哪种具体
 * 产品类将被实例化这一细节，用户只需要关心所需产品对应的工厂，无须关心创建细节，甚
 * 至无须知道具体产品类的类名。
 * (2) 基于工厂角色和产品角色的多态性设计是工厂方法模式的关键。它能够让工厂可以自主确
 * 定创建何种产品对象，而如何创建这个对象的细节则完全封装在具体工厂内部。工厂方法模
 * 式之所以又被称为多态工厂模式，就正是因为所有的具体工厂类都具有同一抽象父类。
 * (3) 使用工厂方法模式的另一个优点是在系统中加入新产品时，无须修改抽象工厂和抽象产品
 * 提供的接口，无须修改客户端，也无须修改其他的具体工厂和具体产品，而只要添加一个具
 * 体工厂和具体产品就可以了，这样，系统的可扩展性也就变得非常好，完全符合“开闭原则”。
 * <p>
 * 工厂方法模式的主要缺点如下：
 * (1) 在添加新产品时，需要编写新的具体产品类，而且还要提供与之对应的具体工厂类，系统
 * 中类的个数将成对增加，在一定程度上增加了系统的复杂度，有更多的类需要编译和运行，
 * 会给系统带来一些额外的开销。
 * (2) 由于考虑到系统的可扩展性，需要引入抽象层，在客户端代码中均使用抽象层进行定义，
 * 增加了系统的抽象性和理解难度，且在实现时可能需要用到DOM、反射等技术，增加了系统
 * 的实现难度。
 * <p>
 * 在以下情况下可以考虑使用工厂方法模式：
 * (1) 客户端不知道它所需要的对象的类。在工厂方法模式中，客户端不需要知道具体产品类的
 * 类名，只需要知道所对应的工厂即可，具体的产品对象由具体工厂类创建，可将具体工厂类
 * 的类名存储在配置文件或数据库中。
 * (2) 抽象工厂类通过其子类来指定创建哪个对象。在工厂方法模式中，对于抽象工厂类只需要
 * 提供一个创建产品的接口，而由其子类来确定具体要创建的对象，利用面向对象的多态性和
 * 里氏代换原则，在程序运行时，子类对象将覆盖父类对象，从而使得系统更容易扩展。
 *
 * @auther qiys@hzzh.com
 * @date 2018-03-30
 */
public class FactoryMethod {

    public static void main(String[] args) {
        String className = "com.ape.pattern.factory.FactoryMethod.FileLoggerFactory"; //read from config file
        LoggerFactory factory = (LoggerFactory) getInstance(className);
        factory.getInstance();
        factory.writeLog();
    }

    public static Object getInstance(String className) {
        Object obj = null;
        try {
            Class clazz = Class.forName(className);
            obj = clazz.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return obj;
    }
}

interface Logger {

    public void init();

    public void init(Properties properties);

    public void writeLog();
}

class DBLogger implements Logger {

    @Override
    public void init() {

    }

    @Override
    public void init(Properties properties) {

    }

    @Override
    public void writeLog() {

    }
}

class FileLogger implements Logger {

    @Override
    public void init() {

    }

    @Override
    public void init(Properties properties) {

    }

    @Override
    public void writeLog() {

    }
}

abstract class LoggerFactory {

    public abstract Logger getInstance();

    public abstract Logger getInstance(Properties properties);

    public void writeLog() {
        Logger logger = getInstance();
        logger.writeLog();
    }
}

class DBLoggerFactory extends LoggerFactory {

    @Override
    public Logger getInstance() {
        return null;
    }

    @Override
    public Logger getInstance(Properties properties) {
        return null;
    }

    @Override
    public void writeLog() {

    }
}

class FileLoggerFactory extends LoggerFactory {

    @Override
    public Logger getInstance() {
        return null;
    }

    @Override
    public Logger getInstance(Properties properties) {
        return null;
    }

    @Override
    public void writeLog() {

    }
}
