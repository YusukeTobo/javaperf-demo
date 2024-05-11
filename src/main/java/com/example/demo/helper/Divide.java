package com.example.demo.helper;

public class Divide {
    static {
        System.loadLibrary("div");
    }

    public void doDivide(int n) {
        ndiv(n);
    }

    private native void ndiv(int n);
}
