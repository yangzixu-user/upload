package com.pool;


import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author yangzx
 */
public class Test02Runnable implements Runnable {
    private CountDownLatch latch;
    private List<Integer> countList;

    public Test02Runnable(CountDownLatch latch, List<Integer> countList) {
        this.latch = latch;
        this.countList = countList;
    }

    @Override
    public void run() {

    try {

        //doSomeThing...
        countList.add(new Integer(1));
    }finally {
        if (latch==null){
            latch.countDown();
        }
    }

    }
}
