package com.example.mike.week2weekend;

import java.math.BigInteger;

interface Callback{
    void callback( BigInteger bi );
}

public class FibonacciCalculator implements Runnable {
    int n;
    Callback c;

    public FibonacciCalculator( Callback c ){
        this.c = c;
    }

    @Override
    public void run() {
        BigInteger a,b;

        a = BigInteger.valueOf(1);
        b = BigInteger.valueOf(1);

        for (int i = 0; i < n; i++) {
            if ( i%2 == 0 ){
                a = a.add( b );
            }else{
                b = b.add( a );
            }
        }

        if ( (n-1)%2 == 0 ){
            this.c.callback( a );
        }else{
            this.c.callback( b );
        }
    }

    public int getX() {
        return n;
    }

    public FibonacciCalculator setX(int x) {
        this.n = x;
        return this;
    }
}
