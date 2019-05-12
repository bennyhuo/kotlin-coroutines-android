package com.bennyhuo.kotlin.coroutines.android.mainscope.exception

class UnsupportedTypeException(type: Class<*>, vararg supportedTypes: String)
    : Exception("Unsupported type: $type. ${supportedTypes.joinToString()} ${if(supportedTypes.size == 1) "is" else "are" } needed.")

class UnsupportedVersionException(library: String, version: String): Exception("Unsupported version: $version of $library")