package com.gcaguilar.secrets.utils

object Keys {

    init {
        System.loadLibrary("native-lib")
    }

    external fun clientId(): String
    external fun clientSecret(): String
}