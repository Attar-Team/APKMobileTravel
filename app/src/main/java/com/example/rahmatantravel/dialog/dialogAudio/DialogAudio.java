package com.example.rahmatantravel.dialog.dialogAudio;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.SeekBar;

import androidx.annotation.NonNull;

import com.example.rahmatantravel.R;
import com.example.rahmatantravel.databinding.AudioDialogBinding;
import com.example.rahmatantravel.model.DataSurahItem;

import java.io.IOException;

public class DialogAudio extends Dialog implements View.OnClickListener {
    private Context context;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private Handler handler = new Handler();
    private AudioDialogBinding binding;

    public DialogAudio(@NonNull Context context, DataSurahItem dataSurahItem) {
        super(context);
        this.context = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        binding = AudioDialogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SetContent(dataSurahItem);
        SetOnTouchSeekBar();
        binding.ibPlay.setOnClickListener(this);
        binding.ibClose.setOnClickListener(this);
    }

    private void SetOnTouchSeekBar() {
        binding.sbDuration.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                SeekBar seekBar = (SeekBar) view;
                int playPosition = (mediaPlayer.getDuration() / 100) * seekBar.getProgress();
                mediaPlayer.seekTo(playPosition);
                binding.tvDuration.setText(milliSecondToTimer(mediaPlayer.getCurrentPosition()));
                return false;
            }
        });

        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                binding.sbDuration.setSecondaryProgress(i);
            }
        });
    }

    private void SetContent(DataSurahItem dataSurah) {
        binding.tvSurah.setText(dataSurah.getNama());
        binding.tvAyat.setText(String.valueOf(dataSurah.getAyat()));
        prepareMediaPlayer(dataSurah.getAudio());
        binding.tvTotalDuration.setText(milliSecondToTimer(mediaPlayer.getDuration()));
    }

    private void prepareMediaPlayer(String url){
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        updateSeekbar();
    }
    private Runnable updater = new Runnable() {
        @Override
        public void run() {
            updateSeekbar();
            long curentDuration = mediaPlayer.getCurrentPosition();
            binding.tvDuration.setText(milliSecondToTimer(curentDuration));
        }
    };
    private void updateSeekbar(){
        if (mediaPlayer.isPlaying()){
            binding.sbDuration.setProgress((int) (((float)mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration()) * 100));
            handler.postDelayed(updater, 1000);
        }
    }
    private String milliSecondToTimer(long millisecond){
        String timerString = "";
        String secondString = "";
        int hours = (int) (millisecond / (1000*60*60));
        int minutes = (int) (millisecond % (1000*60*60)) / (1000*60);
        int seconds = (int) (millisecond % (1000*60*60)) % (1000*60) / 1000;
        if (hours > 0){
            timerString = hours + ":";
        }
        if (seconds < 10 ){
            secondString = "0" + seconds;
        }else {
            secondString = "" + seconds;
        }
        timerString = timerString + minutes + ":" +secondString;
        return timerString;
    }
    @Override
    public void onClick(View view) {
        if (view == binding.ibPlay){
            if (mediaPlayer.isPlaying()){
                handler.removeCallbacks(updater);
                mediaPlayer.pause();
                binding.ibPlay.setBackground(context.getDrawable(R.drawable.icon_play_arrow_black_64dp));
            }else {
                mediaPlayer.start();
                binding.ibPlay.setBackground(context.getDrawable(R.drawable.icon_pause_black_64dp));
                updateSeekbar();
            }
        }
        else if (view == binding.ibClose){
            mediaPlayer.stop();
            dismiss();
        }
    }
}
