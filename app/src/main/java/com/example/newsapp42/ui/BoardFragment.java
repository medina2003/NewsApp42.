package com.example.newsapp42.ui;

import static com.example.newsapp42.MainActivity.prefs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.newsapp42.BoardAdapter;
import com.example.newsapp42.OnClick.OnItemClickListener;
import com.example.newsapp42.R;
import com.example.newsapp42.databinding.FragmentBoardBinding;
import com.example.newsapp42.models.Article;

public class BoardFragment extends Fragment implements OnItemClickListener {
    private FragmentBoardBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBoardBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BoardAdapter adapter = new BoardAdapter(this);
        binding.viewPager.setAdapter(adapter);
        binding.dotsIndicator.setViewPager2(binding.viewPager);
        binding.skip.setOnClickListener(view1 -> close());
    }
    private void close() {
        prefs.saveState();
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onItemLongClick(Article position) {

    }

    @Override
    public void simpleClick() {
        NavController navController = Navigation.findNavController(requireActivity(),
                R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.navigation_home);
    }

    @Override
    public void onItemLongClick(String uri) {

    }
}