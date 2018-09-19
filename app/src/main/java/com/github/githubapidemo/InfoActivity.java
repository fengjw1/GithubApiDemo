package com.github.githubapidemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.TextView;

import com.github.githubapidemo.model.DataBean;

/**
 * Detail Activity
 */
public class InfoActivity extends AppCompatActivity {

    private TextView mTvName;
    private TextView mTvLogin;
    private TextView mTvScore;
    private TextView mTvDescription;
    private TextView mTvCreated;
    private TextView mTvUpdated;
    private TextView mTvStars;
    private TextView mTvForks;
    private TextView mTvIssues;
    private TextView mTvLanguage;
    private CheckBox mTvPrivateX;
    private CheckBox mTvFork;

    //data
    private DataBean.ItemsBean mItemsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent = getIntent();
        mItemsBean = (DataBean.ItemsBean)intent.getSerializableExtra("data");

        initView();
        initData();
    }

    private void initData(){
        mTvName.setText(mItemsBean.getName());
        mTvLogin.setText(mItemsBean.getOwner().getLogin());
        mTvScore.setText(mItemsBean.getScore() + "");
        mTvDescription.setText(mItemsBean.getDescription());
        mTvCreated.setText(mItemsBean.getCreated_at());
        mTvUpdated.setText(mItemsBean.getUpdated_at());
        mTvStars.setText(mItemsBean.getStargazers_count() + "");
        mTvForks.setText(mItemsBean.getForks() + "");
        mTvIssues.setText(mItemsBean.getOpen_issues_count() + "");
        mTvLanguage.setText(mItemsBean.getLanguage());
        if (mItemsBean.isPrivateX()){
            mTvPrivateX.setChecked(true);
        }
        if (mItemsBean.isFork()){
            mTvFork.setChecked(true);
        }
    }

    private void initView() {
        mTvName = (TextView) findViewById(R.id.name_tv);
        mTvLogin = (TextView) findViewById(R.id.login_tv);
        mTvScore = (TextView) findViewById(R.id.score_tv);
        mTvDescription = (TextView) findViewById(R.id.description_tv);
        mTvCreated = (TextView) findViewById(R.id.created_tv);
        mTvUpdated = (TextView) findViewById(R.id.updated_tv);
        mTvStars = (TextView) findViewById(R.id.stars_tv);
        mTvForks = (TextView) findViewById(R.id.forks_tv);
        mTvIssues = (TextView) findViewById(R.id.issues_tv);
        mTvLanguage = (TextView) findViewById(R.id.language_tv);
        mTvPrivateX = (CheckBox) findViewById(R.id.privateX_tv);
        mTvFork = (CheckBox) findViewById(R.id.fork_tv);
    }
}
