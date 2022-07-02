package com.ineedyourcode.core

import com.ineedyourcode.core.data.datasource.remote.SearchingDtoMapper
import com.ineedyourcode.core.data.datasource.remote.dto.MeaningDto
import com.ineedyourcode.core.data.datasource.remote.dto.SearchingResultItemDto
import com.ineedyourcode.core.data.datasource.remote.dto.TranslationDto
import com.ineedyourcode.core.ui.uils.ClassForTest
import com.ineedyourcode.core.ui.uils.ErrorMapper
import com.ineedyourcode.domain.entity.ResponseCodes
import org.junit.Assert.*
import org.junit.Test

class CoreUnitTest {
    @Test
    fun errorMapper_InvalidRequestCode() {
        val errorMapper = ErrorMapper.StringResource(ResponseCodes.INVALID_REQUEST)
        assertEquals(R.string.error_300_message, errorMapper.getMessage())
    }

    @Test
    fun errorMapper_DirectString_True() {
        val message = "message"
        val errorMapper = ErrorMapper.DirectString(message)
        assertTrue(errorMapper.getMessage<String>() == message)
    }

    @Test
    fun searchingDtoMapper_MeaningNoteIsNotNull() {
        val translation = TranslationDto(null, "text")
        val meaningDto = MeaningDto(
            "1",
            "url",
            "code",
            "url",
            "url",
            "foo",
            translation = translation)
        val resultDto = SearchingResultItemDto(
            "1",
            meanings = listOf(meaningDto),
            "text")

        val dtoMapper = SearchingDtoMapper()
        val result = dtoMapper.convertResultDtoToEntity(resultDto)
        assertNotNull(result.wordMeanings.first().note)
    }

    @Test
    fun classForTest_ReturnNull() {
        assertNull(ClassForTest.returnNull())
    }

    @Test
    fun classForTest_EqualsArray() {
        assertArrayEquals(arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9), ClassForTest.generateArray())
    }

    @Test
    fun classForTest_SameObjects() {
        assertSame(ClassForTest.testObject, ClassForTest.sameObject())
    }

    @Test
    fun classForTest_NotEqualsObject() {
        assertNotEquals(Any(), ClassForTest.testObject)
    }
}