package com.test.room;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WordsFragment extends Fragment {

    MyAdapter myAdapter1, myAdapter2;
    MyViewModel myViewModel;
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;


    public WordsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_words, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myViewModel = ViewModelProviders.of(getActivity()).get(MyViewModel.class);
        recyclerView = getView().findViewById(R.id.recyclerView);
        floatingActionButton = getView().findViewById(R.id.floatingActionButton);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myAdapter1 = new MyAdapter(false, myViewModel);
        myAdapter2 = new MyAdapter(true, myViewModel);

        recyclerView.setAdapter(myAdapter1);

        myViewModel.getAllWords().observe(getActivity(), new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                myAdapter1.setAllWords(words);
                myAdapter2.notifyDataSetChanged();

                myAdapter1.notifyDataSetChanged();
                myAdapter2.notifyDataSetChanged();

            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_wordsFragment_to_addFragment);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }
}
