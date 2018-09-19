package com.github.githubapidemo.model;

import com.github.githubapidemo.presenter.IDataPresenter;
import com.github.githubapidemo.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataModel implements IDataModel {

    private RetrofitInterface mInterface;
    private IDataPresenter mIDataPresenter;

    public DataModel(IDataPresenter dataPresenter){
        Constants.debug("DataModel");
        mIDataPresenter = dataPresenter;
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build();
        mInterface = retrofit.create(RetrofitInterface.class);
    }

    @Override
    public void getData(String query) {
        Call<DataBean> dataBean = mInterface.getData(query);
        dataBean.enqueue(new Callback<DataBean>() {
            @Override
            public void onResponse(Call<DataBean> call, Response<DataBean> response) {
                if (response.isSuccessful()){
//                    Constants.debug(response.body().toString());
                    mIDataPresenter.setData(response.body());
                }
            }

            @Override
            public void onFailure(Call<DataBean> call, Throwable t) {

            }
        });
    }
}
