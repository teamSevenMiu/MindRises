package com.example.mindrises.puzzle.magic

import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.ArrayList

class MagicSecondViewModel : ViewModel() {

    var width = Resources.getSystem().getDisplayMetrics().widthPixels
    var offsetX = 80;
    var offsetY = 200;
    var matrixSize = 4
    var count:MutableLiveData<Int> = MutableLiveData(0)

    val buttonSize:Int
         get() = (width - (offsetX * 2)) / matrixSize



    fun initGame(iterator: (MagicPosition) -> Unit) {

        val l1 = ArrayList<Int>()
        val l2 = ArrayList<Int>()

        (0..matrixSize - 1).forEach {
            l1.add(it)
            l2.add(it)
        }

        if (matrixSize > 2){
            l1.shuffle()
            l2.shuffle()
        }

        var id = 1
        l1.forEach { x ->
            l2.forEach { y ->
                iterator(MagicPosition(x.toFloat(),y.toFloat(),id))
                id++
            }
        }
    }


    fun move(current: MagicPosition, target: MagicPosition) : Boolean {

        val neighbours =
            MagicPosition(
                (current.x - offsetX) / buttonSize,
                (current.y - offsetY) / buttonSize
            ).neighbours(
                matrixSize
            )

        val x = (target.x - offsetX) / buttonSize
        val y = (target.y - offsetY) / buttonSize

        if (neighbours.contains(MagicPosition(x, y))) {

            val nextPC = MagicPosition(current.x, current.y) - MagicPosition(target.x, target.y)
            val nextPN = MagicPosition(target.x, target.y) - MagicPosition(current.x, current.y)
            current.x = nextPC.x
            current.y = nextPC.y
            target.x = target.x + nextPN.x
            target.y = target.y + nextPN.y

            count.value = (count.value ?: 0) + 1

            return true

        } else {

            return false
        }
    }

    fun check(positions: ArrayList<MagicPosition>): Boolean {

        var b = true

        var index = 0

        (0.. matrixSize - 1).forEach { i ->
            (0.. matrixSize - 1).forEach { j ->

                val xCheckL = positions[index].x
                val xCheckR = (j * buttonSize.toFloat()) + offsetX

                val yCheckL = positions[index].y
                val yCheckR = (i * buttonSize.toFloat()) + offsetY

                Log.i("Check +++","$xCheckL == $xCheckR || $yCheckL == $yCheckR")

                if (xCheckL != xCheckR || yCheckL != yCheckR) {
                    b = false
                }
                index++
            }
        }

        return b
    }
}