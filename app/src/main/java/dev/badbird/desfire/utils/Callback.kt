package dev.badbird.desfire.utils

interface Callback<T> {
    fun run(t: T)
}
