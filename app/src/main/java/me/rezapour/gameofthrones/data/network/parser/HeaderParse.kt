package me.rezapour.gameofthrones.data.network.parser

import okhttp3.Headers

object HeaderParse {
    fun linkParser(headers: Headers): String {
        val link = headers["Link"]
        val url = link!!.split(";")[0]
        return url.replace("<", "").replace(">", "")
    }
}