import express from 'express'
import cors from 'cors'
import { graphqlHTTP } from 'express-graphql'
import { makeExecutableSchema } from '@graphql-tools/schema'

const app = express()
const port = 80

// In-memory data store
const data = {
  warriors:[
    { id: '1', nom: 'Barbaro',tipusAtac: 'cos a cos',raresa: 'elixir',espai: '1',velocitat: '16',abast: '0.4 caselles',imatge: 'https://static.wikia.nocookie.net/clash-of-clans/images/9/90/Bárbaro_info.png/revision/latest?cb=20210819002032&path-prefix=es' }, //Bárbaro
    { id: '2', nom: 'Arquera',tipusAtac: 'distancia',raresa: 'elixir',espai: '1',velocitat: '24',abast: '3.5 caselles',imatge:'https://static.wikia.nocookie.net/clashofclans/images/2/24/Archer_Render2.png/revision/latest?cb=20150427011816'},
    { id: '3', nom: 'Montapuerco',tipusAtac: 'cos a cos',raresa: 'elixir oscuro',espai: '5',velocitat: '24',abast: '0.6 caselles',imatge:'https://static.wikia.nocookie.net/clashofclans/images/5/54/Hog_Rider_info.png/revision/latest?cb=20170927231149'},
    { id: '4', nom: 'Golem',tipusAtac: 'cos a cos',raresa: 'elixir oscuro',espai: '30',velocitat: '12',abast: '1.5 caselles',imatge:'https://static.wikia.nocookie.net/clash-of-clans/images/7/78/Gólem_info.png/revision/latest?cb=20210819011512&path-prefix=es'},
    { id: '5', nom: 'Rey Barbaro',tipusAtac: 'cos a cos',raresa: 'Heroe',espai: '0',velocitat: '16',abast: '1.0 caselles',imatge:'https://static.wikia.nocookie.net/clashofclans/images/e/ec/Barbarian_King_info.png/revision/latest?cb=20170927231521'},
    { id: '6', nom: 'Reina Arquera',tipusAtac: 'distancia',raresa: 'Heroe',espai: '0',velocitat: '24',abast: '5.0 caselles',imatge:'https://static.wikia.nocookie.net/clashofclans/images/4/4b/Archer_Queen_info.png/revision/latest?cb=20170927231550'},
    { id: '7', nom: 'Globo Bombástico',tipusAtac: 'cos a cos',raresa: 'elixir',espai: '5',velocitat: '10',abast: '0.5 caselles',imatge:'https://static.wikia.nocookie.net/clash-of-clans/images/c/c8/Globo_info.png/revision/latest?cb=20210731090913&path-prefix=es'},
    { id: '8', nom: 'Drac',tipusAtac: 'cos a cos',raresa: 'elixir',espai: '20',velocitat: '16',abast: '3 caselles',imatge:'https://static.wikia.nocookie.net/clashofclans/images/2/28/Dragon_info.png/revision/latest?cb=20170927230914'},

    ],
}

// Schema
const typeDefs = `
type Warrior {
  id: ID!
  nom: String!
  tipusAtac: String!
  raresa: String!
  espai: Int!
  velocitat: Int!
  abast: String!
  imatge: String!
}

type Query {
  warriors: [Warrior]
  warrior(id: ID!): Warrior
}
`

// Resolver for warriors
const resolvers = {
  Query: {
    warriors: () => data.warriors, // Resolució de la consulta "warriors" per obtenir tots els guerrers
    warrior: (parent, args) => data.warriors.find(warrior => warrior.id === args.id), // Resolució de la consulta "warrior" per obtenir un guerrer per ID
  },
};

const executableSchema = makeExecutableSchema({
  typeDefs,
  resolvers,
})

app.use(cors())
app.use(express.json())
app.use(express.urlencoded({ extended: true }))

// Entrypoint
app.use(
  '/graphql',
  graphqlHTTP({
    schema: executableSchema,
    context: data,
    graphiql: true,
  })
)

app.listen(port, () => {
  console.log(`Running a server at http://192.168.1.154:${port}`)
})