package com.softcross.onepiece.core.common.mapper

import retrofit2.Response

interface OnePieceResponseMapper<I,O> : OnePieceMapper<Response<I>, List<O>>