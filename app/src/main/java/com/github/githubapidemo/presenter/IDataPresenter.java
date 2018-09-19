package com.github.githubapidemo.presenter;

import com.github.githubapidemo.model.DataBean;

public interface IDataPresenter {

    void getData(String query);

    void setData(DataBean dataBean);

}
