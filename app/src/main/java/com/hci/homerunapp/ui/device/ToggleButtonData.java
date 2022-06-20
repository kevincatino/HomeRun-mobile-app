package com.hci.homerunapp.ui.device;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.material.button.MaterialButton;
import com.hci.homerunapp.MyApplication;
import com.hci.homerunapp.R;
import com.hci.homerunapp.data.remote.device.action.ActionBody;
import com.hci.homerunapp.ui.MainActivity;

public class ToggleButtonData extends ControlData{
    private boolean state;
    private String[] btnLabels;
    private String[] actionLabels;

    public ToggleButtonData(Context context, String[] btnLabels, String[] actionLabels, String deviceId) {
        super(context, R.layout.toggle_button_item, context.getResources().getString(R.string.blinds_state), deviceId);
        this.btnLabels = btnLabels;
        this.actionLabels = actionLabels;
    }

    public String getButtonText() {
        return btnLabels[state ? 1 : 0];
    }

    private String getLabelText() {
        return actionLabels[state ? 0 : 1];
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean getState() {
        return state;
    }



//    @Override
//    public void setupViewHolder(ControlDataAdapter.ViewHolder holder) {
//        super.setupViewHolder(holder);
//        ToggleButtonData.ViewHolder toggleButtonViewHolder = (ToggleButtonData.ViewHolder) holder;
//        MaterialButton toggleButton = toggleButtonViewHolder.getButton();
//        toggleButton.setText(getButtonText());
//        toggleButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setState(!getState());
//                toggleButton.setText(getButtonText());
//                holder.getControlText().setText(getActionLabel());
//
//            }
//        });
//
//    }

    @Override
    public String getActionLabel() {
        return String.format(super.getActionLabel(), getLabelText());
    }

    public static class ViewHolder extends ControlDataViewHolder<ToggleButtonData> {
        private final MaterialButton button;

        public ViewHolder(@NonNull View itemView, DeviceFragment deviceFragment) {
            super(itemView, deviceFragment);
            button = itemView.findViewById(R.id.device_button);
        }

        public MaterialButton getButton() {
            return button;
        }

        @Override
        public void bindTo(ToggleButtonData controlData) {
            super.bindTo(controlData);
//            ToggleButtonData.ViewHolder toggleButtonViewHolder = (ToggleButtonData.ViewHolder) holder;
            MaterialButton toggleButton = getButton();
            getControlText().setText(controlData.getActionLabel());
            toggleButton.setText(controlData.getButtonText());
            toggleButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    controlData.setState(!controlData.getState());
                    ((MyApplication)((MainActivity)context).getApplication()).getDeviceRepository().putAction(controlData.getDeviceId(), controlData.getState() ? "close" : "open", new ActionBody(), ViewHolder.this, true);
//                    toggleButton.setText(controlData.getButtonText());
//                    getControlText().setText(controlData.getActionLabel());

                }
            });
        }

    }
}
