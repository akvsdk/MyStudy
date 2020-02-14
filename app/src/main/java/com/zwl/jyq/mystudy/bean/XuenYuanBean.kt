package com.zwl.jyq.mystudy.bean

data class XuenYuanBean(val id: Int, val name: String)

data class HandleBook(
    var bookDesc: String? = "",
    var bookName: String? = "",
    var bookNum: Int? = 0,
    var bookUrl: String? = "",
    var correctAnswer: Int? = 0,
    var difficulty: Int? = 0,
    var leftAnswer: Int? = 0,
    var rightAnswer: Int? = 0
    )