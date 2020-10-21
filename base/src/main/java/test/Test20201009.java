package test;

import bean.UserInfo;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @Author: rj
 * @Date: 2020-10-09 22:46
 * @Version: 1.0
 * Java 到底是值传递还是引用传递？
 * 答：值传递。基本数据类型传的是数据本身，对象类型传的是引用地址（引用地址也是值）
 *
 */
public class Test20201009 {
    public static void print(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.println();
    }
    public static void change(int[] arr){
//        arr = new int[]{6, 7, 8, 9, 10};
        arr[0] = 6;
    }
    public static void changeObj(UserInfo userInfo){
        userInfo.setId("222");
        userInfo.setUsername("第二个");
        userInfo.setPassword("@@@");
    }
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        print(arr);
        change(arr);
        print(arr);
        UserInfo userInfo = new UserInfo("111","第一个","!!!");
        System.out.println(userInfo);
        changeObj(userInfo);
        System.out.println(userInfo);


        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("1");
        list.add("2");
        list.add("2");
        list.add("3");
        ArrayList<String> list1 = list;
        System.out.println(System.identityHashCode(list));
        System.out.println(System.identityHashCode(list1));
        // 通过 filter 去除 所有的 "1"
        list = (ArrayList<String>) list.stream()
                .filter(x -> !"1".equals(x))
                .collect(Collectors.toList());
        System.out.println(System.identityHashCode(list));
        System.out.println(System.identityHashCode(list1));
        System.out.println(list);
        System.out.println(list1);
    }
}
