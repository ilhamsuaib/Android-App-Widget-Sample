package dev.ilhamsuaib.appwidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.RemoteViews
import dev.ilhamsuaib.appwidget.common.Const
import dev.ilhamsuaib.appwidget.model.ContactUiModel

/**
 * Created By @ilhamsuaib on 05/01/21
 */

object ExampleWidgetStateHandler {

    fun showContactList(context: Context, contacts: List<ContactUiModel>) {
        val awm = AppWidgetManager.getInstance(context)
        val widgetIds = awm.getAppWidgetIds(ComponentName(context, ExampleAppWidgetProvider::class.java))
        val remoteViews = RemoteViews(context.packageName, R.layout.app_widget_layout)
        widgetIds.forEach { widgetId ->
            with(remoteViews) {

                //show contact list
                val remoteViewIntent = Intent(context, ExampleRemoteViewService::class.java).apply {
                    data = Uri.parse(toUri(Intent.URI_INTENT_SCHEME))
                    putExtra(Const.Extra.BUNDLE, Bundle().also {
                        it.putParcelableArrayList(Const.Extra.CONTACT_LIST, ArrayList(contacts))
                    })
                }
                setRemoteAdapter(R.id.lvContact, remoteViewIntent)

                //setup list view item click event
                val contactClickIntent =
                    Intent(context, ExampleAppWidgetProvider::class.java).apply {
                        data = Uri.parse(toUri(Intent.URI_INTENT_SCHEME))
                        action = Const.Action.CLICK_CONTACT
                    }
                val contactClickPendingIntent = PendingIntent.getBroadcast(
                    context,
                    0,
                    contactClickIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
                setPendingIntentTemplate(R.id.lvContact, contactClickPendingIntent)

                //setup refresh click intent
                val refreshIntent = Intent(context, ExampleAppWidgetProvider::class.java).apply {
                    action = Const.Action.CLICK_REFRESH
                }
                val refreshPendingIntent = PendingIntent.getBroadcast(
                    context,
                    0,
                    refreshIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
                setOnClickPendingIntent(R.id.btnRefresh, refreshPendingIntent)

                awm.updateAppWidget(widgetId, this)
            }
        }
    }
}