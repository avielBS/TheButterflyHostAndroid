package com.example.mylibrary

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class ButterflyUtils(){

    companion object{

        private val comments = R.string.butterfly_comments_title
        private val wayContact = R.string.butterfly_way_to_contact
        private val message = R.string.butterfly_fake_contact
        private val title = R.string.butterfly_contact

        private val wayTocContactHint = R.string.way_to_contact_hint
        private val nameHint = R.string.name_hint
        private val notCallLateHint= R.string.not_call_late_hint
        private val messageHint = R.string.butterfly_fake_contact_hint

        fun getUserInput(
                activity: Activity,
                onDone: (String,String,String) -> Unit
        ) {
            val builder = AlertDialog.Builder(activity)
            builder.setTitle(title).setIcon(R.drawable.ic_butterfly)

            // Set up the input control
            val _alertScaffold = LinearLayout(activity)
            _alertScaffold.orientation = LinearLayout.VERTICAL

            val commentsLabel = TextView(activity)
            commentsLabel.text = activity.getString(comments)

            val commentsInput = EditText(activity)
            commentsInput.hint = activity.getString(nameHint) + "\n " + activity.getString(notCallLateHint)

            val wayContactLabel = TextView(activity)
            wayContactLabel.text = activity.getString(wayContact)

            val wayContactInput = EditText(activity)
            wayContactInput.hint = activity.getString(wayTocContactHint)

            val messageLabel = TextView(activity)
            messageLabel.text = activity.getString(message)

            val messageInput = EditText(activity)
            messageInput.hint = activity.getString(messageHint)

            _alertScaffold.addView(View(activity), LinearLayout.LayoutParams(0, 10))

            val alertBodyContainer = LinearLayout(activity)
            alertBodyContainer.orientation = LinearLayout.HORIZONTAL
            alertBodyContainer.addView(View(activity), LinearLayout.LayoutParams(0, 0, 0.5f))

            _alertScaffold.addView(
                    wayContactLabel,
                    LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    )
            )
            _alertScaffold.addView(
                    wayContactInput,
                    LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    )
            )

            _alertScaffold.addView(
                    messageLabel,
                    LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    )
            )
            _alertScaffold.addView(
                    messageInput,
                    LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    )
            )

            _alertScaffold.addView(
                    commentsLabel,
                    LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    )
            )
            _alertScaffold.addView(
                    commentsInput,
                    LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    )
            )

            alertBodyContainer.addView(
                    _alertScaffold,
                    LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 9f)
            )
            alertBodyContainer.addView(View(activity), LinearLayout.LayoutParams(0, 0, 0.5f))

            builder.setView(alertBodyContainer)

            _alertScaffold.addView(View(activity), LinearLayout.LayoutParams(0, 50))

            builder.setNegativeButton(android.R.string.cancel) { dialog, which -> dialog.cancel() }
            builder.setPositiveButton(android.R.string.ok, null)

            val dialog = builder.create()
            dialog.setOnShowListener {
                val button = dialog.getButton(Dialog.BUTTON_POSITIVE).setOnClickListener {
                    if(wayContactInput.text.toString().isNotEmpty()){
                    onDone(commentsInput.text.toString(),wayContactInput.text.toString(),messageInput.text.toString())
                    dialog.dismiss()
                    }
                    else{
                        wayContactInput.startAnimation(AnimationUtils.loadAnimation(activity,R.anim.abc_fade_in))
                        wayContactInput.setHintTextColor(Color.RED)
                    }
                }
            }

           dialog.show()
        }
    }
}
