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

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import java.util.Calendar
import java.util.Date
import java.util.concurrent.TimeUnit

class ExampleService {
    fun getCurrentHourOnce(): Single<Int> =
        Single.just(Calendar.getInstance().get(Calendar.HOUR_OF_DAY))

    fun getCurrentTimeStream(): Flowable<Long> =
        Flowable.timer(1, TimeUnit.SECONDS)
            .map { Calendar.getInstance().time.time }

    fun getCurrentDateStream(): Observable<Date> =
        Observable.timer(1, TimeUnit.MINUTES)
            .map { Calendar.getInstance().time }

    fun resetServiceCompletable(): Completable = Completable.complete()

    fun anythingMaybe(): Maybe<Boolean> = Maybe.just(true)
}

internal class ExampleInternalService {
    internal fun getCurrentHourOnce(): Single<Int> =
        Single.just(Calendar.getInstance().get(Calendar.HOUR_OF_DAY))

    internal fun getCurrentTimeStream(): Flowable<Long> =
        Flowable.timer(1, TimeUnit.SECONDS)
            .map { Calendar.getInstance().time.time }

    internal fun getCurrentDateStream(): Observable<Date> =
        Observable.timer(1, TimeUnit.MINUTES)
            .map { Calendar.getInstance().time }

    internal fun resetServiceCompletable(): Completable = Completable.complete()

    internal fun anythingMaybe(): Maybe<Boolean> = Maybe.just(true)
}
