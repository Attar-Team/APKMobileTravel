package com.example.rahmatantravel.activity.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rahmatantravel.databinding.CustomListSurahBinding;
import com.example.rahmatantravel.dialog.dialogAudio.DialogAudio;
import com.example.rahmatantravel.dialog.dialogAyat.BottomSheetDialogDetailAyat;
import com.example.rahmatantravel.model.DataSurahItem;

import java.util.List;

public class SurahAdapter extends RecyclerView.Adapter<SurahAdapter.viewHolder> {
    List<DataSurahItem> listSurah;
    Activity activity;

    public SurahAdapter(List<DataSurahItem> listSurah, Activity activity) {
        this.listSurah = listSurah;
        this.activity = activity;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomListSurahBinding binding = CustomListSurahBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new viewHolder(binding, binding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.Bind(listSurah.get(position));
    }

    @Override
    public int getItemCount() {
        return listSurah.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final CustomListSurahBinding binding;
        private DataSurahItem dataSurahItem;

        public viewHolder(@NonNull CustomListSurahBinding itemView, CustomListSurahBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.llSurah.setOnClickListener(this);
            binding.ibPlay.setOnClickListener(this);
        }

        public void Bind(DataSurahItem data) {
            binding.tvNumberSurah.setText(data.getNomor());
            binding.tvNameSurahArab.setText(data.getAsma());
            binding.tvNameSurah.setText(data.getNama());
            binding.tvKota.setText(data.getType());
            binding.tvNomorAyat.setText(String.valueOf(data.getAyat()));
            dataSurahItem = data;
        }

        @Override
        public void onClick(View view) {
            if (view == binding.llSurah){
            BottomSheetDialogDetailAyat dialog = new BottomSheetDialogDetailAyat(activity, dataSurahItem);
            dialog.show();
            dialog.setCanceledOnTouchOutside(false);
            }
            else if (view == binding.ibPlay){
                DialogAudio dialogAudio = new DialogAudio(activity, dataSurahItem);
                dialogAudio.show();
                dialogAudio.setCanceledOnTouchOutside(false);
            }
        }
    }
}
