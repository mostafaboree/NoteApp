package com.example.myapplication.domin.use_case

import com.example.myapplication.domin.Repository

class AllNote(
    val repository: Repository
) {
    operator fun invoke()=repository.getALL()

}