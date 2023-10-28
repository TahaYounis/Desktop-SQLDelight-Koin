package data

import SQLDelightKoin.db.PersonEntity
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext


interface PersonDataSource {

    suspend fun getPersonById(id: Long): PersonEntity?

    fun getAllPersons(context: CoroutineContext): Flow<List<PersonEntity>>

    suspend fun deletePersonBy(id: Long)

    suspend fun insertPerson(firstName: String, lastName:String, id: Long? = null)

}