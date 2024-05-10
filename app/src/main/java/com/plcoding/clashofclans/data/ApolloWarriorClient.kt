package com.plcoding.clashofclans.data

import com.apollographql.apollo3.ApolloClient
import com.plcoding.WarriorQuery
import com.plcoding.WarriorsQuery
import com.plcoding.clashofclans.domain.DetailedWarrior
import com.plcoding.clashofclans.domain.SimpleWarrior
import com.plcoding.clashofclans.domain.WarriorClient

class ApolloWarriorClient (
    private val apolloClient: ApolloClient
): WarriorClient{

    override suspend fun getWarriors(): List<SimpleWarrior> {

        return apolloClient
            .query(WarriorsQuery())
            .execute()
            .data
            ?.warriors
            ?.map { it.toSimpleWarrior() }
            ?: emptyList()



    }

    override suspend fun getWarrior(id: String): DetailedWarrior? {

        return apolloClient
            .query(WarriorQuery(id))
            .execute()
            .data
            ?.warrior
            ?.toDetailedWarrior()


    }


}// Volem descoplar les dades que tenim de la nostre App.