package com.android.oblig

import com.android.oblig.activities.PersonAdapterHelpers
import com.android.oblig.modules.Person
import org.junit.Before
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

//https://proandroiddev.com/on-kotlin-a-unit-test-conversion-guide-71e0597bb45d
class PersonListTest {

    val byteArray = ByteArray(1)
    val person1 = Person(0,"person1",byteArray)
    val person2 = Person(1,"person2",byteArray)


    var testList:MutableList<Int> = arrayListOf()

    @Before
    fun testBefore(){
        testList.add(person1.id)
        testList.add(person2.id)
    }
    @Test
    fun testDelete(){
        val paHelpers = PersonAdapterHelpers(testList)
        assertTrue(testList.size==2)
        assertTrue(testList.contains(person1.id))
        assertTrue(testList.contains(person2.id))

        paHelpers.deletePersonFromList(person1.id)
        assertTrue(testList.size==1)
        assertFalse(testList.contains(person1.id))
        assertTrue(testList.contains(person2.id))

        paHelpers.deletePersonFromList(person2.id)
        assertTrue(testList.size==0)
        assertFalse(testList.contains(person1.id))
        assertFalse(testList.contains(person2.id))

    }



}

