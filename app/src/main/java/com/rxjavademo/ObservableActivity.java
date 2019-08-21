package com.rxjavademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.disposables.Disposable;

public class ObservableActivity extends AppCompatActivity {

    private static String TAG = ObservableActivity.class.getSimpleName();
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
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observable);
        findViewById(R.id.btn_first).setOnClickListener(onClickListener);
        findViewById(R.id.btn_second).setOnClickListener(onClickListener);
        findViewById(R.id.btn_third).setOnClickListener(onClickListener);
        findViewById(R.id.btn_fourth).setOnClickListener(onClickListener);
    }

    private void first() {

        final User user = new User();
        Single<User> observable = Single.create(new SingleOnSubscribe<User>() {
            @Override
            public void subscribe(SingleEmitter<User> emitter) throws Exception {
                emitter.onSuccess(user);
            }
        });

        SingleObserver<User> singleObserver = new SingleObserver<User>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, ">>>>> onSubscribe " + d.isDisposed());
            }

            @Override
            public void onSuccess(User user) {
                Log.e(TAG, ">>>>> onSuccess " + user.toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, ">>>>> onError " + e.getMessage());
            }
        };

        observable.subscribe(singleObserver);

    }

    private void second() {
        Completable completable = Completable.timer(10, TimeUnit.SECONDS);

        CompletableObserver observer = new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, ">>>>> onSubscribe ::" + d.isDisposed());
            }

            @Override
            public void onComplete() {
                Log.e(TAG, ">>>>> onComplete");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, ">>>>> onError");
            }
        };

        completable.subscribe(observer);
    }

    private void third() {
        Single<String> single = Single.just("Single 1");

        SingleObserver<String> observer = new SingleObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, ">>>>> onSubscribe ::" + d.isDisposed());
            }

            @Override
            public void onSuccess(String single) {
                Log.e(TAG, ">>>>> onSuccess ::" + single);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, ">>>>> onError");
            }
        };

        single.subscribe(observer);
    }

    private void fourth() {
        Maybe<String> maybe = Maybe.just("Hello");

        Maybe<String> empty = Maybe.empty();

        MaybeObserver<String> observer = new MaybeObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, ">>>>> onSubscribe ::" + d.isDisposed());
            }

            @Override
            public void onSuccess(String s) {
                Log.e(TAG, ">>>>> onSuccess" + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, ">>>>> onError" + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.e(TAG, ">>>>> onComplete ");
            }
        };

//        maybe.subscribe(observer);
        empty.subscribe(observer);
    }

    private void fifth() {

    }

    private void sixth() {

    }

}

//https://android.jlelse.eu/rxjava-single-maybe-and-completable-8686db42bac8
