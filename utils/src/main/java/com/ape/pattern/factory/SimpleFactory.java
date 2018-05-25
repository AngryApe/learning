/**
 * Copyright (C), 杭州中恒云能源互联网技术有限公司，保留所有权利
 */
package com.ape.pattern.factory;

/**
 * 简单工厂模式
 * <p>
 * 简单工厂模式的主要优点如下：
 * (1) 工厂类包含必要的判断逻辑，可以决定在什么时候创建哪一个产品类的实例，客户端可以
 * 免除直接创建产品对象的职责，而仅仅“消费”产品，简单工厂模式实现了对象创建和使用的分
 * 离。
 * (2) 客户端无须知道所创建的具体产品类的类名，只需要知道具体产品类所对应的参数即可，
 * 对于一些复杂的类名，通过简单工厂模式可以在一定程度减少使用者的记忆量。
 * (3) 通过引入配置文件，可以在不修改任何客户端代码的情况下更换和增加新的具体产品类，
 * 在一定程度上提高了系统的灵活性。
 * <p>
 * 简单工厂模式的主要缺点如下：
 * (1) 由于工厂类集中了所有产品的创建逻辑，职责过重，一旦不能正常工作，整个系统都要受
 * 到影响。
 * (2) 使用简单工厂模式势必会增加系统中类的个数（引入了新的工厂类） ，增加了系统的复杂
 * 度和理解难度。
 * (3) 系统扩展困难，一旦添加新产品就不得不修改工厂逻辑，在产品类型较多时，有可能造成
 * 工厂逻辑过于复杂，不利于系统的扩展和维护。
 * (4) 简单工厂模式由于使用了静态工厂方法，造成工厂角色无法形成基于继承的等级结构。
 * <p>
 * 在以下情况下可以考虑使用简单工厂模式：
 * (1) 工厂类负责创建的对象比较少，由于创建的对象较少，不会造成工厂方法中的业务逻辑太
 * 过复杂。
 * (2) 客户端只知道传入工厂类的参数，对于如何创建对象并不关心
 *
 * @auther qiys@hzzh.com
 * @date 2018-03-30
 */
public class SimpleFactory {

    public static void main(String[] args) {
        Father obj1 = Father.getInstance(1);
        Father obj2 = Father.getInstance(2);
        obj1.commonMethod();
        obj1.customizedMethod();
        obj2.commonMethod();
        obj2.customizedMethod();
    }
}

abstract class Father {

    private String type;

    protected String getClassName() {
        return this.getClass().getSimpleName();
    }

    public void commonMethod() {
        System.out.println(getClassName() + " invoke commonMethod.");
    }

    // do something different
    public abstract void customizedMethod();

    // the factory
    public static Father getInstance(int tag) {
        Father instance = null;
        switch (tag) {
            case 1:
                instance = new Child1();
                break;
            case 2:
                instance = new Child2();
                break;
        }
        return instance;
    }

}

class Child1 extends Father {

    @Override
    public void customizedMethod() {
        System.out.println(getClassName() + " in child 1");
    }
}

class Child2 extends Father {

    @Override
    public void customizedMethod() {
        System.out.println(getClassName() + " in child 2");
    }
}