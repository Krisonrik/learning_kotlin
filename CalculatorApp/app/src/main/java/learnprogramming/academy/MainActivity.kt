package learnprogramming.academy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Button
import learnprogramming.academy.databinding.ActivityMainBinding


private const val TAG = "MainActivity"
private const val KEY_PENDING_OPERATION = "pendingOperation"
private const val KEY_OPERATION_PERFORMED = "operationPerformed"


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
//    private lateinit var result: EditText
//    private lateinit var newNumber: EditText
//    private val displayOperation: TextView by lazy(LazyThreadSafetyMode.NONE) { findViewById(R.id.operation) }

    private var pendingOperation = "="
    private var operationPerformed = false


    private fun performPendingOperation(value: Double) {
        var currentResult: Double
        if (binding.result.text.isEmpty()) {
            binding.result.setText(value.toString())
            return
        } else {
            currentResult = binding.result.text.toString().toDouble()
        }
        Log.d(TAG, pendingOperation)
        when (pendingOperation) {
            "+" -> currentResult += value
            "-" -> currentResult -= value
            "*" -> currentResult *= value
            "/" -> if (value == 0.0) currentResult = Double.NaN else currentResult /= value
            "=" -> currentResult = value
        }
        binding.result.setText(currentResult.toString())
        operationPerformed = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        result = findViewById(R.id.result)
//        newNumber = findViewById(R.id.newNumber)
//
//        val button0: Button = findViewById(R.id.button0)
//        val button1: Button = findViewById(R.id.button1)
//        val button2: Button = findViewById(R.id.button2)
//        val button3: Button = findViewById(R.id.button3)
//        val button4: Button = findViewById(R.id.button4)
//        val button5: Button = findViewById(R.id.button5)
//        val button6: Button = findViewById(R.id.button6)
//        val button7: Button = findViewById(R.id.button7)
//        val button8: Button = findViewById(R.id.button8)
//        val button9: Button = findViewById(R.id.button9)
//        val buttonDot: Button = findViewById(R.id.buttonDot)
//
//        val buttonEquals: Button = findViewById(R.id.buttonEquals)
//        val buttonDivide: Button = findViewById(R.id.buttonDivide)
//        val buttonMultiply: Button = findViewById(R.id.buttonMultiply)
//        val buttonPlus: Button = findViewById(R.id.buttonPlus)
//        val buttonMinus: Button = findViewById(R.id.buttonMinus)

        val numericButtonListener = View.OnClickListener { view ->
            val button = view as Button
            binding.newNumber.append(button.text)
        }

        binding.button0.setOnClickListener(numericButtonListener)
        binding.button1.setOnClickListener(numericButtonListener)
        binding.button2.setOnClickListener(numericButtonListener)
        binding.button3.setOnClickListener(numericButtonListener)
        binding.button4.setOnClickListener(numericButtonListener)
        binding.button5.setOnClickListener(numericButtonListener)
        binding.button6.setOnClickListener(numericButtonListener)
        binding.button7.setOnClickListener(numericButtonListener)
        binding.button8.setOnClickListener(numericButtonListener)
        binding.button9.setOnClickListener(numericButtonListener)
        binding.buttonDot.setOnClickListener(numericButtonListener)

        val operationButtonListener = View.OnClickListener { view ->
            val operation = (view as Button).text.toString()
            if (!operationPerformed && operation == "-" && binding.newNumber.text.isEmpty()) {
                binding.newNumber.setText(operation)
                return@OnClickListener
            }
            try {
                val value = binding.newNumber.text.toString().toDouble()
                performPendingOperation(value)
            } catch (e: NumberFormatException) {
                Log.e(TAG, "Input incomplete, can't convert to double")
            }
            binding.operation.text = operation
            binding.newNumber.text.clear()
            pendingOperation = operation
            operationPerformed = false
        }

        binding.buttonEquals.setOnClickListener(operationButtonListener)
        binding.buttonDivide.setOnClickListener(operationButtonListener)
        binding.buttonMultiply.setOnClickListener(operationButtonListener)
        binding.buttonPlus.setOnClickListener(operationButtonListener)
        binding.buttonMinus.setOnClickListener(operationButtonListener)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_PENDING_OPERATION, pendingOperation)
        outState.putBoolean(KEY_OPERATION_PERFORMED, operationPerformed)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        pendingOperation = savedInstanceState.getString(KEY_PENDING_OPERATION, "")
        binding.operation.text = pendingOperation
        operationPerformed = savedInstanceState.getBoolean(KEY_OPERATION_PERFORMED, false)
    }
}