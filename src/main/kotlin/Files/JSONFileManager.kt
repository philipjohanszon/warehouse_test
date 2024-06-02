package Files

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import java.io.File

class JSONFileManager<T> : FileManager<T> {
    val filename: String
    val serializer: KSerializer<T>

    constructor(filename: String, serializer: KSerializer<T>) {
        this.filename = filename
        this.serializer = serializer
    }

    override fun get(): T {
        val contents = File(filename).readText(charset = Charsets.UTF_8)
        return Json.decodeFromString(serializer, contents)
    }

    override fun save(data: T) {
        val contents = Json.encodeToString(serializer, data)
        File(filename).writeText(contents, Charsets.UTF_8)
    }
}