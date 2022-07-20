package com.example.tippyappmvvm.view

import android.animation.ArgbEvaluator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.SeekBar
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.example.tippyappmvvm.R
import com.example.tippyappmvvm.databinding.ActivityMainBinding
import com.example.tippyappmvvm.viewmodel.MyViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    /**
     * Creacion e inicializacion de viewmodel
     */
    private val viewModel by viewModels<MyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.seekBarTip.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                /**
                 * Imprime en LogCat el valor de la barra de progreso
                 */

                (progress.toString() + getString(R.string.percent)).also { binding.tvTipPercentLabel.text = it }
                obtainTipAndTotal()
                changeDescription()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })

        binding.etBaseAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                /**
                 * Obtengo lo que el usuario esta escribiendo
                 */
                Log.i("TAG", "afterTextChanged $s")
                obtainTipAndTotal()
                changeDescription()
            }
        })
    }

    private fun changeDescription() {
        viewModel.addTipDescription(binding.seekBarTip.progress)
        binding.tvDescriptionTip.text = viewModel.addTipDescription(binding.seekBarTip.progress).categoryTip
        changeColorDescription()
    }

    private fun changeColorDescription() {
        val color = ArgbEvaluator().evaluate(
            binding.seekBarTip.progress.toFloat() / binding.seekBarTip.max,
            ContextCompat.getColor(this, R.color.worst_tip),
            ContextCompat.getColor(this, R.color.best_tip),
        ) as Int
        binding.tvDescriptionTip.setTextColor(color)
    }

    private fun obtainTipAndTotal() {
        if(binding.etBaseAmount.text.isBlank()){
            binding.tvTipAmount.text = ""
            binding.tvTotalAmount.text = ""
            return
        }
        /**
         * Se actualiza la actividad
         */
        val tipAmount = viewModel.calculateTipAmount(binding.etBaseAmount.text.toString(), binding.seekBarTip.progress)
        binding.tvTipAmount.text = getString(R.string.format).format(tipAmount)

        val totalAmount = viewModel.calculateTotalAmount(binding.etBaseAmount.text.toString())
        binding.tvTotalAmount.text = getString(R.string.format).format(totalAmount)
    }


}