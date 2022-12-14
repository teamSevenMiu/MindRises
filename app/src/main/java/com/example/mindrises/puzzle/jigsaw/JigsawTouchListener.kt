package com.example.mindrises.puzzle.jigsaw

import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.example.mindrises.puzzle.jigsaw.JigsawPiece
import kotlin.math.abs


class JigsawTouchListener : OnTouchListener {

    lateinit var gameOverCheck: () -> Unit
    private var xDelta = 0f
    private var yDelta = 0f


    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {

        val piece: JigsawPiece = view as JigsawPiece

        if (!piece.canMove) {
            return true
        }

        val lParams = view.layoutParams as RelativeLayout.LayoutParams
        val x = motionEvent.rawX
        val y = motionEvent.rawY

        val tolerance = Math.sqrt(
            Math.pow(view.width.toDouble(), 2.0) + Math.pow(
                view.height.toDouble(),
                2.0
            )
        ) / 10

        when (motionEvent.action and MotionEvent.ACTION_MASK) {

            MotionEvent.ACTION_DOWN -> {
                xDelta = x - lParams.leftMargin
                yDelta = y - lParams.topMargin
                piece.bringToFront()
            }

            MotionEvent.ACTION_MOVE -> {
                lParams.leftMargin = (x - xDelta).toInt()
                lParams.topMargin = (y - yDelta).toInt()
                view.layoutParams = lParams
            }

            MotionEvent.ACTION_UP -> {
                val xDiff: Int = abs(piece.xCoord - lParams.leftMargin)
                val yDiff: Int = abs(piece.yCoord - lParams.topMargin)
                if (xDiff <= tolerance && yDiff <= tolerance) {
                    lParams.leftMargin = piece.xCoord
                    lParams.topMargin = piece.yCoord
                    piece.setLayoutParams(lParams)
                    piece.canMove = false
                    sendViewToBack(piece)
                    gameOverCheck()
                }
            }
        }

        return true
    }

    fun sendViewToBack(child: View) {
        val parent = child.parent as? ViewGroup
        parent?.removeView(child)
        parent?.addView(child, 0)
    }
}