package com.plcoding.clashofclans.domain



class GetWarriorUseCase (

    private val warriorClient: WarriorClient

)  {

   suspend fun execute(id: String): DetailedWarrior? {

        return warriorClient.getWarrior(id)

    }


}