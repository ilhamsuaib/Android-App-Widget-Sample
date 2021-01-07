package dev.ilhamsuaib.appwidget.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created By @ilhamsuaib on 05/01/21
 */

@Parcelize
data class ContactUiModel(
    val name: String,
    val phone: String
): Parcelable