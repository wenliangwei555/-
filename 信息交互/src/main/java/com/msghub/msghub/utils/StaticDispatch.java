package com.msghub.msghub.utils;


class Human {
}

class Man extends Human {
}

class Woman extends Human {
}
public class StaticDispatch {
    public void sayHello(Human guy) {
        System.out.println("hello, guy!");
    }

    public void sayHello(Man guy){
        System.out.println("hello, gentleman!");
    }

    public void sayHello(Woman guy){
        System.out.println("hello, lady!");
    }

    public static void main(String[] args){
      /*  Human man = new Man();
        Human woman = new Woman();
        StaticDispatch sr = new StaticDispatch();
        sr.sayHello(man);
        sr.sayHello(woman);
        int a=1300;
        Integer integer = new Integer(1300);
        Integer integer2 = new Integer(1300);
        System.out.println(a==integer);
        System.out.println(integer2==a);
        System.out.println(integer==integer2);*/

    }
}
