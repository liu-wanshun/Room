package com.test.room;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {

    EditText editText_english, editText_chinese;
    Button button_submit;
    MyViewModel myViewModel;

    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myViewModel = ViewModelProviders.of(getActivity()).get(MyViewModel.class);
        editText_chinese = getView().findViewById(R.id.editText_chinese);
        editText_english = getView().findViewById(R.id.editText_english);
        button_submit = getView().findViewById(R.id.button_submit);

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chinese = editText_chinese.getText().toString().trim(),
                        english = editText_english.getText().toString().trim();
                if (!TextUtils.isEmpty(chinese) && !TextUtils.isEmpty(english)) {
                    Word word = new Word(english, chinese);
                    myViewModel.insertWords(word);
                    NavController navController = Navigation.findNavController(v);
                    navController.navigateUp();
                } else {
                    Toast.makeText(getActivity(), "请输入英语和汉语", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
