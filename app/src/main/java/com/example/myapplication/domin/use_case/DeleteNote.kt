package com.example.myapplication.domin.use_case

import com.example.myapplication.domin.Repository

class DeleteNote(
    val repository: Repository
) {
    operator fun invoke(id:Int)=repository.delete(id)
}