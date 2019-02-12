package com.saif.jakerepo;

public interface BaseView<T extends BasePresenter> extends ParentView {
    void setPresenter(T presenter);

}