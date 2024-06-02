package mocks

import Files.FileManager

class MockFileManager<T> : FileManager<T> {
    var data: T
    var onSave: (data: T) -> Unit
    
    constructor(data: T, onSave: (data: T) -> Unit) {
        this.data = data
        this.onSave = onSave
    }
    
    override fun get(): T {
        return data
    }

    override fun save(data: T) {
        onSave(data)
    }

    fun updateData(data: T) {
        this.data = data
    }
    
    fun updateOnSave(onSave: (data: T) -> Unit) {
       this.onSave = onSave
    }
}