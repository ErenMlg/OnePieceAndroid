package com.softcross.onepiece.core.common.mapper

interface OnePieceMapper<I, O> {
    fun map(input: I?): O
}