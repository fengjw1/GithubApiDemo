package com.github.githubapidemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.githubapidemo.adapter.RecyclerAdapter;
import com.github.githubapidemo.model.DataBean;
import com.github.githubapidemo.presenter.DataPresenter;
import com.github.githubapidemo.utils.Constants;
import com.github.githubapidemo.view.IDataView;

/**
 * Home Activity
 */
public class MainActivity extends AppCompatActivity implements IDataView, View.OnClickListener {

    private DataPresenter mPresenter;

    private DataBean mDataBean;
    private EditText mEtSearch;
    private Button mBtnSearch;
    private RecyclerView mRecyclerView;

    private RecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData(){
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, DataBean.ItemsBean itemsBean) {
                Constants.debug("onItemClick");
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);//点击跳转
                intent.putExtra("data", itemsBean);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        mPresenter = new DataPresenter(this);
        mEtSearch = (EditText) findViewById(R.id.search_et);
        mBtnSearch = (Button) findViewById(R.id.search_btn);
        mBtnSearch.setOnClickListener(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mAdapter = new RecyclerAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration
                .VERTICAL));
    }

    /**
     * 这个是IDataView接口：因为在Presenter层已经传入了IDataView接口，并传递了DataBean给接口，所以这里就拿到了数据
     * @param dataBean
     */
    @Override
    public void setQueryData(DataBean dataBean) {
        Constants.debug("setQueryData");
        mDataBean = dataBean;
        mAdapter.setDataBean(dataBean);//这里是将获得的数据传递给Adapter
        mAdapter.notifyDataSetChanged();//并刷新显示
    }

    /**
     * 点击事件：参数是点击的view
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_btn:
                String str = mEtSearch.getText().toString();
                if (str.isEmpty()){//输入的数据是否为空
                    Toast.makeText(this, R.string.search_empty, Toast.LENGTH_SHORT).show();
                }else {
                    mPresenter.getData(str);//这个是传递请求的数据给Presenter层
                }
                break;
            default:
                break;
        }
    }
}
