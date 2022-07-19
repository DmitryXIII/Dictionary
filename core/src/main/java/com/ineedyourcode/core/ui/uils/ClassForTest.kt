package com.ineedyourcode.core.ui.uils

class ClassForTest {
    companion object {
        val testObject = Any()

        fun returnNull() : Any? {
            return null
        }

        fun generateArray() : Array<Int> {
            return arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        }

        fun sameObject(): Any {
            return testObject
        }
    }
}