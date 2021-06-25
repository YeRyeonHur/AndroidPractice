package com.kyh.knucommunity.viewmodel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;

import androidx.lifecycle.ViewModel;

import com.kyh.knucommunity.databinding.ActivityDataGetBinding;
import com.kyh.knucommunity.model.DTODataGet;
import com.kyh.knucommunity.model.RepositoryDataGet;
import com.kyh.knucommunity.ui.DataPostActivity;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

@SuppressLint("StaticFieldLeak")
public class DataGetViewModel extends ViewModel {
    private static DataGetViewModel dataGetViewModel;

    private ActivityDataGetBinding binding;
    private Context context;

    private DataGetViewModel(LayoutInflater inflater, Context context) {
        this.binding = ActivityDataGetBinding.inflate(inflater);
        this.context = context;

        binding.getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        RepositoryDataGet.requestData(binding.input.getText().toString());
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);

                        DTODataGet data = RepositoryDataGet.getData();

                        if(data == null) {
                            binding.result.setText("Error with Request Data Failure");
                        } else {
                            binding.result.setText(data.getMsg());
                        }
                    }
                }.execute();
            }
        });

        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DataPostActivity.class);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    public static DataGetViewModel getInstance() {
        return dataGetViewModel;
    }

    public static void setDataGetViewModel(LayoutInflater inflater, Context context) {
        DataGetViewModel.dataGetViewModel = new DataGetViewModel(inflater, context);
    }

    public ActivityDataGetBinding getBinding() {
        return this.binding;
    }
}
