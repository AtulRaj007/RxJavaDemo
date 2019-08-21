package com.rxjavademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class OperatorsActivity extends AppCompatActivity {

    private static final String TAG = OperatorsActivity.class.getSimpleName();
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_first:
                    first();
                    break;
                case R.id.btn_second:
                    second();
                    break;
                case R.id.btn_third:
                    third();
                    break;
                case R.id.btn_fourth:
                    fourth();
                    break;
                case R.id.btn_fifth:
                    fifth();
                    break;
                case R.id.btn_sixth:
                    sixth();
                    break;
                case R.id.btn_seventh:
                    seventh();
                    break;
                case R.id.btn_eighth:
                    eighth();
                    break;
                case R.id.btn_ninth:
                    ninth();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_first).setOnClickListener(onClickListener);
        findViewById(R.id.btn_second).setOnClickListener(onClickListener);
        findViewById(R.id.btn_third).setOnClickListener(onClickListener);
        findViewById(R.id.btn_fourth).setOnClickListener(onClickListener);
        findViewById(R.id.btn_fifth).setOnClickListener(onClickListener);
        findViewById(R.id.btn_sixth).setOnClickListener(onClickListener);
        findViewById(R.id.btn_seventh).setOnClickListener(onClickListener);
        findViewById(R.id.btn_eighth).setOnClickListener(onClickListener);
        findViewById(R.id.btn_ninth).setOnClickListener(onClickListener);
    }

    /**
     *
     */
    private void first() {
        final String[] names = {"First", "Second", "Third", "Fourth", "Fifth"};
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Log.e(TAG, ">>>>> subscribed");
                for (String name : names) {
                    emitter.onNext(name);
                }
                emitter.onComplete();
            }
        });

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, ">>>>> onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, ">>>>> onNext ::" + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, ">>>>> onError ::");
            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }

    private void second() {
        Observable<String> observable = Observable.fromArray("A", "B", "C", "D", "E");

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, ">>>>> onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, ">>>>> onNext ::" + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, ">>>>> onError");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, ">>>>> onComplete");
            }
        };

        observable
                .observeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }

    private void third() {
        Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS);

        Observer<Long> observer = new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, ">>>>> onSubscribe");
            }

            @Override
            public void onNext(Long aLong) {
                Log.e(TAG, ">>>>> onNext ::" + aLong);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, ">>>>> onError ");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, ">>>>> onComplete");
            }
        };

        observable.subscribe(observer);

    }

    private void fourth() {
        Observable<String[]> observable = Observable.just(new String[]{"A", "B", "C"});

        Observer<String[]> observer = new Observer<String[]>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, ">>>>> onSubscribe");
            }

            @Override
            public void onNext(String[] strings) {
                Log.e(TAG, ">>>>> onNext ::" + strings);
                for (String x : strings) {
                    Log.e(TAG, ">>>>> onNext ::" + x);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, ">>>>> onError ");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, ">>>>> onComplete");
            }
        };

        observable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(observer);
    }

    private void fifth() {
        Observable<Long> observable = Observable.timer(30, TimeUnit.SECONDS);

        Observer<Long> observer = new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, ">>>>> onSubscribe");
            }

            @Override
            public void onNext(Long aLong) {
                Log.e(TAG, ">>>>> onNext ::" + aLong);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, ">>>>> onError");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, ">>>>> onComplete");
            }
        };

        observable.subscribe(observer);
    }

    private void sixth() {
        Observable<List<String>> observable = Observable.just("A", "B", "C", "D", "E")
                .buffer(2);

        Observer<List<String>> observer = new Observer<List<String>>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, ">>>>> onSubscribe");
            }

            @Override
            public void onNext(List<String> strings) {
                for (String str : strings)
                    Log.e(TAG, ">>>>> onNext ::" + str);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, ">>>>> onError");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, ">>>>> onComplete");
            }
        };

        observable.subscribe(observer);


    }

    private void seventh() {
        Observable<String> observable = Observable.just("A", "B", "C", "D");

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, ">>>>> onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, ">>>>> onNext ::" + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, ">>>>> onError ");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, ">>>>> onComplete ");
            }
        };

        observable
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return s + ">>>>>" + s;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    private void eighth() {
        Observable<String> observable = Observable.just("1", "2", "3", "4", "5");

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, ">>>>> onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, ">>>>> onNext ::" + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, ">>>>> onError ");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, ">>>>> onComplete ");
            }
        };

        observable.delay(30, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    private void ninth() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5, 6);

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, ">>>>> onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                Log.e(TAG, ">>>>> onNext ::" + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, ">>>>> onError");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, ">>>>> onComplete");
            }
        };

        observable
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer % 2 == 0;
                    }
                })

                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }
}
