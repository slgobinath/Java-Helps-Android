package com.javahelps.listsdcardfiles

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.reactivestreams.Publisher
import org.reactivestreams.Subscriber
import java.io.File


class MainActivity : AppCompatActivity() {

    private val requestCode = 100
    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun list(view: View) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), requestCode)
        } else {
            listExternalStorage()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == this.requestCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                listExternalStorage()
            } else {
                Toast.makeText(this, "Until you grant the permission, I cannot list the files", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        this.disposable?.dispose()
    }

    private fun listExternalStorage() {
        val state = Environment.getExternalStorageState()

        if (Environment.MEDIA_MOUNTED == state || Environment.MEDIA_MOUNTED_READ_ONLY == state) {

            this.disposable = Observable.fromPublisher(FileLister(Environment.getExternalStorageDirectory()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    txtFiles.append(it + "\n")
                }, {
                    Log.e("MainActivity", "Error in listing files from the SD card", it)
                }, {
                    Toast.makeText(this, "Successfully listed all the files!", Toast.LENGTH_SHORT)
                        .show()
                    this.disposable?.dispose()
                    this.disposable = null
                })
        }
    }

    private class FileLister(val directory: File) : Publisher<String> {

        private lateinit var subscriber: Subscriber<in String>

        override fun subscribe(s: Subscriber<in String>?) {
            if (s == null) {
                return
            }
            this.subscriber = s
            this.listFiles(this.directory)
            this.subscriber.onComplete()
        }

        /**
         * Recursively list files from a given directory.
         */
        private fun listFiles(directory: File) {
            val files = directory.listFiles()
            if (files != null) {
                for (file in files) {
                    if (file != null) {
                        if (file.isDirectory) {
                            listFiles(file)
                        } else {
                            subscriber.onNext(file.absolutePath)
                        }
                    }
                }
            }
        }

    }


}
