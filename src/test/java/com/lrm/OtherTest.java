package com.lrm;

import com.google.common.collect.Maps;
import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class OtherTest {

    public static void main(String[] args) {
        testListAddAll();

    }

    /**
     * list addAll()的使用
     */
    public static void testListAddAll() {
        ArrayList<Object> list1 = Lists.newArrayList();
        list1.add(1);
        list1.add(2);
        ArrayList<Object> list2 = Lists.newArrayList();
        list2.add(3);
        list2.add(1);
        list2.addAll(list1);
        System.out.println(list2.stream().distinct().collect(Collectors.toList()));
        System.out.println("==================");

    }

    /**
     * map putAll()的使用
     */
    public static void testMapPutAll() {
        //
        // 两个map具有不同的key
        HashMap map1 = new HashMap();
        map1.put("1", "A");
        HashMap map2 = new HashMap();
        map2.put("2", "B");
        map2.put("3", "C");
        map1.putAll(map2);
        System.out.println(map1);
        System.out.println("==================");
        //两个map具有重复的key
        HashMap map3 = new HashMap();
        map3.put("1", "A");
        HashMap map4 = new HashMap();
        map4.put("1", "B");
        map4.put("3", "C");
        map3.putAll(map4);
        System.out.println(map3);
        // 总结：合并map并剔除相同key的数据。
    }
}
