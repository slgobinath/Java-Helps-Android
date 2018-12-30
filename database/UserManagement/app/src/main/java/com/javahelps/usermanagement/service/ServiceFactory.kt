package com.javahelps.usermanagement.service

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class ServiceFactory private constructor(private val retrofit: Retrofit) {

    companion object {
        @Volatile
        private var INSTANCE: ServiceFactory? = null

        /**
         * Returns a singleton ServiceFactory.
         */
        fun getInstance(baseUrl: String, username: String, password: String): ServiceFactory =
            INSTANCE ?: synchronized(this) {


                INSTANCE ?: build(baseUrl, username, password).also { INSTANCE = it }
            }

        /**
         * Build a ServiceFactory object.
         */
        private fun build(baseUrl: String, username: String, password: String): ServiceFactory {
            // Required only if using Basic authentication
            val clientBuilder = OkHttpClient.Builder()
            val headerAuthorizationInterceptor = Interceptor { chain ->
                var request = chain.request()
                val headers = request.headers()
                    .newBuilder()
                    .add("Authorization", Credentials.basic(username, password))
                    .build()
                request = request.newBuilder().headers(headers).build()
                chain.proceed(request)
            }
            val okHttpClient = clientBuilder.addInterceptor(headerAuthorizationInterceptor)
                .build()

            // Create a Retrofit object `.client(okHttpClient)` is required only if using Basic authentication
            val retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build()
            return ServiceFactory(retrofit)
        }

    }

    /**
     * Return the given service object.
     */
    fun <T> build(service: Class<T>): T {
        return retrofit.create(service)
    }
}