package di

import SQLDelight_Koin.MyDatabase
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import data.PersonDataSource
import data.PersonDataSourceImpl
import data.PersonListViewModel
import org.koin.dsl.module

val databaseModule = module {

    fun provideSqlDriver(): SqlDriver {
        return JdbcSqliteDriver(url = "jdbc:sqlite:MyDatabase.db", schema = MyDatabase.Schema)

    }
    fun providePersonDataSource(driver: SqlDriver): PersonDataSource{
        return PersonDataSourceImpl(MyDatabase(driver))
    }

    single {
        provideSqlDriver()
    }

    single {
        providePersonDataSource(get())
    }

    single {
        MyDatabase(get())
    }

    single {
        PersonListViewModel(get())
    }
}