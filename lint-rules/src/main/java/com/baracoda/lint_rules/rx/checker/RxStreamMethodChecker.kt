package com.baracoda.lint_rules.rx.checker

class RxStreamMethodChecker : RxMethodChecker() {
    override val returnClassNames: List<String> = listOf(
        "Observable",
        "Flowable"
    )
    override val suffixes: List<String> = listOf(
        "Stream",
        "Once"
    )

    override val message: String = """
        [Observable] and [Flowable] returning functions should be named with suffix Stream or Once. 
        Example: removeAccountOnce(), getAccountsStream()
    """
}
