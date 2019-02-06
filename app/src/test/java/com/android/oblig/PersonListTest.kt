package com.android.oblig

import android.content.Context
import android.graphics.Bitmap
import com.android.oblig.activities.PersonAdapter
import com.android.oblig.activities.PersonAdapterHelpers
import com.android.oblig.modules.Person
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

//https://proandroiddev.com/on-kotlin-a-unit-test-conversion-guide-71e0597bb45d
class PersonListTest {

    val byteArray = ByteArray(1)
    val person1 = Person(0,"person1",byteArray)
    val person2 = Person(1,"person2",byteArray)


    var testList:MutableList<Person> = arrayListOf()

    @Before
    fun testBefore(){
        testList.add(person1)
        testList.add(person2)
    }
    @Test
    fun testDelete(){
        val paHelpers = PersonAdapterHelpers(testList)
        assertTrue(testList.size==2)
        assertTrue(testList.contains(person1))
        assertTrue(testList.contains(person2))

        paHelpers.deletePersonFromList(person1)
        assertTrue(testList.size==1)
        assertFalse(testList.contains(person1))
        assertTrue(testList.contains(person2))

        paHelpers.deletePersonFromList(person2)
        assertTrue(testList.size==0)
        assertFalse(testList.contains(person1))
        assertFalse(testList.contains(person2))

    }



}

