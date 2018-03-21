package com.example.laomao.kotlinplayer.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.laomao.kotlinplayer.R
import com.example.laomao.kotlinplayer.model.Lyric
import com.example.laomao.kotlinplayer.utils.LyricLoader
import com.example.laomao.kotlinplayer.utils.LyricUtil
import org.jetbrains.anko.doAsync

/**
 * User: laomao
 * Date: 2018-03-18
 * Time: 12-15
 *
 */
class LyricView : View {

    val paint by lazy { Paint(Paint.ANTI_ALIAS_FLAG) }
    var bigFont = 0f
    var smallFont = 0f
    var white = 0
    var green = 0

    var viewW = 0
    var viewH = 0

    var centerPosition = 0

    var lineHeight = 0

    var duration = 0 // 音乐时长
    var progress = 0 // 当前进度

    var updateByPro = true // 是否根据progress更新进度

    var offY = 0 // 歌词偏移量，可以通过播放进度和手滑动更改

    var list = ArrayList<Lyric>()

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        bigFont = resources.getDimension(R.dimen.bigFont)
        smallFont = resources.getDimension(R.dimen.smallFont)
        white = resources.getColor(R.color.white)
        green = resources.getColor(R.color.green)
        lineHeight = resources.getDimensionPixelOffset(R.dimen.lineHeight)

        paint.textAlign = Paint.Align.CENTER
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (list.size > 0) {
            drawMultiLine(canvas)
        } else {
            drawSingleLine(canvas)
        }
    }

    private fun drawMultiLine(canvas: Canvas?) {
        if (updateByPro)
            offY = getOffsetY()

        // 获取要绘制的文字的边界
        var bounds = Rect()
        var centerText = list.get(centerPosition).content
        paint.getTextBounds(centerText, 0, centerText.length, bounds)
        var textH = bounds.height()
        var centerY = (viewH + textH) / 2 - offY

        for ((index, value) in list.withIndex()) {
            if (index == centerPosition) {
                // 设置画笔大小及颜色
                paint.textSize = bigFont
                paint.color = green
            } else {
                paint.textSize = smallFont
                paint.color = white
            }
            var currentY = centerY - (centerPosition - index) * lineHeight

            // 超出边界的条目不进行绘制
            if (currentY < 0) continue
            if (currentY > viewH + lineHeight) break

            // 绘制条目
            canvas?.drawText(value.content, (viewW / 2).toFloat(), currentY.toFloat(), paint)
        }

    }

    /**
     * 求中心条目向上的偏移量
     */
    private fun getOffsetY(): Int {
        // 求行可用时间
        var lineTime = 0
        if (centerPosition == list.size - 1) {
            // 最后一行为中间行
            lineTime = duration - list.get(centerPosition).startTime
        } else {
            lineTime = list.get(centerPosition + 1).startTime - list.get(centerPosition).startTime
        }
        // 偏移百分比=（progress-中间行开始时间）/ 行可用时间
        var offsetPercent = (progress - list.get(centerPosition).startTime) / lineTime.toFloat()
        // 偏移量=行高*偏移百分比
        var offsetY = lineHeight * offsetPercent
        return offsetY.toInt()
    }

    private fun drawSingleLine(canvas: Canvas?) {
        var text = "正在加载歌词..."
        // 设置画笔大小及颜色
        paint.textSize = bigFont
        paint.color = green
        // 获取要绘制的文字的边界
        var bounds = Rect()
        paint.getTextBounds(text, 0, text.length, bounds)
        var textW = bounds.width()
        var textH = bounds.height()
        // 绘制一行文本
        canvas?.drawText(text, (viewW / 2).toFloat(), ((viewH + textH) / 2).toFloat(), paint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewH = h
        viewW = w
    }

    fun updateProgress(progress: Int) {
        if (!updateByPro) return
        this.progress = progress
        if (progress >= list.get(list.size - 1).startTime) {
            // 使最后一条居中
            centerPosition = list.size - 1
        } else {
            for (index in 0 until list.size - 1) {
                if (progress >= list.get(index).startTime && progress < list.get(index + 1).startTime) {
                    centerPosition = index
                }
            }
        }
        invalidate()
    }

    fun setAudioDuration(duration: Int) {
        this.duration = duration
    }

    /**
     * 初始化歌词数据，这里就不加载真正的歌词了，不过加载歌词的类已写好
     * [LyricUtil] 获取歌词对应的bean集合
     * [LyricLoader] 获取歌词的地址
     */
    fun loadLyric(displayName: String) {
//        doAsync {
//            this@LyricView.list.clear()
//            this@LyricView.list.addAll(LyricUtil.parseLyric(LyricLoader.getLyricFile(displayName)))
//        }
        doAsync {
            list.clear()
            for (index in 1..100) {
                list.add(Lyric(2000 * index, "正在播放第${index}条数据"))
            }
        }
    }

    var downY = 0f
    var markY=0
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    updateByPro = false
                    downY = event.y
                    markY=offY
                }
                MotionEvent.ACTION_MOVE -> {
                    val moveY = event.y
                    offY=(downY-moveY).toInt()+markY
                    if (Math.abs(offY)>lineHeight){
                        centerPosition=centerPosition+offY/lineHeight

                        if (centerPosition<0) centerPosition=0 else if (centerPosition>list.size-1) centerPosition=list.size-1

                        // downY重新设置 这个不是一直设置的，就是在滑动位置大于行高的时候设置一次
                        downY=moveY

                        // 重新确定偏移
                        offY=offY%lineHeight

                        // 重新记录y的便宜量
                        markY=offY

                        listener?.invoke(list.get(centerPosition).startTime)
                    }


                    invalidate()
                }

                MotionEvent.ACTION_UP -> {
                    updateByPro = true
                }
            }
        }
        return true // 消费事件
    }

    /**
     * 设置回调
     */
    var listener:((progress:Int)->Unit)?=null

    fun setLiricMoveListener(listener:((progress:Int)->Unit)?){
        this.listener=listener
    }
}