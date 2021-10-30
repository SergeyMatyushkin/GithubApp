package com.example.githubapp.data.domain

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

object EventBus {
    open class Event
    private val bus = BehaviorSubject.create<Event>()

    fun post(event: Event){
        bus.onNext(event)
    }

    fun get(): Observable<Event> = bus
}