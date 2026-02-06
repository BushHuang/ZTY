package com.google.zxing.client.android;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.util.Log;
import java.io.Closeable;
import java.io.IOException;

public final class BeepManager implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, Closeable {
    private static final float BEEP_VOLUME = 0.1f;
    private static final String TAG = BeepManager.class.getSimpleName();
    private static final long VIBRATE_DURATION = 200;
    private final Activity activity;
    private boolean playBeep;
    private boolean beepEnabled = true;
    private boolean vibrateEnabled = false;
    private MediaPlayer mediaPlayer = null;

    public BeepManager(Activity activity) {
        this.activity = activity;
        updatePrefs();
    }

    private MediaPlayer buildMediaPlayer(Context context) throws IllegalStateException, Resources.NotFoundException, IOException {
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(3);
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnErrorListener(this);
        try {
            AssetFileDescriptor assetFileDescriptorOpenRawResourceFd = context.getResources().openRawResourceFd(R.raw.zxing_beep);
            try {
                mediaPlayer.setDataSource(assetFileDescriptorOpenRawResourceFd.getFileDescriptor(), assetFileDescriptorOpenRawResourceFd.getStartOffset(), assetFileDescriptorOpenRawResourceFd.getLength());
                assetFileDescriptorOpenRawResourceFd.close();
                mediaPlayer.setVolume(0.1f, 0.1f);
                mediaPlayer.prepare();
                return mediaPlayer;
            } catch (Throwable th) {
                assetFileDescriptorOpenRawResourceFd.close();
                throw th;
            }
        } catch (IOException e) {
            Log.w(TAG, e);
            mediaPlayer.release();
            return null;
        }
    }

    private static boolean shouldBeep(boolean z, Context context) {
        if (!z || ((AudioManager) context.getSystemService("audio")).getRingerMode() == 2) {
            return z;
        }
        return false;
    }

    @Override
    public synchronized void close() {
        if (this.mediaPlayer != null) {
            this.mediaPlayer.release();
            this.mediaPlayer = null;
        }
    }

    public boolean isBeepEnabled() {
        return this.beepEnabled;
    }

    public boolean isVibrateEnabled() {
        return this.vibrateEnabled;
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) throws IllegalStateException {
        mediaPlayer.seekTo(0);
    }

    @Override
    public synchronized boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        if (i == 100) {
            this.activity.finish();
        } else {
            mediaPlayer.release();
            this.mediaPlayer = null;
            updatePrefs();
        }
        return true;
    }

    public synchronized void playBeepSoundAndVibrate() {
        if (this.playBeep && this.mediaPlayer != null) {
            this.mediaPlayer.start();
        }
        if (this.vibrateEnabled) {
            ((Vibrator) this.activity.getSystemService("vibrator")).vibrate(200L);
        }
    }

    public void setBeepEnabled(boolean z) {
        this.beepEnabled = z;
    }

    public void setVibrateEnabled(boolean z) {
        this.vibrateEnabled = z;
    }

    public synchronized void updatePrefs() {
        boolean zShouldBeep = shouldBeep(this.beepEnabled, this.activity);
        this.playBeep = zShouldBeep;
        if (zShouldBeep && this.mediaPlayer == null) {
            this.activity.setVolumeControlStream(3);
            this.mediaPlayer = buildMediaPlayer(this.activity);
        }
    }
}
