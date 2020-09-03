package com.pool;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author yangzx
 */
public class Test02 {

    public static void main(String[] args) {
        ThreadPoolExecutor taskPool = new ThreadPoolExecutor(
                6,
                9,
                5L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(10)
        );
        int waitTimes = 5;
        CountDownLatch latch = new CountDownLatch(waitTimes);
        List<Integer> rlt = new ArrayList<Integer>();


        new WaitingThread(latch).start();

        //创建任务的次数和等待的次数相等
        for (int i = 0; i < waitTimes; i++) {
            taskPool.execute(new Test02Runnable(latch,rlt));

        }
        Integer sum = 0;
        for (Integer i : rlt) {
            sum += i;
        }
        System.out.println("sum:: " + sum);
        // 任务全部执行完毕，所以获取的为0
        System.out.println("latch:: " + latch.getCount());
        System.out.println("taskPool shutdown flag:: " + taskPool.isShutdown());
        //立即关闭线程池
        taskPool.shutdownNow();
        System.out.println("taskPool shutdown flag:: " + taskPool.isShutdown());

    }

    static class WaitingThread extends Thread{
        CountDownLatch countDownLatch;
        WaitingThread(CountDownLatch countDownLatch){
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println(System.nanoTime()+" 等待 " + countDownLatch.getCount() +" 个子任务执行开始");
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.nanoTime()+"等待结束");
        }
    }




}
