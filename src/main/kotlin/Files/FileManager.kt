package Files

interface FileManager<T> {
    fun get(): T
    fun save(data: T) 
}