package com.kyh.movieapp.viewmodel;

import android.annotation.SuppressLint;
import android.content.Context;

import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.kyh.movieapp.databinding.ActivitySearchBinding;
import com.kyh.movieapp.model.repositories.RepositoryHome;
import com.kyh.movieapp.model.repositories.RepositoryLikeList;
import com.kyh.movieapp.model.repositories.RepositorySearch;
import com.kyh.movieapp.model.repositories.RepositorySearchResult;
import com.kyh.movieapp.view.HomeFragment;
import com.kyh.movieapp.view.LikeListFragment;
import com.kyh.movieapp.view.SearchResultFragment;

import java.io.IOException;

@SuppressLint("StaticFieldLeak")
public class SearchViewModel {
    private static SearchViewModel searchViewModel;
    
    private ActivitySearchBinding binding;
    private Context context;
    private AppCompatActivity appCompatActivity;
    private int start = 1;

    private SearchViewModel(LayoutInflater inflater, Context context, AppCompatActivity appCompatActivity) {
        this.binding = ActivitySearchBinding.inflate(inflater);
        this.context = context;

        binding.searchBottomNavSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(binding.searchResultLayout.getId(), new SearchResultFragment());
                fragmentTransaction.commit();
            }
        });

        binding.searchBottomNavHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RepositoryHome.setContext(context);
                FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(binding.searchResultLayout.getId(), new HomeFragment());
                fragmentTransaction.commit();
            }
        });

        binding.searchBottomNavLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RepositoryLikeList.setContext(context);
                FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(binding.searchResultLayout.getId(), new LikeListFragment());
                fragmentTransaction.commit();
            }
        });

        binding.searchResultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("LogicPath", "Clicked");
                RepositorySearchResult.setContext(context);
                RepositoryHome.addHistory(binding.searchInput.getText().toString());

                new AsyncTask<Void, Void, Void>() {
                    private boolean isSuccess;
                    private int errorCode;

                    private boolean request(String query, int start) throws IOException {
                        boolean result = RepositorySearch.requestData(query, start);
                        if(isSuccess = result) {
                            Log.d("RetrofitSuccess", RepositorySearch.getResponseMessage());
                        }
                        return result;
                    }

                    private void handleError(int error) {
                        switch (error) {
                            case ErrorType.NOT_FOUND:
                                Log.d("RetrofitError", "Result Not Found");
                                errorCode = ErrorType.NOT_FOUND;
                                break;
                            default:
                                Log.d("RetrofitError", "Unhandled Error : " + RepositorySearch.getResponseCode());
                        }
                    }

                    private void handleNotFound() {
                        Toast.makeText(context, "서버가 불안정 합니다. 다시 시도 해주세요.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    protected Void doInBackground(Void... voids) {
                        try {
                            start = 1;
                            if(!request(binding.searchInput.getText().toString(), start)) {
                                handleError(RepositorySearch.getResponseCode());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.d("RetrofitFailure", "Retrofit was Failed with IOException");
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        if(isSuccess) {
                            RepositorySearchResult.setItems(RepositorySearch.getData().getItems());
                            FragmentManager fm = appCompatActivity.getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fm.beginTransaction();
                            fragmentTransaction.replace(binding.searchResultLayout.getId(), new SearchResultFragment());
                            fragmentTransaction.commit();
                        } else if (errorCode == ErrorType.NOT_FOUND) {
                            handleNotFound();
                        }
                    }
                }.execute();
            }
        });
    }

    public static SearchViewModel getInstance() {
        return searchViewModel;
    }

    public static void setSearchViewModel(LayoutInflater inflater, Context context, AppCompatActivity appCompatActivity) {
        SearchViewModel.searchViewModel = new SearchViewModel(inflater, context, appCompatActivity);
    }

    public void requestAddItem() {
        if(start + RepositorySearch.DISPLAY_PER_PAGE <= RepositorySearch.getData().getTotal()) {
            new AsyncTask<Void, Void, Void>() {
                private boolean isSuccess;
                private int errorCode;

                private boolean request(String query, int start) throws IOException {
                    boolean result = RepositorySearch.requestData(query, start);
                    if(isSuccess = result) {
                        Log.d("RetrofitSuccess", RepositorySearch.getResponseMessage());
                    }
                    return result;
                }

                private void handleError(int error) {
                    switch (error) {
                        case ErrorType.NOT_FOUND:
                            Log.d("RetrofitError", "Result Not Found");
                            errorCode = ErrorType.NOT_FOUND;
                            break;
                        default:
                            Log.d("RetrofitError", "Unhandled Error : " + RepositorySearch.getResponseCode());
                    }
                }

                private void handleNotFound() {
                    Toast.makeText(context, "서버가 불안정 합니다. 다시 시도 해주세요.", Toast.LENGTH_SHORT).show();
                }

                @Override
                protected Void doInBackground(Void... voids) {
                    try {
                        start += RepositorySearch.DISPLAY_PER_PAGE;
                        if(!request(binding.searchInput.getText().toString(), start)) {
                            handleError(RepositorySearch.getResponseCode());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.d("RetrofitFailure", "Retrofit was Failed with IOException");
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    if(isSuccess) {
                        RepositorySearchResult.addItems(RepositorySearch.getData().getItems());
                        SearchResultViewModel.getInstance().getBinding().searchResultLoading.setVisibility(View.GONE);
                    } else if (errorCode == ErrorType.NOT_FOUND) {
                        handleNotFound();
                    }
                }
            }.execute();
        }
    }

    public ActivitySearchBinding getBinding() {
        return binding;
    }
}