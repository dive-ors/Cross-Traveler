package org.crosstraveler.util

import org.springframework.core.io.ClassPathResource
import java.io.BufferedReader


fun String.readJson(): String = ClassPathResource(this).inputStream.bufferedReader().use(BufferedReader::readText)