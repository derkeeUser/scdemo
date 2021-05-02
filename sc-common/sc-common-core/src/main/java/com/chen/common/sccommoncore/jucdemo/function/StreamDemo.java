package com.chen.common.sccommoncore.jucdemo.function;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @program: scdemo
 * @description: 流式计算 lambda表达式,链式编程,函数式接口,Stream流式计算
 * 1.ID必须是偶数
 * 2.年龄必须大于18岁
 * 3.用户名转为大写字母
 * 4.用户名倒序
 * 5.只输出一个用户
 * @author: chenxiaoming
 * @create: 2021-04-30 15:26:18
 */
public class StreamDemo {
    public static void main(String[] args) {
        User user1 = new User(1,"A",18);
        User user2 = new User(2,"B",19);
        User user3 = new User(3,"C",20);
        User user4 = new User(4,"D",21);
        User user5 = new User(5,"E",22);

        Arrays.asList(user1, user2, user3, user4, user5).stream()
                .filter(e -> e.getId() % 2 == 0)
                .filter(e -> e.getAge() > 18)
                .map(e -> e.getName().toUpperCase())
                .sorted(Comparator.reverseOrder())
                .limit(1)
                .forEach(System.out::println);
    }
}

class User{

    public User(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    private Integer id;

    private String name;

    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}