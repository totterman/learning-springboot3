import React from 'react'
import ListOfVideos from './ListOfVideos'
import NewVideo from "./NewVideo"
export function App() {
    return (
        <><h1>Greetings Learning Spring Boot 3.0 fans!</h1><p>
            In this chapter, we are learning how to make
            a web app using Spring Boot 3.0
        </p>
        
        <div>
            <ListOfVideos />
            <NewVideo />
        </div></>
    )
}