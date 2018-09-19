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
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
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

    @Override
    public void setQueryData(DataBean dataBean) {
        Constants.debug("setQueryData");
        mDataBean = dataBean;
        mAdapter.setDataBean(dataBean);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_btn:
                String str = mEtSearch.getText().toString();
                if (str.isEmpty()){
                    Toast.makeText(this, R.string.search_empty, Toast.LENGTH_SHORT).show();
                }else {
                    mPresenter.getData(str);
                }
                break;
            default:
                break;
        }
    }
}
