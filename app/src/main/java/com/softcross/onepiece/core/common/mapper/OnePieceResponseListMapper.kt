package com.softcross.onepiece.core.common.mapper

import retrofit2.Response

interface OnePieceResponseListMapper<I,O> : OnePieceMapper<Response<I>, List<O>>