package com.osicorp.adebayo_osipitan.presenter;

public abstract class BasePresenter<V> implements DataPresenterInterface {

    protected final V view;
    protected BasePresenter(V view) {
        this.view = view;
    }
}
