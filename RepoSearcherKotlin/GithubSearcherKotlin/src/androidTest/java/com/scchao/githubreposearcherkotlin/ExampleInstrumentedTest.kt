package com.scchao.githubreposearcherkotlin

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.scchao.githubreposearcherkotlin.helper.DataParser
import com.scchao.githubreposearcherkotlin.models.RepoItem

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun normalParserTestCase() {
        val testRaw = "{'items':[{'name': 'test01','full_name': 'test01_full','description': 'description of test01','language': 'Python','private': true}]}"
        val verifyItem = RepoItem()
        verifyItem.name = "test01"
        verifyItem.fullName = "test01_full"
        verifyItem.description = "description of test01"
        verifyItem.language = "Python"
        verifyItem.private = true

        val result = DataParser.parserResult(testRaw)
        assertEquals(1, result.items?.size)

        val resultItem = result.items?.first() ?: RepoItem()

        assertEquals(verifyItem.name, resultItem.name)
        assertEquals(verifyItem.fullName, resultItem.fullName)
        assertEquals(verifyItem.description, resultItem.description)
        assertEquals(verifyItem.language, resultItem.language)
        assertEquals(verifyItem.private, resultItem.private)
    }

    @Test
    fun exceptParserTestCaseWithWrongData() {
        val testRaw = "{'items':[{'name': 'test01','full_name': 'test01_full','description': 'description of test01','language': 'Java','private': true,}],}"
        val result = DataParser.parserResult(testRaw)
        assertEquals(0, result.items?.size)
    }

    @Test
    fun exceptParserTestCaseWithEmptyData() {
        val testRaw = "{'items':[]}"
        val result = DataParser.parserResult(testRaw)
        assertEquals(0, result.items?.size)
    }
}