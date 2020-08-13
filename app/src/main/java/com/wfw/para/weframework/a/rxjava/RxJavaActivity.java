package com.wfw.para.weframework.a.rxjava;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.wfw.para.weframework.R;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2016/11/23.
 */

public class RxJavaActivity extends AppCompatActivity {
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;
    @BindView(R.id.btn5)
    Button btn5;
    @BindView(R.id.btn6)
    Button btn6;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_activity_rxjava);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn1)
    void btn1() {//逐个射出
        Flowable.just(1, 2, 3, 4)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e("asd", "accept" + integer);
                    }
                });
    }

    @OnClick(R.id.btn2)
    void btn2() {//逐个射出
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {
                e.onNext("11");
                e.onNext("22");
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER).subscribe(new Consumer<String>() {
            @Override
            public void accept(String integer) throws Exception {
                Log.e("asd", "accept" + integer);
            }
        });
    }


    @OnClick(R.id.btn3)
    void btn3() {//逐个射出
        Flowable.fromArray("11", "22", "33", "44")
                .subscribe(new Consumer<String>() {
            @Override
            public void accept(String integer) throws Exception {
                Log.e("asd", "accept" + integer);
            }
        });
    }


    @OnClick(R.id.btn4)
    void btn4() {//2秒之后射出1个
        Flowable.timer(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.e("asd", "accept" + aLong);
                    }
                });
    }

    @OnClick(R.id.btn5)
    void btn5() {//合并两个数据
        Flowable<String> f1 = Flowable.fromArray("11", "22", "33", "44");
        Flowable<String> f2 = Flowable.fromArray("aa", "bb", "cc", "dd");
        Flowable.merge(f1, f2).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e("asd", s);
            }
        });
    }


    @OnClick(R.id.btn6)
    void btn6() {//合并两个数据
        Flowable<String> f1 = Flowable.fromArray("11", "22", "33", "44");
        Flowable<String> f2 = Flowable.just("aa", "bb", "cc", "dd");
        Flowable<String> f3 = Flowable.fromArray("qq", "ww", "ee", "rr");
        Flowable<String> f4 = Flowable.just("zz", "xx", "cc", "vv");
        List<Flowable<String>> list1 = new ArrayList<>();
        list1.add(f1);
        list1.add(f2);
        list1.add(f3);
        list1.add(f4);
        Flowable.merge(list1).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e("asd", s);
            }
        });
    }


}
