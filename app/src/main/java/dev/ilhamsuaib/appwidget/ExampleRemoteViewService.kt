package dev.ilhamsuaib.appwidget

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import dev.ilhamsuaib.appwidget.common.Const
import dev.ilhamsuaib.appwidget.model.ContactUiModel

/**
 * Created By @ilhamsuaib on 05/01/21
 */

class ExampleRemoteViewService : RemoteViewsService() {

    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        return ExampleViewFactory(applicationContext, intent)
    }

    class ExampleViewFactory(
        private val context: Context,
        intent: Intent
    ) : RemoteViewsFactory {

        private val contactList: List<ContactUiModel> by lazy {
            val bundle = intent.getBundleExtra(Const.Extra.BUNDLE)
            bundle?.getParcelableArrayList<ContactUiModel>(Const.Extra.CONTACT_LIST).orEmpty()
        }

        override fun onCreate() {

        }

        override fun onDataSetChanged() {

        }

        override fun onDestroy() {

        }

        override fun getCount(): Int = contactList.size

        override fun getViewAt(position: Int): RemoteViews {
            val contact = contactList[position]
            return RemoteViews(context.packageName, R.layout.item_contact).apply {
                setTextViewText(R.id.tvName, contact.name)
                setTextViewText(R.id.tvPhone, contact.phone)

                //setup fill intent for item click
                val fillIntent = Intent().apply {
                    putExtra(Const.Extra.BUNDLE, Bundle().also {
                        it.putParcelable(Const.Extra.CONTACT_ITEM, contact)
                    })
                }
                setOnClickFillInIntent(R.id.contactItemContainer, fillIntent)
            }
        }

        override fun getLoadingView(): RemoteViews? = null

        override fun getViewTypeCount(): Int = 1

        override fun getItemId(position: Int): Long = position.toLong()

        override fun hasStableIds(): Boolean = true
    }
}