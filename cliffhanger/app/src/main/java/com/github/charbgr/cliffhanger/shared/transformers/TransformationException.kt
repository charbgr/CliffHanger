package com.github.charbgr.cliffhanger.shared.transformers

import java.lang.Exception
import kotlin.reflect.KClass

class TransformationException(src: Any, to: KClass<*>) : Exception(
    "$src cannot be transform to " + to
)