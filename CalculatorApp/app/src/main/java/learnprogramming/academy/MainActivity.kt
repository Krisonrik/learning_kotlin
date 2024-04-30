package learnprogramming.academy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import learnprogramming.academy.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val viewModel = ViewModelProvider(this)[CalculatorViewModel::class.java]
        viewModel.resultValue.observe(
            this
        ) { stringResult: String -> binding.result.setText(stringResult) }
        viewModel.newNumberValue.observe(
            this
        ) { stringResult: String -> binding.newNumber.setText(stringResult) }
        viewModel.operationValue.observe(
            this
        ) { stringResult: String -> binding.operation.text = stringResult }

        val numericButtonListener = View.OnClickListener { v ->
            viewModel.digitPressed((v as Button).text.toString())
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

        val operationButtonListener = View.OnClickListener { v ->
            viewModel.operandPressed((v as Button).text.toString())
        }

        binding.buttonEquals.setOnClickListener(operationButtonListener)
        binding.buttonDivide.setOnClickListener(operationButtonListener)
        binding.buttonMultiply.setOnClickListener(operationButtonListener)
        binding.buttonPlus.setOnClickListener(operationButtonListener)
        binding.buttonMinus.setOnClickListener(operationButtonListener)
    }

}