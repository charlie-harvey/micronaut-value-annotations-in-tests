package com.beable

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.micronaut.context.annotation.Value
import io.micronaut.test.extensions.kotest.annotation.MicronautTest

@MicronautTest()
class BookRequestHandlerTest : AnnotationSpec() {

    @Value("\${thing.a}")
    lateinit var thingA: String
    @Value("\${thing.b}")
    lateinit var thingB: String

    @Test
    fun handlerTest() {
        println("This is thingA: $thingA")
        println("This is thingB: $thingB")
        val bookRequestHandler = BookRequestHandler()
        val book = Book()
        book.name = "Building Microservices"
        val bookSaved = bookRequestHandler.execute(book)
        bookSaved.shouldNotBeNull()
        bookSaved.name.shouldBe(book.name)
        bookSaved.isbn.shouldNotBeNull()
        bookRequestHandler.applicationContext.close()
    }
}
