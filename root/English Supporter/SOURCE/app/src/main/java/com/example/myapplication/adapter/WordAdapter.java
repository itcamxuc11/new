package com.example.myapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
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
import com.example.myapplication.model.DatabaseAccess;
import com.example.myapplication.model.Topic;
import com.example.myapplication.model.Word;

import org.xmlpull.v1.XmlPullParser;

import java.util.List;

import static android.widget.PopupMenu.*;

public class WordAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Word> list;
    private DatabaseAccess databaseAccess;
    public WordAdapter(Context context, int layout, List<Word> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
        databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();
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
        TextView txtWord = convertView.findViewById(R.id.word_item);
        final Word word = list.get(position);
        txtWord.setText(word.getVocabulary());

        final ImageButton banRemember = convertView.findViewById(R.id.remeber);

        if(word.getStatus().equals("5")) {
            banRemember.setImageResource(R.drawable.star_gold);
        }

        else banRemember.setImageResource(R.drawable.star_white);
        banRemember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(word.getStatus().equals("5")) {
                    banRemember.setImageResource(R.drawable.star_white);
                    word.setStatus("1");
                    databaseAccess.RemoveToRememberList(word.getId());
                }
                else {
                    databaseAccess.addToRememberList(word.getId());
                    word.setStatus("5");
                    banRemember.setImageResource(R.drawable.star_gold);
                }
            }
        });
        return convertView;
    }
}
