package com.gcaguilar.authentication.utils

object Keys {

    init {
        System.loadLibrary("native-lib")
    }

    external fun clientId(): String
    external fun appUrl(): String
}