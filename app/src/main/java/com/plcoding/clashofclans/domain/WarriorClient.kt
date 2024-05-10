package com.plcoding.clashofclans.domain

interface WarriorClient {
    suspend fun getWarriors(): List<SimpleWarrior>
    suspend fun getWarrior(id: String): DetailedWarrior?
}