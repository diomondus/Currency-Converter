package com.butilov;

/**
 * Created by Dmitry Butilov
 * on 23.02.18.
 */
public interface TestInterface {
    private int foo(){
        return 5;
    }

    int foop(int a);
    default int foopd(int a){
        return a + 5;
    }
}
