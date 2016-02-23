package com.example.germangirod.rxjavaexample.util;

import android.app.Activity;
import android.content.Context;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.germangirod.rxjavaexample.R;
import com.example.germangirod.rxjavaexample.data.storage.MyLocationDBManager;
import com.example.germangirod.rxjavaexample.data.storage.StorageData;

/**
 * Created by germangirod on 1/26/16.
 */
public class DialogUtil {

    private MaterialDialog.Builder builder;
    private StorageData storageData;

    public void createSaveDialog(Activity context, final MyLocationDBManager myLocationDBManager, String cityName, final String cityId,
            final StorageData storageData) {

        this.storageData = storageData;
        final MaterialDialog dialog;
        builder = setMaterialDialogStyles(context);
        builder.title(cityName).content("Are you sure you want to save this location").positiveText("SAVE").negativeText("CANCEL");
        dialog = builder.build();
        builder.onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {

                myLocationDBManager.saveCity(cityId);
                storageData.onStorageActionFinished(cityId);
            }
        });

        dialog.show();
    }

    public void createDeleteDialog(Activity context, final MyLocationDBManager myLocationDBManager, final String cityName, final String cityId,
            final StorageData storageData) {

        this.storageData = storageData;
        final MaterialDialog dialog;
        builder = setMaterialDialogStyles(context);
        builder.title(cityName).content("Are you sure you want to remove this location").positiveText("REMOVE").negativeText("CANCEL");
        dialog = builder.build();
        builder.onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                myLocationDBManager.deleteCity(cityId);
                storageData.onStorageActionFinished(cityId);
            }
        });
        builder.onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {

            }
        });

        dialog.show();
    }

    private MaterialDialog.Builder setMaterialDialogStyles(Context context) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        builder.titleColorRes(R.color.black)
                .contentColorRes(R.color.black)
                .backgroundColorRes(R.color.white)
                .positiveColorRes(R.color.colorPrimary)
                .negativeColorRes(R.color.colorPrimary);

        return builder;
    }
}
