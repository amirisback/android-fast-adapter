package com.qomunal.opensource.androidresearch.model

import java.util.UUID

/**
 * Created by faisalamircs on 13/01/2024
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 */


data class MainModel(
    var id: String = "",
    var data: String = "",
    var uuid: String = UUID.randomUUID().toString(),
)