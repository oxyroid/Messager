package com.oxy.messager.ktx

import org.http4k.core.Request
import org.http4k.format.KotlinxSerialization

inline fun <reified T: Any> Request.format(): T {
    val string = this.bodyString()
    return KotlinxSerialization.asA<T>(string)
}