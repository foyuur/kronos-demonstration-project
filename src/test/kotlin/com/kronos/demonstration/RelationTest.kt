package com.kronos.demonstration

import com.kotlinorm.Kronos
import com.kotlinorm.KronosBasicWrapper
import com.kotlinorm.orm.database.table
import com.kotlinorm.orm.delete.delete
import com.kotlinorm.orm.insert.insert
import com.kotlinorm.orm.select.select
import com.kotlinorm.orm.update.update
import com.kronos.demonstration.pojo.GroupClass
import com.kronos.demonstration.pojo.School
import com.kronos.demonstration.pojo.Student
import com.kronos.demonstration.util.DataSourceWrapper.dataSource
import kotlin.test.Test

/**
 * @description:
 * @author: lujieyao
 * @date: 2025/5/7
 */
class RelationTest {

    private var wrapper: KronosBasicWrapper = KronosBasicWrapper(dataSource)

    init {
        Kronos.init {
            fieldNamingStrategy = lineHumpNamingStrategy
            tableNamingStrategy = lineHumpNamingStrategy
            dataSource = { wrapper }
        }
    }

    @Test
    fun testDrop() {
        Kronos.dataSource.table.dropTable<School>()
        Kronos.dataSource.table.dropTable<GroupClass>()
        Kronos.dataSource.table.dropTable<Student>()
    }

    @Test
    fun testSync() {
        Kronos.dataSource.table.syncTable<School>()
        Kronos.dataSource.table.syncTable<GroupClass>()
        Kronos.dataSource.table.syncTable<Student>()
    }

    @Test
    fun testCascadeInsert() {

        val school = School(
            id = 1, name = "School", groupClass = listOf(
                GroupClass(
                    id = 11, name = "一年级", students = listOf(
                        Student(name = "张三", studentNo = "2021001"),
                        Student(
                            name = "李四", studentNo = "2021002"
                        )
                    )
                ),
                GroupClass(
                    name = "三年级", students = listOf(
                        Student(
                            name = "孙七", studentNo = "2023001"
                        ), Student(
                            name = "周八", studentNo = "2023002"
                        )
                    )
                ),
                GroupClass(
                    id = 42, name = "二年级", students = listOf(
                        Student(
                            name = "王五", studentNo = "2022001"
                        ), Student(
                            name = "赵六", studentNo = "2022002"
                        )
                    )
                ),
            )
        )

        school.insert().execute()
    }

    @Test
    fun testCascadeUpdate() {
        testCascadeInsert()
        val res = School(name = "School")
            .update()
            .set { it.name = "School2" }
            .execute()
        println(res)
    }

    @Test
    fun testCascadeDelete() {
        testCascadeInsert()
        val result = School(name = "School").select().queryList()
        println(result)
        val res = School(name = "School").delete().execute()
        println(res)
    }

    @Test
    fun testReverseCascadeDelete() {
        testCascadeInsert()
        val result = School(name = "School").select().queryList()
        println(result)
        val student = School(name = "School").select().queryOne()
        val res = student.delete().execute()
        println(res)
    }

    @Test
    fun testSelect() {
        testCascadeInsert()
        val result = School(name = "School").select().queryList()
        println(result)
    }

    @Test
    fun testRevertSelect() {

        val student = Student(name = "张三", studentNo = "2021001")
        val groupClass = GroupClass(name = "一年级", students = listOf(student))
        val school = School(name = "School", groupClass = listOf(groupClass))

        school.insert().execute()

        val groupClassQ = groupClass.select().queryOne()
        val schoolQ = school.select().queryOne()

        school.delete().execute()

        print(groupClassQ)
    }

}