package dev.ilhamsuaib.appwidget.common

import android.content.Context
import android.widget.Toast

/**
 * Created By @ilhamsuaib on 05/01/21
 */
 
fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}