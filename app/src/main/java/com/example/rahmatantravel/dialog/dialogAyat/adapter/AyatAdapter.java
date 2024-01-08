package com.example.rahmatantravel.dialog.dialogAyat.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rahmatantravel.R;
import com.example.rahmatantravel.databinding.CustomListAyatBinding;
import com.example.rahmatantravel.model.DataAyatItem;

import java.util.List;

public class AyatAdapter extends RecyclerView.Adapter<AyatAdapter.viewHolder> {
    private Context mContext;
    private List<DataAyatItem>listAyat;

    public AyatAdapter(Context mContext, List<DataAyatItem> listAyat) {
        this.mContext = mContext;
        this.listAyat = listAyat;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomListAyatBinding binding = CustomListAyatBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new viewHolder(binding, binding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.Bind(listAyat.get(position));
    }

    @Override
    public int getItemCount() {
        return listAyat.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private final CustomListAyatBinding binding;
        public viewHolder(@NonNull CustomListAyatBinding itemView, CustomListAyatBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void Bind(DataAyatItem data) {
            binding.tvAyatArab.setText(data.getAr());
            binding.tvAyatIndo.setText(Html.fromHtml(data.getTr()));
            binding.tvArti.setText(data.getId());
            binding.tvNumberAyat.setText(data.getNomor());
            if (Integer.valueOf(data.getNomor()) % 2 == 0){
                binding.llBackground.setBackground(mContext.getDrawable(R.drawable.custom_bg_radius10_white));
                binding.line.setBackground(mContext.getDrawable(R.drawable.custom_bg_radius10_green));
            }
        }
    }
}
