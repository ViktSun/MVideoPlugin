package com.immediate.functiontest

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * PackageName : com.immediate.functiontest <br/>
 *
 * Creator : sun <br/>
 *
 * CreateDate : 2019-12-03 <br/>
 *
 * CreateTime : 11 : 41 <br/>
 *
 * Description :
 */


fun main(args: Array<String>) {
    print("aaaaaaaa")
    GlobalScope.launch(Dispatchers.Main) {
        withContext(Dispatchers.Main){
        print("Thread:${Thread.currentThread().name}")
        }
    }

}