package com.github.githubapidemo.presenter;

import com.github.githubapidemo.model.DataBean;
import com.github.githubapidemo.model.DataModel;
import com.github.githubapidemo.view.IDataView;

public class DataPresenter implements IDataPresenter {

    private IDataView mIDataView;
    private DataModel mDataModel;

    public DataPresenter(IDataView dataView){
        mIDataView = dataView;
        mDataModel = new DataModel(this);
    }

    @Override
    public void getData(String query) {
        mDataModel.getData(query);
    }

    @Override
    public void setData(DataBean dataBean) {
        mIDataView.setQueryData(dataBean);
    }
}
