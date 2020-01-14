package com.test.room;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<Word> allWords = new ArrayList<>();
    private MyViewModel myViewModel;
    private boolean iscard;

    public MyAdapter(Boolean iscard, MyViewModel myViewModel) {
        this.myViewModel = myViewModel;
        this.iscard = iscard;
    }

    public void setAllWords(List<Word> allWords) {
        this.allWords = allWords;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView;
        if (iscard) {
            itemView = layoutInflater.inflate(R.layout.card_item, parent, false);
        } else {
            itemView = layoutInflater.inflate(R.layout.normal_item, parent, false);
        }

        final MyViewHolder holder = new MyViewHolder(itemView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://m.youdao.com/dict?le=eng&q=" + holder.textView_words.getText());
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(uri);
                holder.itemView.getContext().startActivity(intent);
            }
        });


        holder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Word word = (Word) holder.itemView.getTag(R.id.Word_for_ViewHolder);
                if (isChecked) {
                    word.setMeaning_invisible(true);
                    myViewModel.updataWords(word);
                } else {
                    word.setMeaning_invisible(false);
                    myViewModel.updataWords(word);
                }
            }
        });


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Word word = allWords.get(position);

        holder.itemView.setTag(R.id.Word_for_ViewHolder, word);

        holder.textView_id.setText(String.valueOf(word.getId()));
        holder.textView_words.setText(word.getWord());
        holder.textView_meaning.setText(word.getMeaning());


        if (word.isMeaning_invisible()) {
            holder.textView_meaning.setVisibility(View.GONE);
            holder.aSwitch.setChecked(true);
        } else {
            holder.textView_meaning.setVisibility(View.VISIBLE);
            holder.aSwitch.setChecked(false);

        }


    }

    @Override
    public int getItemCount() {
        return allWords.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView_id, textView_words, textView_meaning;
        Switch aSwitch;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_id = itemView.findViewById(R.id.item_id);
            textView_words = itemView.findViewById(R.id.item_word);
            textView_meaning = itemView.findViewById(R.id.item_meaning);
            aSwitch = itemView.findViewById(R.id.switch_invisible);

        }
    }
}
