package com.bryo.marvel.myheroteam.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicBoolean

fun <T> LiveData<T>.toSingleEvent(): LiveData<T> {
    val pending = AtomicBoolean(false)

    val result = object : MediatorLiveData<T>() {
        private val observers = ConcurrentHashMap<Observer<in T>, Observer<in T>>()

        override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
            super.observe(owner, addWrapper(observer))
        }

        override fun observeForever(observer: Observer<in T>) {
            super.observeForever(addWrapper(observer))
        }

        private fun addWrapper(observer: Observer<in T>): Observer<in T> {
            return Observer<T> { t ->
                if (pending.compareAndSet(true, false)) {
                    observer.onChanged(t)
                }
            }.also {
                observers[observer] = it
            }
        }

        override fun removeObserver(observer: Observer<in T>) {
            val wrapper = observers.remove(observer)
            if (wrapper != null) {
                super.removeObserver(wrapper)
            } else {
                observers.filterValues { it == observer }.keys.forEach { observers.remove(it) }
                super.removeObserver(observer)
            }
        }
    }

    result.addSource(this) {
        pending.set(true)
        result.value = it
    }

    return result
}