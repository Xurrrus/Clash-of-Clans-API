package com.plcoding.clashofclans.domain



class GetWarriorsUseCase (

    private val warriorClient: WarriorClient

)  {

   suspend fun execute(): List<SimpleWarrior>{

        return warriorClient
            .getWarriors()
            .sortedBy { it.nom }

    }


}