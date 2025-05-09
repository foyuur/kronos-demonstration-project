package com.kronos.demonstration.pojo

import com.kotlinorm.annotations.Column
import com.kotlinorm.annotations.CreateTime
import com.kotlinorm.annotations.DateTimeFormat
import com.kotlinorm.annotations.LogicDelete
import com.kotlinorm.annotations.Table
import com.kotlinorm.annotations.UpdateTime
import com.kotlinorm.annotations.Version
import com.kotlinorm.interfaces.KPojo
import java.time.LocalDateTime

@Table("tb_address")
data class Address(
    val id: Int? = null,
    val userId: Int? = null,
    val street: String? = null,
    val city: String? = null,
    val state: String? = null,
    @Column("zip") val zipCode: String? = null,
    val country: String? = null,
    @UpdateTime
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val updateTime: String? = null,
    @CreateTime
    val createTime: LocalDateTime? = null,
    @Version
    val version: Int? = null,
    @LogicDelete
    val deleted: Boolean? = null,
) : KPojo