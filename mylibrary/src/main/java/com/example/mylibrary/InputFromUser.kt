package com.example.mylibrary

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

fun getUserInput(
        activity: Activity,
        title: String,
        body: String,
        hint: String,
        onDone: (String) -> Unit
) {
    val builder = android.app.AlertDialog.Builder(activity)
    builder.setTitle(title)

    // Set up the input control
    val _alertScaffold = LinearLayout(activity)
    _alertScaffold.orientation = LinearLayout.VERTICAL

    val bodyLabel = TextView(activity)
    bodyLabel.text = body

    val inputTextBox = EditText(activity)
    inputTextBox.hint = hint

    _alertScaffold.addView(View(activity), LinearLayout.LayoutParams(0, 10))

    val alertBodyContainer = LinearLayout(activity)
    alertBodyContainer.orientation = LinearLayout.HORIZONTAL
    alertBodyContainer.addView(View(activity), LinearLayout.LayoutParams(0, 0, 0.5f))
    _alertScaffold.addView(
            bodyLabel,
            LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            )
    )
    _alertScaffold.addView(
            inputTextBox,
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
    builder.setPositiveButton(android.R.string.ok) { dialog, which ->
        onDone(inputTextBox.text.toString())
    }

    builder.show()
}