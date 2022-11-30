package com.leapmotor.utils.ui

import android.util.Log
import com.blankj.utilcode.util.GsonUtils
import com.leapmotor.lputils.utils.ToastUtils


/**
 * good programmer.
 *
 * @date : 2022-10-17 19:24
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
class Test {

    fun vars(vararg vararg: Int) {
        for (i in vararg) {
            System.out.println(i)
        }

        for (i in vararg.indices) {
            System.out.println(vararg[i])
            System.out.println(i)
        }

        for ((index, value) in vararg.withIndex()) {
            System.out.println("withIndex the element at $index is $value")
        }
        vararg
            .withIndex()
            .forEach { (index, value): IndexedValue<Int> -> System.out.println("withIndex the element at $index is $value") }

        for (i in 1..6 step 2) {
            Log.e(TAG, "vars1: $i")
        }

        for (i in 10 downTo 6 step 2) {
            Log.e(TAG, "vars2: $i")
        }

        for (i in 1 until 10) {
            Log.e(TAG, "vars3: $i")
        }

        val sumlam: (Int, Int) -> Int = { x, y -> x + y }

        Log.e(TAG, "vars4: " + sumlam(1, 2))
    }

    fun sum(x: Int?, y: Int): Int {
        val test = test(1) { a, b -> a + b + 2 }
        val test1 = test(1, { a, b -> a + b + 2 })
        val test3 = test(1) { a: Int, b: Int -> a + b + 2 }
        val test2 = test(1, { a: Int, b: Int -> a + b + 2 })
        val test4 = test(1, fun(a: Int, b: Int): Int {
            return a + b + 2
        })
        return x ?: (1 + y + test)
    }

    fun sum1(x: Int, y: Int): Int = x + y
    fun sum2(x: Int, y: Int) = x + y

    fun test(x: Int?, y: (Int, Int) -> Int): Int {
        val a: Int
        val b = 1
        a = 1
        return x!! + y(1, 2) + a + b
    }

    fun convert() {
        var byte: Byte = 1
        var int: Int = byte.toInt()

        var int1: Int = 1
        var byte1: Byte = int1.toByte()

        val array = Array(3, { i -> (i * 2) })
        val array3 = Array(3) { i -> (i * 2) }
        val array1 = Array(3, { (it * 2) })
        val array2 = Array(3) { it * 2 }
        val array4 = Array(3, { i ->
            val i1 = i * 2
            i1
        })
        val array5 = Array(3) { i ->
            val i1 = i * 2
            i1
        }
        val array6 = Array(3, fun(i: Int): Int {
            val i1 = i * 2
            return i1
        })
        val array7 = Array(6) { i ->
            val i1 = i * 2
            i1
        }
        val array8 = Array<String>(3) { i -> i.toString() }
        val array9 = arrayOf(0, 1, 2)

        Log.e(TAG, "convert: " + GsonUtils.toJson(array8))

        for (c in "String") {
            Log.e(TAG, "convert: $c" + ("String"[0] == 'S') + ("String"[0] == 's'))
        }
        var s = "world"
        val string = """开始
            |多行
            |多行
            |多行
            |多行 j
            |$s
        结束""".trimIndent()
        Log.e(TAG, "多行字符串:$string")
        Log.e(TAG, "多行字符串: ${"$"}$string")
        ToastUtils.showLong("多行字符串:$string")
    }


    val a: Int = 0
    val b = 1
    val c = "one"

    companion object {

        private const val TAG = "Test"
        const val TAG1 = "Test"

        @JvmField
        val TAG2 = "Test2"

        @JvmStatic
        val TAG3 = "Test3"
        val TAG4 = "Test4"
        val TAG5 = "Test5"

        fun test() {
            Log.e(TAG, "test: " + TAG)
        }

        @JvmStatic
        fun test1() {
            Log.e(TAG, "test1: " + TAG)
        }
    }

    fun judge() {
        var max = if (a > b) a else b
        var max1 = if (a > b) {
            a
            Log.e(TAG, "max is : $a")
        } else {
            b
            Log.e(TAG, "max is : $b")
        }
        if (a !in 0..5) {

        }

        val list = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val list1: Collection<Int>
        list1 = list
        var i = when (a) {
            0 -> Log.e(TAG, "a == 1")
            1 -> Log.e(TAG, "a == ")
            2, 3, 4 -> Log.e(TAG, "a == 2or3or4")
            in 5..7 -> Log.e(TAG, "a == 5-7")
            in list1 -> Log.e(TAG, "a in list")
            !is Int -> Log.e(TAG, "a is't Int")
            else -> Log.e(TAG, "a == $a")
        }

        val items = setOf("one", "two", "three")
        when {
            a is Int -> Log.e(TAG, "a is't Int")
            a == 1 -> Log.e(TAG, "a == 1")
            c in items -> Log.e(TAG, "c in items")
            else -> {

            }
        }


    }
}