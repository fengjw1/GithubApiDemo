package com.github.githubapidemo.presenter;

import com.github.githubapidemo.model.DataBean;
import com.github.githubapidemo.model.DataModel;
import com.github.githubapidemo.view.IDataView;

/**
 *inter-bedding-layer (MVP design pattern : P)
 */
public class DataPresenter implements IDataPresenter {

    private IDataView mIDataView;
    private DataModel mDataModel;

    /**
     * Presenter构造器：传入一个view接口；并初始化一个Model对象，初始化才会调用请求数据的操作
     * @param dataView
     */
    public DataPresenter(IDataView dataView){
        mIDataView = dataView;
        mDataModel = new DataModel(this);
    }

    /**
     * 这个是IDataPresenter接口的具体实现：这里是调用Model的getData()方法，并传入query字段来请求数据
     * @param query
     */
    @Override
    public void getData(String query) {
        mDataModel.getData(query);
    }

    /**
     * 这个也是接口的具体实现：作用是得到返回的结果，并传递给View。他的参数是我们得到的JSON的数据类DataBean
     * @param dataBean
     */
    @Override
    public void setData(DataBean dataBean) {
        mIDataView.setQueryData(dataBean);
    }
}
