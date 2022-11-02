package com.app.or.Universal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.or.Config.Universal;
import com.app.or.DTO.SimpleReview;
import com.app.or.R;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    private List<SimpleReview> list;

    public ListViewAdapter(List<SimpleReview> list){
        this.list = list;
    }

    public ListViewAdapter() {
    }
    public void setList(List<SimpleReview> list){
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Context context = viewGroup.getContext();
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.review_list, viewGroup, false);

        }

        TextView list_where  = view.findViewById(R.id.list_where);
        TextView list_title  = view.findViewById(R.id.list_title);
        TextView list_body  = view.findViewById(R.id.list_body);
        TextView list_good_cnt  = view.findViewById(R.id.list_good_cnt);
        TextView list_score = view.findViewById(R.id.list_score);

        SimpleReview temp = list.get(i);

        list_where.setText(Universal.memory.CodeToRegion(temp.getWhere())+"  "+temp.getCreateTime());
        list_title.setText((temp.getTitle().length()>10) ? temp.getTitle().substring(0,9)+"..." : temp.getTitle());
        list_body.setText(temp.getPreview());
        list_good_cnt.setText(""+temp.getGood());
        if(temp.getOwner_rating() != null){
            list_score.setText("총 별점 : "+String.format("%.1f", temp.getOwner_rating()));
        }else {
            list_score.setText("별점 없음");
        }
        return view;

    }
}
