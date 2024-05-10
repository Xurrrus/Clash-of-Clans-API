package com.plcoding.clashofclans

import com.apollographql.apollo3.ApolloClient
import com.plcoding.clashofclans.data.ApolloWarriorClient
import com.plcoding.clashofclans.domain.GetWarriorUseCase
import com.plcoding.clashofclans.domain.GetWarriorsUseCase
import com.plcoding.clashofclans.domain.WarriorClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    @Provides
    @Singleton
    fun provideApolloCLient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("http://192.168.1.154:80/graphql") //canviar per IP pròpia
            .build()
    }

    @Provides
    @Singleton
    fun provideWarriorClient(apolloClient: ApolloClient): WarriorClient {
        return ApolloWarriorClient(apolloClient)
    }

    @Provides
    @Singleton
    fun provideGetWarriorsUseCase(warriorClient: WarriorClient): GetWarriorsUseCase {
        return GetWarriorsUseCase(warriorClient)
    }

    @Provides
    @Singleton
    fun provideGetWarriorUseCase(warriorClient: WarriorClient): GetWarriorUseCase {
        return GetWarriorUseCase(warriorClient)
    }

}