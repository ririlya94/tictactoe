package my.ririlya.tictactoe.util

import android.content.res.Resources

val Int.toPx get() = (this * Resources.getSystem().displayMetrics.density).toInt()
