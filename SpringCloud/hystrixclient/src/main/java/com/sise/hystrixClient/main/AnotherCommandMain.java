package com.sise.hystrixClient.main;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import java.util.Iterator;

/**
 * @program: SpringCloud
 * @description: Lab7 实验3
 * @author: wxy
 * @create: 2020-01-22 14:07
 **/

public class AnotherCommandMain extends HystrixObservableCommand<String> {

    private final String name;

    public AnotherCommandMain(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected Observable<String> construct() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (!subscriber.isUnsubscribed()){
                    subscriber.onNext("Hello！");
                    subscriber.onNext(name + "！Thread：");
                    subscriber.onNext(Thread.currentThread().getName());
                    subscriber.onCompleted();
                }
            }
        }).subscribeOn(Schedulers.io());
    }

    public static void main(String[] args){
        Observable<String> observable = new AnotherCommandMain("Asynchronous-hystrix").observe();
        Iterator<String> iterator = observable.toBlocking().getIterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}
