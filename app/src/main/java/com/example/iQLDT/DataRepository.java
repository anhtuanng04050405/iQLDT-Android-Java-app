package com.example.iQLDT;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepository {

    private static final String TAG = "DataRepository";

    private static DataRepository instance;

    public static DataRepository getInstance() {
        if (instance == null) {
            instance = new DataRepository();
        }
        return instance;
    }

    private List<item_api> cachedData = null;
    private boolean isFetching = false;

    public interface OnDataLoadedListener {
        void onSuccess(List<item_api> data);
        void onFailure(String errorMessage);
    }

    private final List<OnDataLoadedListener> pendingListeners = new ArrayList<>();

    public void getData(OnDataLoadedListener listener) {
        if (cachedData != null) {
            listener.onSuccess(new ArrayList<>(cachedData));
            return;
        }

        pendingListeners.add(listener);

        if (isFetching) {
            return;
        }

        isFetching = true;
        RetrofitClient.getApiService().getPosts().enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                isFetching = false;
                if (response.isSuccessful() && response.body() != null
                        && response.body().getData() != null) {
                    cachedData = new ArrayList<>(response.body().getData());
                    notifySuccess();
                } else {
                    Log.e(TAG, "API lỗi: " + response.code());
                    notifyFailure("API lỗi: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                isFetching = false;
                Log.e(TAG, "Gọi API thất bại: " + t.getMessage());
                notifyFailure("Gọi API thất bại: " + t.getMessage());
            }
        });
    }

    public void invalidateCache() {
        cachedData = null;
    }

    private void notifySuccess() {
        List<OnDataLoadedListener> listeners = new ArrayList<>(pendingListeners);
        pendingListeners.clear();
        for (OnDataLoadedListener l : listeners) {
            l.onSuccess(new ArrayList<>(cachedData));
        }
    }

    private void notifyFailure(String msg) {
        List<OnDataLoadedListener> listeners = new ArrayList<>(pendingListeners);
        pendingListeners.clear();
        for (OnDataLoadedListener l : listeners) {
            l.onFailure(msg);
        }
    }
}
