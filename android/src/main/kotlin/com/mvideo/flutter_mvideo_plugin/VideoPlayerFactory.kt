package com.kvideo.flutter_kvideo_plugin

import android.content.Context
import io.flutter.plugin.common.PluginRegistry
import io.flutter.plugin.common.StandardMessageCodec
import io.flutter.plugin.platform.PlatformView
import io.flutter.plugin.platform.PlatformViewFactory

/**
 * PackageName : com.kvideo.flutter_kvideo_plugin <br/>
 *
 * Creator : sun <br/>
 *
 * CreateDate : 2019-07-17 <br/>
 *
 * CreateTime : 16 : 16 <br/>
 *
 * Description : 用于创建Video实例
 */
class VideoPlayerFactory(var registrar: PluginRegistry.Registrar) : PlatformViewFactory(
    StandardMessageCodec.INSTANCE) {

  override fun create(contex: Context?, viewId: Int, args: Any?): PlatformView {
    return VideoPlayerView(contex, viewId, args, this.registrar)
  }


}