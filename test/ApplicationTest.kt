package com.cobs.dpc.test

import io.ktor.http.*
import kotlin.test.*
import io.ktor.server.testing.*

class ApplicationTest {
    @Test
    fun testRoot() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/apk").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("application/vnd.android.package-archive", response.contentType().toString())
                assertEquals(ContentDisposition.Attachment.withParameter(ContentDisposition.Parameters.FileName,
                    "test-dpc.apk").toString(), response.headers[HttpHeaders.ContentDisposition])
            }
        }
    }
}
