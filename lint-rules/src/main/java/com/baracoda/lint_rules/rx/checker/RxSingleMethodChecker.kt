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


class RxSingleMethodChecker : RxMethodChecker() {
    override val returnClassNames: List<String> = listOf("Single")
    override val suffixes: List<String> = listOf("Once")

    override val message: String = """
        [Single] returning functions should be named with suffix Once. 
        Example: removeAccountOnce()
    """
}
