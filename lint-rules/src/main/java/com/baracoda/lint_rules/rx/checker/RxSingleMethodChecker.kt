package com.baracoda.lint_rules.rx.checker


class RxSingleMethodChecker : RxMethodChecker() {
    override val returnClassNames: List<String> = listOf("Single")
    override val suffixes: List<String> = listOf("Once")

    override val message: String = """
        [Single] returning functions should be named with suffix Once. 
        Example: removeAccountOnce()
    """


}
