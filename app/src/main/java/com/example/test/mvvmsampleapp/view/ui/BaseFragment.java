package com.example.test.mvvmsampleapp.view.ui;

import android.arch.lifecycle.LifecycleFragment;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseFragment extends LifecycleFragment {
    private CompositeDisposable disposables = new CompositeDisposable();

    protected final void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }

    @Override
    public void onDestroy() {
        System.out.println("Cleaning up disposables ...");
        disposables.dispose();
        super.onDestroy();
    }
}
