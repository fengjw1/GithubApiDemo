package com.github.githubapidemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.githubapidemo.R;
import com.github.githubapidemo.model.DataBean;
import com.github.githubapidemo.utils.Constants;

/**
 * RecyclerAdapter
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> implements View.OnClickListener{

    private Context mContext;

    private DataBean mDataBean;
    private OnItemClickListener mOnItemClickListener = null;

    private static final int COUNT = 100;

    public RecyclerAdapter(Context context){
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        view.setOnClickListener(this);//view支持点击
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ItemViewHolder holder, int position) {
        holder.nameTv.setText(mDataBean.getItems().get(position).getName());
        holder.loginTv.setText(mDataBean.getItems().get(position).getOwner().getLogin());
        holder.scoreTv.setText(String.valueOf(mDataBean.getItems().get(position).getScore()));
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        if (mDataBean != null){
            if (mDataBean.getItems().size() > COUNT){
                return COUNT;
            }else {
                return mDataBean.getItems().size() - 1;
            }
        }
        return 0;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null){
            Constants.debug("mOnItemClickListener != null");
            mOnItemClickListener.onItemClick(v, mDataBean.getItems().get((int)v.getTag()));
        }
    }

    public static interface OnItemClickListener{
        void onItemClick(View view, DataBean.ItemsBean itemsBean );
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener;
    }

    public void setDataBean(DataBean dataBean){
        mDataBean = dataBean;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView nameTv, loginTv, scoreTv;

        public ItemViewHolder(View v){
            super(v);
            nameTv = v.findViewById(R.id.name_tv);
            loginTv = v.findViewById(R.id.login_tv);
            scoreTv = v.findViewById(R.id.score_tv);
        }

    }

}
