package com.softcross.onepiece.core.common.mapper

import retrofit2.Response

interface OnePieceResponseItemMapper <I,O> : OnePieceMapper<Response<I>, O>