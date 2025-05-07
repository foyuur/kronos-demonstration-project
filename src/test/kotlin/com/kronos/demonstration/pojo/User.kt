package com.kronos.demonstration.pojo

import com.kotlinorm.annotations.CreateTime
import com.kotlinorm.annotations.LogicDelete
import com.kotlinorm.annotations.PrimaryKey
import com.kotlinorm.annotations.Table
import com.kotlinorm.annotations.UpdateTime
import com.kotlinorm.interfaces.KPojo
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * @description:
 * @author: lujieyao
 * @date: 2025/5/7
 */
@Table("tb_user")
data class User(
    @PrimaryKey(identity = true)
    var id: Int? = null,
    var uid: Long? = null,
    var name: String? = null,
    var age: Int? = null,
    var sex: Boolean? = null,
    var height: Float? = null,
    var weight: Float? = null,
    var score: Double? = null,
    var salary: BigDecimal? = null,
    var birthday: LocalDate? = null,
    var email: String? = null,
    var address: String? = null,
    var version: Int? = null,
    @LogicDelete var deleted: Boolean? = null,
    @CreateTime var createTime: LocalDateTime? = null,
    @UpdateTime var updateTime: LocalDateTime? = null,
): KPojo