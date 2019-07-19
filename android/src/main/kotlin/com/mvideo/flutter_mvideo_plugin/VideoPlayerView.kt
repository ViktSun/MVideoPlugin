package com.kvideo.flutter_kvideo_plugin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.mvideo.flutter_mvideo_plugin.R
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.PluginRegistry
import io.flutter.plugin.platform.PlatformView

/**
 * PackageName : com.kvideo.flutter_kvideo_plugin <br/>
 *
 * Creator : sun <br/>
 *
 * CreateDate : 2019-07-17 <br/>
 *
 * CreateTime : 15 : 15 <br/>
 *
 * Description : 用于视频播放的View
 */
class VideoPlayerView(var context: Context?, var viewId: Int, var args: Any?,
    var registrar: PluginRegistry.Registrar) : PlatformView, MethodChannel.MethodCallHandler {

  private val inflater: LayoutInflater = LayoutInflater.from(registrar.activity())
  private var mVideo = inflater.inflate(R.layout.m_video, null) as StandardGSYVideoPlayer
  private var methodChannel = MethodChannel(registrar.messenger(), "flutter_mvideo_plugin_$viewId")

  private lateinit var orientationUtils: OrientationUtils

  private lateinit var mVideoOptions: GSYVideoOptionBuilder

  private var isPlay: Boolean = false
  private var isPause: Boolean = false
  private var isFullScreen:Boolean =false

  init {
    this.methodChannel.setMethodCallHandler(this)
  }

  override fun getView() = mVideo

  override fun onMethodCall(methodCall: MethodCall, chanel: MethodChannel.Result) {
    when (methodCall.method) {
      "loadUrl" -> {
        val url = methodCall.arguments.toString()
        initVideo(url)
      }
      else -> {

      }
    }

  }

  override fun dispose() {

  }

  //创建播放器相关的参数
  private fun initVideo(url: String) {
    //初始化旋转
    orientationUtils = OrientationUtils(registrar.activity(), mVideo)

    //是否旋转
    mVideo.isRotateViewAuto = false

    //是否可以滑动调整
    mVideo.setIsTouchWiget(true)

    //添加封面
//    val imageView = ImageView(registrar.activity())
//    Glide.with(registrar.activity()).load("").centerCrop().into(imageView)
//    mVideo.thumbImageView = imageView

    //返回按钮是否可见
    mVideo.backButton.visibility = View.GONE

    //处事换不打开外部旋转
    mVideo.isEnabled = false

    //播放器的参数设置
    mVideoOptions = GSYVideoOptionBuilder()

    mVideoOptions.setIsTouchWiget(true)
        .setRotateViewAuto(false)
        .setLockLand(false)
        .setAutoFullWithSize(true)
        .setShowFullAnimation(true)
        .setNeedLockFull(true)
        .setUrl(url)
        .setCacheWithPlay(true)
        .setVideoAllCallBack(KVideoCallBack(orientationUtils))
        .setLockClickListener { _, lock ->
          orientationUtils.isEnable = !lock
        }.build(mVideo)
    mVideo.fullscreenButton.setOnClickListener {
      orientationUtils.resolveByClick()
      mVideo.startWindowFullscreen(registrar.activity(), true, false)
//      registrar.activity().window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//          WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
    mVideo.startPlayLogic()
  }

}

class KVideoCallBack(private var orientationUtils: OrientationUtils) : GSYSampleCallBack() {
  override fun onPrepared(url: String?, vararg objects: Any?) {
    super.onPrepared(url, *objects)
    orientationUtils.isEnable = true
  }

  override fun onQuitFullscreen(url: String?, vararg objects: Any?) {
    super.onQuitFullscreen(url, *objects)
    orientationUtils.backToProtVideo()
  }
}


