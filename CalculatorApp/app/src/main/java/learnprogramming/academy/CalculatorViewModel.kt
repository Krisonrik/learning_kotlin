package learnprogramming.academy

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


private const val TAG = "CalculatorViewModel"

class CalculatorViewModel : ViewModel() {
    private var pendingOperation = "="
    private var operationPerformed = false

    var result = MutableLiveData<String>()
    var newNumber = MutableLiveData<String>()
    var operation = MutableLiveData<String>()

    fun digitPressed(buttonName: String) {
        if (!newNumber.isInitialized) {
            newNumber.value = buttonName
            return
        }
        newNumber.value += buttonName
    }

    fun operandPressed(operand: String) {
        if (!operationPerformed && operand == "-" && (!newNumber.isInitialized || newNumber.value!!.isEmpty())) {
            newNumber.value = operand
            return
        }
        try {
            val value = newNumber.value.toString().toDouble()
            performPendingOperation(value)
        } catch (e: NumberFormatException) {
            Log.e(TAG, "Input incomplete, can't convert to double")
        }
        operation.value = operand
        newNumber.value = ""
        pendingOperation = operand
        operationPerformed = false
    }

    private fun performPendingOperation(value: Double) {
        var currentResult: Double
        if (!result.isInitialized || result.value!!.isEmpty()) {
            result.value = value.toString()
            return
        } else {
            currentResult = result.value.toString().toDouble()
        }
        when (pendingOperation) {
            "+" -> currentResult += value
            "-" -> currentResult -= value
            "*" -> currentResult *= value
            "/" -> if (value == 0.0) currentResult = Double.NaN else currentResult /= value
            "=" -> currentResult = value
        }
        result.value = currentResult.toString()
        operationPerformed = true
    }

}