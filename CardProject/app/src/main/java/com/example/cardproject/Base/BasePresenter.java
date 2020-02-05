package com.example.cardproject.Base;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class BasePresenter    {


    @CallSuper
    void onCreate(@Nullable final Bundle savedInstance){
    }

    @CallSuper
    void onStart() {
    }

    @CallSuper
    void onResume() {
    }

    @CallSuper
    void onStop() {
    }

    @CallSuper
    void onPause() {
    }

    @CallSuper
    void onDestroy() {
    }

    @CallSuper
    void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    }


    @CallSuper
    void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    }

}
