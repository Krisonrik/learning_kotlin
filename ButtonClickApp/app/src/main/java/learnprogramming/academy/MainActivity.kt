package learnprogramming.academy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Button

private const val TAG = "MainActivity"
private const val TEXT_CONTENTS = "TextContent"

class MainActivity : AppCompatActivity() {
    private var textView: TextView? = null
    //    private var numTimesClicked = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate: called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val userInput = findViewById<EditText>(R.id.editText)
        userInput.text.clear()
        val button = findViewById<Button>(R.id.button)
        textView = findViewById<TextView>(R.id.textView)
        textView?.text = ""
        textView?.movementMethod = ScrollingMovementMethod()

        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                Log.d(TAG, "onClick: called")
//                numTimesClicked++
//                textView?.append("The button got tapped $numTimesClicked time")
//                if (numTimesClicked != 1) {
//                    textView?.append("s")
//                }
//                textView?.append("\n")
                val textToBeAdded = userInput.text.toString()
                if (textToBeAdded.isNotEmpty()) {
                    textView?.append(userInput.text.toString() + "\n")
                    userInput.text.clear()
                }
            }
        })
    }


    override fun onStart() {
        Log.d(TAG, "onStart: called")
        super.onStart()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Log.d(TAG, "onRestoreInstanceState: called")
        super.onRestoreInstanceState(savedInstanceState)
        textView?.text = savedInstanceState.getString(TEXT_CONTENTS, "")
    }

    override fun onResume() {
        Log.d(TAG, "onResume: called")
        super.onResume()

    }

    override fun onPause() {
        Log.d(TAG, "onPause: called")
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d(TAG, "onSaveInstanceState: called")
        super.onSaveInstanceState(outState)
        outState.putString(TEXT_CONTENTS, textView?.text.toString())
    }

    override fun onStop() {
        Log.d(TAG, "onStop: called")
        super.onStop()
    }

    override fun onRestart() {
        Log.d(TAG, "onRestart: called")
        super.onRestart()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: called")
        super.onDestroy()
    }

}