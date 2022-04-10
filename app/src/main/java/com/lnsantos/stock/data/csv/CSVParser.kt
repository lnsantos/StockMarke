package com.lnsantos.stock.data.csv

import java.io.InputStream

interface CSVParser<T> {
    suspend fun parser(inputStream: InputStream) : List<T>
}