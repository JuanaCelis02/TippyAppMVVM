package com.example.tippyappmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.SeekBar
import androidx.activity.viewModels
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
                Log.i("TAG", "onProgressChanged $progress")

                binding.tvTipPercentLabel.text = "$progress%"
                obtainTipAndTotal()
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
            }
        })
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
        viewModel.calculateTipAmount(binding.etBaseAmount.text.toString(), binding.seekBarTip.progress)
        binding.tvTipAmount.text = "%.3f".format(viewModel.savedTipValue)

        viewModel.calculateTotalAmount(binding.etBaseAmount.text.toString())
        binding.tvTotalAmount.text = "%.3f".format(viewModel.savedTotalAmount)
    }

}