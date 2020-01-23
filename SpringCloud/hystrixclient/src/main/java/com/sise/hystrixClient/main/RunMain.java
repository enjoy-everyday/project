package com.sise.hystrixClient.main;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import rx.Observable;
import rx.Observer;

/**
 * @program: SpringCloud
 * @description: Lab7 各种命令执行
 * @author: wxy
 * @create: 2020-01-21 23:02
 **/

public class RunMain {

    public static void main(String[] args) throws InterruptedException {
        RunCommand runCommand =new RunCommand("");
        RunCommand runCommand1 = new RunCommand("execute");
        runCommand1.execute();
        RunCommand runCommand2 = new RunCommand("queue");
        runCommand2.queue();
        RunCommand runCommand3 = new RunCommand("observer");
        runCommand3.observe();
        RunCommand runCommand4 = new RunCommand("toObservable");
        Observable<String > observable = runCommand4.toObservable();
        observable.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("完成");
            }

            @Override
            public void onError(Throwable e) {}

            @Override
            public void onNext(String s) {
                System.out.println("结果：" + s);
            }
        });
        Thread.sleep(100);
    }

    static class RunCommand extends HystrixCommand<String>{

        String msg;
        public RunCommand(String msg){
            super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
            this.msg = msg;
        }

        protected String run() throws  Exception{
            System.out.println(msg);
            return "success";
        }

    }

}
