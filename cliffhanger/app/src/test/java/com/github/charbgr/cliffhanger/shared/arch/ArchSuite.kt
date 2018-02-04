package com.github.charbgr.cliffhanger.shared.arch

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    BasePresenterTest::class,
    MviPresenterTest::class,
    RxPresenterTest::class,
    UseCaseTest::class
)
class ArchSuite
