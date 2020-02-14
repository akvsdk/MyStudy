package com.zwl.jyq.mystudy.bean

data class UpdateBean(
        var `data`: Data? = Data(),
        var code: Int? = 0,
        var message: String? = ""
)

data class Data(
        var alipayenable: Int? = 0,
        var apkurl: String? = "",
        var hasupdate: Int? = 0,
        var informstring: String? = "",
        var invitestring: String? = "",
        var isshowprivacy: String? = "",
        var needupdate: Int? = 0,
        var platform: String? = "",
        var showupdate: Int? = 0,
        var updatecontent: String? = "",
        var updateurl: String? = "",
        var version: String? = ""
)