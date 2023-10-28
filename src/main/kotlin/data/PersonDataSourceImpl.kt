package data

import SQLDelightKoin.db.PersonEntity
import SQLDelight_Koin.MyDatabase
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext


class PersonDataSourceImpl(
    db: MyDatabase
):PersonDataSource  {

    private val queries = db.personEntityQueries

    override suspend fun getPersonById(id: Long): PersonEntity? {
        return withContext(Dispatchers.IO) {
            queries.getPersonById(id).executeAsOneOrNull()
        }
    }

    override fun getAllPersons(context: CoroutineContext): Flow<List<PersonEntity>> {
        return queries.getAllPersons().asFlow().mapToList(context)
    }

    override suspend fun deletePersonBy(id: Long) {
        withContext(Dispatchers.IO) {
            queries.deletePersonById(id)
        }
    }

    override suspend fun insertPerson(firstName: String, lastName: String, id: Long?) {
        withContext(Dispatchers.IO) {
            queries.insertPerson(id, firstName, lastName)
        }
    }
}