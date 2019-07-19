package com.mvideo.flutter_mvideo_plugin_example

import android.content.res.Configuration
import android.os.Bundle
import android.view.WindowManager

import io.flutter.app.FlutterActivity
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity : FlutterActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    GeneratedPluginRegistrant.registerWith(this)
  }

  override fun onConfigurationChanged(newConfig: Configuration?) {
    super.onConfigurationChanged(newConfig)
    if (newConfig!!.orientation == Configuration.ORIENTATION_LANDSCAPE) {
      window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN) //隐藏状态栏
    } else {
      window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN) //显示状态栏
    }
  }
}
