package com.butterfly.sdk

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class ButterflyUtils(){

    companion object{

        private val title = R.string.butterfly_contact

        private val wayTocContactHint = R.string.butterfly_way_to_contact_hint
        private val commentsHint = R.string.butterfly_comments_hint
        private val fakePlaceHint = R.string.butterfly_fake_contact_hint
        private val messageLabelTxt = R.string.butterfly_message_label_text;

        fun getUserInput(
                activity: Activity,
                onDone: (String,String,String) -> Unit
        ) {
            val builder = AlertDialog.Builder(activity)
            builder.setTitle(title).setIcon(R.drawable.ic_butterfly)

            // Set up the input control
            val _alertScaffold = LinearLayout(activity)
            _alertScaffold.orientation = LinearLayout.VERTICAL

            val messageLabel = TextView(activity)
            messageLabel.text = activity.getString(messageLabelTxt)
            messageLabel.typeface = Typeface.DEFAULT_BOLD
            messageLabel.gravity = Gravity.CENTER

            val commentsInput = EditText(activity)
            commentsInput.hint = activity.getString(commentsHint)

            val wayContactInput = EditText(activity)
            wayContactInput.hint = activity.getString(wayTocContactHint)

            val messageInput = EditText(activity)
            messageInput.hint = activity.getString(fakePlaceHint)

            _alertScaffold.addView(View(activity), LinearLayout.LayoutParams(0, 10))

            val alertBodyContainer = LinearLayout(activity)
            alertBodyContainer.orientation = LinearLayout.HORIZONTAL
            alertBodyContainer.addView(View(activity), LinearLayout.LayoutParams(0, 0, 0.5f))


            _alertScaffold.addView(
                    messageLabel,
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
                    messageInput,
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

            builder.setNegativeButton(R.string.butterfly_btn_cancel) { dialog, which -> dialog.cancel() }
            builder.setPositiveButton(R.string.butterfly_btn_send, null)

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
