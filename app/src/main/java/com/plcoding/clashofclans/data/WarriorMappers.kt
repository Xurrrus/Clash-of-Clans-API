package com.plcoding.clashofclans.data


import com.plcoding.WarriorQuery
import com.plcoding.WarriorsQuery
import com.plcoding.clashofclans.domain.DetailedWarrior
import com.plcoding.clashofclans.domain.SimpleWarrior

fun WarriorQuery.Warrior.toDetailedWarrior(): DetailedWarrior{
    return DetailedWarrior(
        id = id,
        nom = nom,
        raresa = raresa,
        espai = espai,
        velocitat = velocitat,
        abast = abast,
        tipusAtac = tipusAtac,
        imatge = imatge
    )
}

fun WarriorsQuery.Warrior.toSimpleWarrior(): SimpleWarrior{
    return SimpleWarrior(
        id = id,
        nom = nom,
        raresa = raresa,
        imatge = imatge



    )
}