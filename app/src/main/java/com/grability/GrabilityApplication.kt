package com.grability

import androidx.multidex.MultiDexApplication

class GrabilityApplication: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private lateinit var instance: GrabilityApplication

        fun getInstance(): GrabilityApplication {
            return instance
        }
    }
}