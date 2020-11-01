package com.bun133.hisya.util

class TripleSet<T, S, X>(var t: T, var s: S, var x: X) {
    constructor(t: T, doubleSet: DoubleSet<S, X>) : this(t, doubleSet.t, doubleSet.s)
}