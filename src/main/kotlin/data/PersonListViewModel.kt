package data

import SQLDelightKoin.db.PersonEntity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonListViewModel(
    private val personDataSource: PersonDataSource
) {
    private var coroutineScope = CoroutineScope(Dispatchers.Default)

    val persons = personDataSource.getAllPersons(CoroutineScope(Dispatchers.Default).coroutineContext)

    var personDetails by mutableStateOf<PersonEntity?>(null)
        private set

    var firstNameText by mutableStateOf("")
        private set
    var lastNameText by mutableStateOf("")
        private set

    fun onInsertPersonClick() {
        if (firstNameText.isBlank() || lastNameText.isBlank()) {
            return
        }
        coroutineScope.launch {
            personDataSource.insertPerson(firstNameText, lastNameText)
            firstNameText = ""
            lastNameText = ""
        }
    }

    fun onDeleteClick(id: Long) {
        coroutineScope.launch {
            personDataSource.deletePersonBy(id)
        }
    }

    fun getPersonById(id: Long) {
        coroutineScope.launch {
            personDetails = personDataSource.getPersonById(id)
        }
    }

    fun onFirstNameChange(value: String) {
        firstNameText = value
    }

    fun onLastNameChange(value: String) {
        lastNameText = value
    }

    fun onPersonDetailsDialogDismiss() {
        personDetails = null
    }
}