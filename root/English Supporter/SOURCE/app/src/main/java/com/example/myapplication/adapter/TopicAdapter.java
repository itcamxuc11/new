package com.example.myapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.myapplication.LessonActivity;
import com.example.myapplication.ListWordActivity;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.model.Topic;

import org.xmlpull.v1.XmlPullParser;

import java.util.List;

import static android.widget.PopupMenu.*;

public class TopicAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Topic> list;

    public TopicAdapter(Context context, int layout, List<Topic> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @SuppressLint("ViewHolder")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        assert layoutInflater != null;
        convertView = layoutInflater.inflate(layout, null);
        TextView txtTopicName = convertView.findViewById(R.id.txtTopicName);
        ImageView imgAvt = convertView.findViewById(R.id.imgAvt);
        Topic topic = list.get(position);
        txtTopicName.setText(topic.getName());
        final ImageButton popUp_btn =  convertView.findViewById(R.id.popUpMenu);
        popUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu popup = new PopupMenu(context, popUp_btn);
                popup.getMenuInflater().inflate(R.menu.context_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        int i = item.getItemId();
                        if (i == R.id.item1) {
                            Intent intent = new Intent(context, LessonActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("id", String.valueOf(position+1));
                            intent.putExtras(bundle);
                            context.startActivity(intent);
                            return true;
                        }
                        else if (i == R.id.item2){
                            //do something
                            Intent intent = new Intent(context, ListWordActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("id", String.valueOf(position+1));
                            intent.putExtras(bundle);
                            context.startActivity(intent);
                            return true;
                        }
                        else {
                            return onMenuItemClick(item);
                        }
                    }
                });
                popup.show();
            }
        });
        return convertView;
    }
}
