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

package com.baracoda.lint_rules.rx.checker

abstract class RxMethodChecker {
    /**
     * List of methods return class names to be checked
     */
    protected abstract val returnClassNames: List<String>
    /**
     * List of correct suffixes for given [returnClassNames]
     */
    protected abstract val suffixes: List<String>
    /**
     * Specific message about why the current method name is incorrect
     */
    abstract val message: String

    fun isMethodReturningRxType(returnClassName: String): Boolean =
        returnClassNames.contains(returnClassName)

    fun checkMethodNameSuffix(methodName: String): Boolean =
        suffixes.any { methodName.endsWith(it) }
}
