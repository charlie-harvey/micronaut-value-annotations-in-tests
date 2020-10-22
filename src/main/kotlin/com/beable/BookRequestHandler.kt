package com.beable
import io.micronaut.context.annotation.Value
import io.micronaut.core.annotation.Introspected
import io.micronaut.function.aws.MicronautRequestHandler
import java.util.UUID

@Introspected
class BookRequestHandler : MicronautRequestHandler<Book?, BookSaved?>() {

    @Value("\${thing.a}")
    lateinit var thingA: String
    @Value("\${thing.b}")
    lateinit var thingB: String

    override fun execute(input: Book?): BookSaved? {
        println("Handler thingA: $thingA")
        println("Handler thingB: $thingB")
        return if (input != null) {
            val bookSaved = BookSaved()
            bookSaved.name = input.name
            bookSaved.isbn = UUID.randomUUID().toString()
            return bookSaved
        } else {
            null
        }
    }
}