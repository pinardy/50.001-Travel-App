package com.example.user.travelapp;

import android.view.View;

/**
 * Created by muayanfrost on 18/11/16.
 */

public interface ClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}
