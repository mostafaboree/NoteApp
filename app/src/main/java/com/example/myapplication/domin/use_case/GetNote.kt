package com.example.myapplication.domin.use_case

import com.example.myapplication.domin.Repository

class GetNote(
    val repository: Repository
) {
    operator fun invoke(id:Int)=repository.getById(id)
}