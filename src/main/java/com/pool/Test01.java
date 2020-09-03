package com.pool;


import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yangzx
 */
public class Test01 {

    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                3,
                6,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(3));



    }


}
