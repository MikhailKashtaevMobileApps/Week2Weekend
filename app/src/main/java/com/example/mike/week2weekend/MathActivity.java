package com.example.mike.week2weekend;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigInteger;


public class MathActivity extends MenuActivity implements LoaderManager.LoaderCallbacks<BigInteger> {

    private static final int OPERATION_FACTORIAL_CALCULATION = 0;
    private static final int OPERATION_FIBONACCI_CALCULATION = 0;
    private static final String TAG = "__TAG__";
    private TextView mathResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setInclude( R.layout.activity_math);
        mathResult = findViewById( R.id.tvMathResult );
    }

    public void onFactorial(View view) {
        // Using loaders to calculate
        BigInteger x = BigInteger.valueOf( Long.valueOf( ((EditText)findViewById( R.id.etFactorial ) ).getText().toString()));
        Bundle bundle = new Bundle();
        bundle.putString( "x", x.toString() );
        getSupportLoaderManager().initLoader(OPERATION_FIBONACCI_CALCULATION, bundle, this);
    }

    public void onFibonacci(View view) {
        // Using HandlerThread to calculate
        HandlerThread handlerThread = new HandlerThread( "fibonacci" );
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        Handler handler = new Handler(looper);

        int x = Integer.valueOf(((EditText)findViewById( R.id.etFactorial ) ).getText().toString());

        mathResult.setText( "Started fibonacci calculation" );

        handler.post( new FibonacciCalculator(new Callback() {
            @Override
            public void callback(BigInteger bi) {
                mathResult.setText( bi.toString() );
            }
        }).setX( x ) );
    }

    @NonNull
    @Override
    public Loader<BigInteger> onCreateLoader(int i, @Nullable Bundle bundle) {
        if ( i == OPERATION_FACTORIAL_CALCULATION ){
            Log.d(TAG, "onCreateLoader: ");
            mathResult.setText( "Started calculation" );
            FactorialCalculator factorialCalculator = new FactorialCalculator(this);
            factorialCalculator.setX( BigInteger.valueOf( Long.valueOf(bundle.getString("x"))) );
            return factorialCalculator;
        }
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<BigInteger> loader, BigInteger bigInteger) {
        Log.d(TAG, "onLoadFinished: ");
        mathResult.setText( bigInteger.toString() );
        getSupportLoaderManager().destroyLoader(OPERATION_FIBONACCI_CALCULATION);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<BigInteger> loader) {

    }
}
