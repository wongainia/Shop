package com.zhenghaikj.shop.imkfsdk.chat.chatrow;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;

import com.moor.imkf.model.entity.FromToMessage;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.imkfsdk.chat.ChatActivity;
import com.zhenghaikj.shop.imkfsdk.chat.holder.BaseHolder;
import com.zhenghaikj.shop.imkfsdk.chat.holder.VoiceViewHolder;

/**
 * Created by longwei on 2016/3/9.
 */
public class VoiceTxChatRow extends BaseChatRow {

    public VoiceTxChatRow(int type) {
        super(type);
    }

    @Override
    public boolean onCreateRowContextMenu(ContextMenu contextMenu, View targetView, FromToMessage detail) {
        return false;
    }

    @Override
    protected void buildChattingData(Context context, BaseHolder baseHolder, FromToMessage detail, int position) {
        final VoiceViewHolder holder = (VoiceViewHolder) baseHolder;
        final FromToMessage message = detail;

        if(message != null) {

            VoiceViewHolder.initVoiceRow(holder, detail, position, (ChatActivity) context, false);

            View.OnClickListener listener = ((ChatActivity)context).getChatAdapter().getOnClickListener();
            getMsgStateResId(position, holder, message, listener);
        }
    }

    @Override
    public View buildChatView(LayoutInflater inflater, View convertView) {

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.kf_chat_row_voice_tx, null);
            VoiceViewHolder holder = new VoiceViewHolder(mRowType);
            convertView.setTag(holder.initBaseHolder(convertView, false));
        }

        return convertView;
    }

    @Override
    public int getChatViewType() {
        return ChatRowType.VOICE_ROW_TRANSMIT.ordinal();
    }
}
