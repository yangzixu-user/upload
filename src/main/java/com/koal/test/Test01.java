package com.koal.test;

import com.koal.entity.Entity;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yangzx
 */
public class Test01 {

    public static void main(String[] args) {

        List<Entity> list =  new ArrayList<>();
        Entity entity;
        for (int i = 1; i < 6; i++) {
            entity = new Entity(i, "wang0"+i, "河南"+i);
            list.add(entity);
        }

        Stream<Integer> stream = Stream.of(6, 4, 6, 7, 3, 9, 8, 10, 12, 14, 14);
            stream.filter(s-> s > 5 )
                    .distinct()
                    .skip(2)
                    .limit(2)
                    .forEach(System.out::println);


        List<String> list1 = Arrays.asList("a,b,c", "1,2,3");

        list1.stream().map(s-> s.replace(",","")).forEach(System.out::println);

        list1.stream().flatMap(s -> {
            String[] split = s.split(",");
            return  Arrays.stream(split);
        }).forEach(System.out::println);


        List<String> list2 = Arrays.asList("aa", "ff", "dd");
        list2.stream().sorted().forEach(System.out::println);

        Student s1 = new Student("aa", 10);
        Student s2 = new Student("bb", 20);
        Student s3 = new Student("aa", 30);
        Student s4 = new Student("dd", 40);


        List<Student> list3 = Arrays.asList(s1, s2, s3, s4);

        Set<Student> aa = list3.stream().filter(s -> s.getName().equals("aa")).collect(Collectors.toSet());
        aa.stream().forEach(System.err::println);

        boolean b = list3.stream().filter(s -> s.getAge() == 20)
                .collect(Collectors.toList())
                .removeIf(s -> s.getAge() == 20);
        System.err.println(b);

        list3.stream().sorted(
                (o1,o2)->{
                    if (o1.getName().equals(o2.getName())){
                        return o1.getAge()-o2.getAge();
                    }else {
                        return o1.getName().compareTo(o2.getName());
                    }
                }
        ).forEach(System.out::println);


        List<Student> list4 = Arrays.asList(s1, s2, s3, s4);
        list4.stream().peek(o->{o.setAge(100);}).forEach(System.err::println);


        List<Integer> list5 = Arrays.asList(1, 2, 3, 4, 5);
        boolean allMatch = list5.stream().allMatch(e -> e > 10); //false
        boolean noneMatch = list5.stream().noneMatch(e -> e > 10); //false
        boolean anyMatch = list5.stream().anyMatch(e -> e > 4); //true

        Integer findFirst = list5.stream().findFirst().get(); //1
        Integer findAny = list5.stream().findAny().get(); //1

        long count = list5.stream().count();//5
        Integer max = list5.stream().max(Integer::compareTo).get(); //5
        Integer min = list5.stream().min(Integer::compareTo).get(); //1


        //经过测试，当元素个数小于24时，并行时线程数等于元素个数，当大于等于24时，并行时线程数为16
        List<Integer> list6 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);


    }



    static class Student{
        private String name;
        private int age;



        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
