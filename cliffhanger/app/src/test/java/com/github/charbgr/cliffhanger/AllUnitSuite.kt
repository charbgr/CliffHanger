package com.github.charbgr.cliffhanger

import com.github.charbgr.cliffhanger.features.detail.arch.MovieDetailSuite
import com.github.charbgr.cliffhanger.shared.extensions.ExtensionsSuite
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    MovieDetailSuite::class,
    ExtensionsSuite::class
)
class AllUnitSuite
