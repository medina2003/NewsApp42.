package com.example.newsapp42;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp42.OnClick.OnItemClickListener;
import com.example.newsapp42.databinding.PagerBoardBinding;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private final String[] titles = new String[]{"Enkanto", "Enkanto", "Enkanto"};
    private final String[] description = new String[]{"Mirabel", "Izalita", "Izabella"};
    private final int[] getImage = new int[]{R.drawable.gg, R.drawable.mm, R.drawable.ss};
    private final OnItemClickListener click;

    public BoardAdapter(OnItemClickListener click) {
        this.click = click;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PagerBoardBinding binding = PagerBoardBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final PagerBoardBinding binding;

        public ViewHolder(@NonNull PagerBoardBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
        public void bind(int position) {
            binding.imageViewBoard.setImageResource(getImage[position]);
            binding.textViewBoard.setText(titles[position]);
            binding.textViewBoard2.setText(description[position]);
            if (position != getItemCount() - 1) {
                binding.btnBoardWelcome.setVisibility(View.GONE);
            } else {
                binding.btnBoardWelcome.setVisibility(View.VISIBLE);
                binding.btnBoardWelcome.setOnClickListener(view -> click.simpleClick());
            }
        }
    }
}
