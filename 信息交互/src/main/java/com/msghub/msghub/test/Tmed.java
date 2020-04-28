package com.msghub.msghub.test;

import com.msghub.msghub.test.colne.Abc;
import com.msghub.msghub.test.colne.Qwe;
import com.msghub.msghub.utils.MyUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.thoughtworks.xstream.core.util.QuickWriter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import sun.reflect.generics.tree.VoidDescriptor;

import java.util.ArrayList;
import java.util.List;


public class Tmed   {

    public static void main(String[] args) throws Exception{
        Abc a= new Abc();
        Qwe qwe = new Qwe();
        qwe.setName("克隆前老王");
        a.setId(1);
        a.setQq(qwe);
        Abc clone = MyUtil.clone(a);
        clone.getQq().setName("克隆后老");
        System.out.println(a);
        System.out.println(clone);
        Void nil = null;

    }

}
