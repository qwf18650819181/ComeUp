package com.comeup.kotlin

/**
 * 功能描述:
 * @author: qiu wanzi
 * @date: 2024年3月28日 0028
 * @version: 1.0
 */
class Person (val id: Int, val name: String, val age: Int) {
    override fun toString(): String {
        return "Person(id=$id, name='$name', age=$age)"
    }
}