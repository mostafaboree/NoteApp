package com.example.myapplication.domin.use_case

import com.example.myapplication.domin.Repository
import com.example.myapplication.domin.model.InvalidNoteExption
import com.example.myapplication.domin.model.Note
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class AddNote(val repository: Repository) {

@Throws(InvalidNoteExption::class)
    operator fun invoke(note: Note): Completable {
        if (note.title. isBlank() ){
            throw InvalidNoteExption("note title cannot  be empty ")}
    if (note.body.isBlank()) {
           throw InvalidNoteExption("note body cannot be empty ?")
       }
       return repository.add(note)
    }

}