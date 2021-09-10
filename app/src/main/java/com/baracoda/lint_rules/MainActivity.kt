package com.baracoda.lint_rules

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service = ExampleService()

        disposables.add(
            service.getCurrentHourOnce()
                .subscribeOn(Schedulers.io())
                .subscribe({}, { Log.e("MainActivity", "Error when getting current hour") })
        )
        disposables.add(
            service.getCurrentTimeStream()
                .subscribeOn(Schedulers.io())
                .subscribe({}, { Log.e("MainActivity", "Error when getting current time") })
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}
