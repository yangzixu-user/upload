package com.pool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yangzx
 */
public class Test04 {


    public static void main(String[] args) throws InterruptedException {

        //可以理解为 需要处理线程的数量
        final CountDownLatch count = new CountDownLatch(10);
        //可以理解为 处理线程的数量
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        //提交任务
        for (int index=1;index<=10;index++){
            final int number = index;
            executorService.submit(()->{
                try {
                    Thread.sleep((long) (Math.random()*10000));
                    System.out.println(number+": arrived");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally{
                    //运动员到达终点,count数减一
                    count.countDown();
                }
            });
        }
        System.out.println("Game Started");
        //等待count数变为0,否则会一直处于等待状态,游戏就没法结束了
        count.await();
        System.out.println("Game Over");
        //关掉线程池
        executorService.shutdown();
    }
}
