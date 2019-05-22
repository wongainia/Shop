package com.zhenghaikj.shop.receiver;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import com.tencent.android.mipush.XMPushMessageReceiver;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

public class XMReceiver extends XMPushMessageReceiver {

    public XMReceiver() {
        super();
    }

    @Override
    public void onRecive(Context context, Intent intent) {
        super.onRecive(context, intent);
//        openAssetMusics(context,"new_messsage_voice.mp3");
    }

    @Override
    public void onReceivePassThroughMessage(Context context, MiPushMessage miPushMessage) {
        super.onReceivePassThroughMessage(context, miPushMessage);
    }

    @Override
    public void onNotificationMessageClicked(Context context, MiPushMessage miPushMessage) {
        super.onNotificationMessageClicked(context, miPushMessage);
    }

    @Override
    public void onNotificationMessageArrived(Context context, MiPushMessage miPushMessage) {
        super.onNotificationMessageArrived(context, miPushMessage);
        if ("您有新工单".equals(miPushMessage.getTitle())){
            openAssetMusics(context,"new_order_voice.mp3");
            EventBus.getDefault().post("0");
        }
    }

    @Override
    public void onCommandResult(Context context, MiPushCommandMessage miPushCommandMessage) {
        super.onCommandResult(context, miPushCommandMessage);
//        openAssetMusics(context,"new_messsage_voice.mp3");
    }

    @Override
    public void onReceiveMessage(Context context, MiPushMessage miPushMessage) {
        super.onReceiveMessage(context, miPushMessage);
//        openAssetMusics(context,"new_messsage_voice.mp3");
    }

    @Override
    public void onReceiveRegisterResult(Context context, MiPushCommandMessage miPushCommandMessage) {
        super.onReceiveRegisterResult(context, miPushCommandMessage);
    }

    @Override
    public void onRequirePermissions(Context context, String[] strings) {
        super.onRequirePermissions(context, strings);
    }

    /**
     * 打开assets下的音乐mp3文件
     */
    private void openAssetMusics(Context context,String fileName) {

        try {
            //播放 assets/a2.mp3 音乐文件
            AssetFileDescriptor fd = context.getAssets().openFd(fileName);
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
