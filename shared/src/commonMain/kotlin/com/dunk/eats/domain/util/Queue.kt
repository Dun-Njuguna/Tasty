package com.dunk.eats.domain.util


class Queue<T> (list:MutableList<T>){

    var items: MutableList<T> = list

    fun count():Int = items.count()

    fun isEmpty():Boolean = items.isEmpty()

    override  fun toString() = items.toString()

    fun add(element: T){
        items.add(element)
    }

    fun addAll(queue: Queue<T>){
        this.items.addAll(queue.items)
    }

    @Throws(Exception::class)
    fun remove(): T {
        if (this.isEmpty()){
            throw Exception("Queue is empty")
        } else {
            return items.removeAt(0)
        }
    }

    fun remove(item: T): Boolean {
        return items.remove(item)
    }

    fun clear(){
        items.removeAll { true }
    }

    @Throws(Exception::class)
    fun element(): T {
        if(this.isEmpty()){
            throw Exception("Queue is empty")
        }
        return items[0]
    }

    fun offer(element: T): Boolean{
        try{
            items.add(element)
        }catch (e: Exception){
            return false
        }
        return true
    }

    fun poll(): T?{
        if(this.isEmpty()) return null
        return items.removeAt(0)
    }

    fun peek():T?{
        if(this.isEmpty()) return null
        return items[0]
    }

}
