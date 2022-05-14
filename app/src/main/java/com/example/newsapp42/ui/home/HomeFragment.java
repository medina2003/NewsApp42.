package com.example.newsapp42.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.newsapp42.App;
import com.example.newsapp42.OnClick.OnItemClickListener;
import com.example.newsapp42.R;
import com.example.newsapp42.databinding.FragmentHomeBinding;
import com.example.newsapp42.models.Article;

import java.util.List;

public class HomeFragment extends Fragment implements OnItemClickListener {

    private FragmentHomeBinding binding;
    private HomeAdapter adapter;
    private List<Article> articleList;

    public HomeFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new HomeAdapter(this);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.fab.setOnClickListener(view1 -> openFragment());

        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                articleList = App.db.dao().getAllWithTitle(editable.toString());
                adapter.addList(articleList);
            }
        });

        binding.recyclerView.setAdapter(adapter);
        articleList = App.db.dao().getAllArticles();
        adapter.addList(articleList);

    }

    private void openFragment() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.newsFragment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onItemLongClick(Article position) {
        delete();
    }

    private void delete() {
        AlertDialog.Builder itemView = new AlertDialog.Builder(requireActivity());
        itemView.setMessage("Do you want to delete?");
        itemView.setPositiveButton("yes",
                (dialogInterface, i) -> {
                });
        itemView.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }

            private void finish() {
            }
        });
        AlertDialog alertDialog = itemView.create();
        alertDialog.show();
    }

    @Override
    public void simpleClick() {

    }

    @Override
    public void onItemLongClick(String uri) {

    }
}