package com.example.pidu

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup

class SecActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sec)

        val editText = findViewById<EditText>(R.id.editTextText)
        val radioGroupSugar = findViewById<RadioGroup>(R.id.radioGroup)
        val radioGroupIce = findViewById<RadioGroup>(R.id.radioGroup2)
        val buttonSubmit = findViewById<Button>(R.id.button2)

        // 點擊「送出」按鈕，將選擇的結果傳回 MainActivity
        buttonSubmit.setOnClickListener {
            val drink = editText.text.toString().ifEmpty { "無" }

            val sugar = findViewById<RadioButton>(
                radioGroupSugar.checkedRadioButtonId
            )?.text?.toString() ?: "無"

            val ice = findViewById<RadioButton>(
                radioGroupIce.checkedRadioButtonId
            )?.text?.toString() ?: "無"

            val resultIntent = Intent().apply {
                putExtra("drink", drink)
                putExtra("sugar", sugar)
                putExtra("ice", ice)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()  // 關閉當前頁面，回到 MainActivity
        }
    }
}
