package com.kyh.knucommunity.viewmodel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.kyh.knucommunity.databinding.ActivityDataPostBinding;
import com.kyh.knucommunity.model.DTODataPost;
import com.kyh.knucommunity.model.RepositoryDataPost;
import com.kyh.knucommunity.ui.UserListActivity;

import java.util.HashMap;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

@SuppressLint("StaticFieldLeak")
public class DataPostViewModel {
    private static DataPostViewModel dataPostViewModel;

    private ActivityDataPostBinding binding;
    private Context context;

    private DataPostViewModel(LayoutInflater inflater, Context context) {
        this.binding = ActivityDataPostBinding.inflate(inflater);
        this.context = context;

        final Button[] BUTTONS = {
                binding.btn1,
                binding.btn2,
                binding.btn3,
                binding.btn4,
                binding.btn5,
        };

        for(Button btn : BUTTONS) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HashMap<String, Integer> params = new HashMap<String, Integer>();
                    params.put("index", Integer.parseInt(btn.getText().toString()));

                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... voids) {
                            RepositoryDataPost.requestData(params);
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            DTODataPost data = RepositoryDataPost.getData();
                            binding.result2.setText(data.toString());
                            super.onPostExecute(aVoid);
                        }
                    }.execute();
                }
            });
        }

        binding.nextBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserListActivity.class);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    public static DataPostViewModel getInstance() {
        return dataPostViewModel;
    }

    public static void setDataPostViewModel(LayoutInflater inflater, Context context) {
        DataPostViewModel.dataPostViewModel = new DataPostViewModel(inflater, context);
    }

    public ActivityDataPostBinding getBinding() {
        return binding;
    }
}
