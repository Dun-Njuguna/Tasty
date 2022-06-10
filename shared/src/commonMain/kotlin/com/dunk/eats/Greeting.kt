package com.dunk.eats

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}