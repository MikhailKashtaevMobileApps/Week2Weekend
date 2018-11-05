package com.example.mike.week2weekend;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.math.BigInteger;

public class FactorialCalculator extends AsyncTaskLoader<BigInteger> {

    private static final String TAG = "__TAG__";
    BigInteger x;

    public FactorialCalculator(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public BigInteger loadInBackground() {
        // y=x!
        Log.d(TAG, "loadInBackground: Factorial Calculator x="+x.toString());
        BigInteger y = BigInteger.valueOf(1);

        int i_x = x.intValue();

        for (int i = 1; i <= i_x; i++) {
            y = y.multiply( BigInteger.valueOf(i) );
        }

        return y;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
        super.onStartLoading();
    }

    public BigInteger getX() {
        return x;
    }

    public void setX(BigInteger x) {
        this.x = x;
    }
}
