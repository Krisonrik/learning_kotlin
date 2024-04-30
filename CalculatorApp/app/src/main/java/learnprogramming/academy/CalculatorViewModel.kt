package learnprogramming.academy

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import java.math.BigDecimal


private const val TAG = "CalculatorViewModel"

class CalculatorViewModel : ViewModel() {
    private var pendingOperation = "="
    private var operationPerformed = false

    private var result = MutableLiveData<Double>()
    val resultValue: LiveData<String>
        get() = result.map { it.toString() }

    private var newNumber = MutableLiveData<String>()
    val newNumberValue: LiveData<String>
        get() = newNumber

    private var operation = MutableLiveData<String>()
    val operationValue: LiveData<String>
        get() = operation

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
        var currentResult: BigDecimal
        val decimalValue: BigDecimal = value.toBigDecimal()
        if (!result.isInitialized) {
            result.value = value
            return
        } else {
            currentResult = result.value.toString().toBigDecimal()
        }
        when (pendingOperation) {
            "+" -> currentResult += decimalValue
            "-" -> currentResult -= decimalValue
            "*" -> currentResult *= decimalValue
            "/" -> if (value == 0.0) currentResult =
                BigDecimal(Double.NaN) else currentResult /= decimalValue

            "=" -> currentResult = decimalValue
        }
        result.value = currentResult.toDouble()
        operationPerformed = true
    }

}