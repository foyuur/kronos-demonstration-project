package com.kronos.demonstration.pojo

import com.kotlinorm.annotations.PrimaryKey
import com.kotlinorm.annotations.Cascade
import com.kotlinorm.annotations.CreateTime
import com.kotlinorm.annotations.LogicDelete
import com.kotlinorm.annotations.UpdateTime
import com.kotlinorm.interfaces.KPojo
import com.kotlinorm.enums.CascadeDeleteAction
import java.time.LocalDateTime

data class GroupClass(
    @PrimaryKey(identity = true)
    var id: Int? = null,
    val name: String? = null, // 班级名
    val groupNo: String? = null,
    var schoolName: String? = null,
    @Cascade(["schoolName"], ["name"], CascadeDeleteAction.SET_NULL)
    var school: School? = null,
    var students: List<Student>? = null,
    @LogicDelete var deleted: Boolean? = null,
    @CreateTime var createTime: LocalDateTime? = null,
    @UpdateTime var updateTime: LocalDateTime? = null,
) : KPojo