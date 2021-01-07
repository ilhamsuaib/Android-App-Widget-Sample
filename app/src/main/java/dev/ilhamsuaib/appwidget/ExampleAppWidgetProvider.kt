package dev.ilhamsuaib.appwidget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import dev.ilhamsuaib.appwidget.common.Const
import dev.ilhamsuaib.appwidget.common.showToast
import dev.ilhamsuaib.appwidget.model.ContactUiModel

/**
 * Created By @ilhamsuaib on 03/01/21
 */

class ExampleAppWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        //do update widget content, hit API, local DB, etc.
        ExampleWidgetStateHandler.showContactList(context, getContactList())
        super.onUpdate(context, appWidgetManager, appWidgetIds)
    }

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            AppWidgetManager.ACTION_APPWIDGET_UPDATE -> {
                //calling onUpdate method
            }
            Const.Action.CLICK_CONTACT -> setOnContactClick(context, intent)
            Const.Action.CLICK_REFRESH -> refreshWidget(context, intent)
        }
        super.onReceive(context, intent)
    }

    private fun refreshWidget(context: Context, intent: Intent) {
        context.showToast("Loading...")
    }

    private fun setOnContactClick(context: Context, intent: Intent) {
        val bundle = intent.getBundleExtra(Const.Extra.BUNDLE)
        val contact = bundle?.getParcelable<ContactUiModel>(Const.Extra.CONTACT_ITEM)
        context.showToast("Click ${contact?.name.orEmpty()}")
    }

    private fun getContactList(): List<ContactUiModel> {
        return listOf(
            ContactUiModel("Bambang", "1234567890"),
            ContactUiModel("Ani", "1234567890"),
            ContactUiModel("Budi", "1234567890"),
            ContactUiModel("Tono", "1234567890"),
            ContactUiModel("Meri", "1234567890"),
            ContactUiModel("Messi", "1234567890"),
            ContactUiModel("Ronaldo", "1234567890"),
        )
    }

    /*...*/
}