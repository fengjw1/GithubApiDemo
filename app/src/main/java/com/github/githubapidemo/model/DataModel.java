package com.github.githubapidemo.model;

import com.github.githubapidemo.presenter.IDataPresenter;
import com.github.githubapidemo.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * data processing(MVP design pattern : M)
 */
public class DataModel implements IDataModel {

    private RetrofitInterface mInterface;
    private IDataPresenter mIDataPresenter;

    /**
     * 数据构造器：初始化的时候加载Retorfit对象和初始化Presenter对象
     * @param dataPresenter
     */
    public DataModel(IDataPresenter dataPresenter){
        Constants.debug("DataModel");
        mIDataPresenter = dataPresenter;
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build();
        mInterface = retrofit.create(RetrofitInterface.class);
    }

    /**
     * 这个是请求方法：传入query字段来查询
     * @param query
     */
    @Override
    public void getData(String query) {
        Call<DataBean> dataBean = mInterface.getData(query);
        dataBean.enqueue(new Callback<DataBean>() {
            @Override
            public void onResponse(Call<DataBean> call, Response<DataBean> response) {
                if (response.isSuccessful()){
//                    Constants.debug(response.body().toString());
                    mIDataPresenter.setData(response.body());//这里是返回的数据，传递给Presenter层
                }
            }

            @Override
            public void onFailure(Call<DataBean> call, Throwable t) {

            }
        });
    }
}
