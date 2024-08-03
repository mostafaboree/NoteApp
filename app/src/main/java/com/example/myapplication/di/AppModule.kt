package com.example.myapplication.di

import android.app.Application
import androidx.room.Room
import com.example.myapplication.NoteApp
import com.example.myapplication.data.data_source.Database_Note
import com.example.myapplication.data.repository.ImP_Repository
import com.example.myapplication.domin.Repository
import com.example.myapplication.domin.use_case.AddNote
import com.example.myapplication.domin.use_case.AllNote
import com.example.myapplication.domin.use_case.DeleteNote
import com.example.myapplication.domin.use_case.GetNote
import com.example.myapplication.domin.use_case.Note_UseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDao(app: Application): Database_Note {
        return Room.databaseBuilder(app, Database_Note::class.java, "note_db").build()
    }
    @Provides
    @Singleton
    fun provideNoteRepository(db: Database_Note): Repository {
        return ImP_Repository(db.getDao)
    }
    @Provides
    @Singleton
    fun provideNoteUseCase(repository: Repository): Note_UseCase {
        return Note_UseCase(
            allNote = AllNote(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            getNote = GetNote(repository)
        )
    }

}