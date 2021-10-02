/*
 * Copyright (C) 2021 Baracoda Daily Healthtech
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and limitations
 * under the License.
 */

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
