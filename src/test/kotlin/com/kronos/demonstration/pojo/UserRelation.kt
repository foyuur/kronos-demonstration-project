package com.kronos.demonstration.pojo

import com.kotlinorm.annotations.PrimaryKey
import com.kotlinorm.interfaces.KPojo

data class UserRelation(
    @PrimaryKey() var id: Int? = null,
    var username: String? = null,
    var gender: Boolean? = null,
    var id2: Int? = null
) : KPojo