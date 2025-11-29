package com.example.hello_world;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKLibdyProxyDemo {
    //4.使用示例
    public static void main(String[] args) {
        //创建真实的明星
        Star realStat = new RealStat("坤坤");

        //创建代理实例
        //loader：类加载器，用于加载代理对象。
        //interfaces：被代理类实现的一些接口；
        //h：实现了InvocationHandler接口的对象；
        Star star = (Star) Proxy.newProxyInstance(
                realStat.getClass().getClassLoader(),
                realStat.getClass().getInterfaces(),
                new StarAgenthandler(realStat)
        );
        star.readLetter("Dear 坤坤,你的音乐太棒了!!!");
        star.readLetter("期待你的下一次演出！!!!");
    }
}

//1.定义明星接口
interface Star {
    void readLetter(String letter);
}

//2.真实明星类
class RealStat implements Star {
    private String name;

    public RealStat(String name) {
        this.name = name;
    }

    @Override
    public void readLetter(String letter) {
        System.out.println(name + "读到信:" + letter);


    }
}


//3.代理处理器 InvocationHandler调用处理程序
class StarAgenthandler implements InvocationHandler {
    private Object target; //被代理的真实对象

    public StarAgenthandler(Object target) {
        this.target = target;
    }

    //负责处理自定义逻辑，当我们的动态代理对象调用一个方法时，这个方法的调用就会被转发到这里
    //proxy：动态生成的代理类
    //method：与代理类对象调用的方法相对应
    //args：当前method方法的参数
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("经纪人收到粉丝来信!");
        System.out.println("经纪人检查信件安全!!");
        //转发给真实明星
        Object result = method.invoke(target, args);


        System.out.println("经纪人记录 信件处理完成\n");

        return result;
    }
}
