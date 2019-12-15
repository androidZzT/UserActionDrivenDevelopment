package com.zzt.infra_lib.action

import org.json.JSONObject

/**
 * Created by Android_ZzT on 2019-12-15.
 */

abstract class BaseUserAction {

    abstract fun toJson(): JSONObject

    class UserActionContext {
        protected lateinit var actionName: String
    }
}

