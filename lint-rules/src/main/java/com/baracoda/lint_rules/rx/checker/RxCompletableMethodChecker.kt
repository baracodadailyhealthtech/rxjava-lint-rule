package com.baracoda.lint_rules.rx.checker

class RxCompletableMethodChecker : RxMethodChecker() {
    override val returnClassNames: List<String> = listOf("Completable")
    override val suffixes: List<String> = listOf("Completable")

    override val message: String = """
        [Completable] returning functions should be named with suffix Completable. 
        Example: removeAccountCompletable()
    """
}
