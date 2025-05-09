package com.kronos.demonstration

import com.kotlinorm.Kronos
import com.kotlinorm.KronosBasicWrapper
import com.kotlinorm.orm.database.table
import com.kotlinorm.orm.insert.insert
import com.kotlinorm.orm.join.join
import com.kotlinorm.orm.select.select
import com.kotlinorm.orm.update.update
import com.kronos.demonstration.pojo.Address
import com.kronos.demonstration.pojo.Movie
import com.kronos.demonstration.pojo.User
import com.kronos.demonstration.pojo.UserRelation
import com.kronos.demonstration.util.DataSourceWrapper.dataSource
import kotlin.test.Test

/**
 * @description:
 * @author: lujieyao
 * @date: 2025/5/7
 */
class BasicTest {

    private var wrapper: KronosBasicWrapper = KronosBasicWrapper(dataSource)

    init {
        Kronos.init {
            fieldNamingStrategy = lineHumpNamingStrategy
            tableNamingStrategy = lineHumpNamingStrategy
            dataSource = { wrapper }
        }
    }

    @Test
    fun testSync() {
        Kronos.dataSource.table.syncTable<User>()
    }

    @Test
    fun testInsert() {
        val user = User(
            uid = 1L,
            name = "Alice",
            age = 18
        )
        user.insert().execute()
    }

    @Test
    fun testSelect() {
        val user = User().select().queryOne()
        println()
    }

    @Test
    fun testUpdate() {
        val user = User(
            id = 1,
            uid = 1L,
            name = "Alice",
            age = 19
        )
        user.update().by { it.id }.execute()
    }

    @Test
    fun testJoin() {
        val result = User(1).join(
            UserRelation(1, "123", 1, 1), Movie(1), Address(1)
        ) { user, relation, movie, address ->
            leftJoin(relation) { user.id == relation.id2 && user.sex == relation.gender }
            rightJoin(movie) { movie.year == user.id }
            fullJoin(address) { address.userId == user.id }
            select {
                user.id + relation.gender + movie.id
            }
            where { user.id == 1 }
            orderBy { user.id.desc() }
        }.build()
    }

}