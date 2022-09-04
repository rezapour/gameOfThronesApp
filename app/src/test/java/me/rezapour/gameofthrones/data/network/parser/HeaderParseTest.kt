package me.rezapour.gameofthrones.data.network.parser

import okhttp3.Headers
import okhttp3.Response
import org.junit.Assert
import org.junit.Test


internal class HeaderParseTest {

    @Test
    fun `test header Parser susscess`() {
        val link =
            "<https://www.anapioficeandfire.com/api/houses?page=2&pageSize=10>; rel=\"next\", <https://www.anapioficeandfire.com/api/houses?page=1&pageSize=10>; rel=\"first\", <https://www.anapioficeandfire.com/api/houses?page=45&pageSize=10>; rel=\"last\""

        val header: Headers = Headers.Builder().set("Link", link).build()

        Assert.assertEquals(
            "https://www.anapioficeandfire.com/api/houses?page=2&pageSize=10",
            HeaderParse.linkParser(header)
        )
    }


}