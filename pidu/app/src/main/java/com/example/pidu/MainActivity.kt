package com.example.pidu

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var textView3: TextView  // 顯示選擇的飲料資訊
    private val REQUEST_CODE = 1  // 定義請求碼

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        textView3 = findViewById(R.id.textView3)

        // 點擊「選擇」按鈕，切換到 SecActivity
        button.setOnClickListener {
            val intent = Intent(this, SecActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    // 接收 SecActivity 傳回的選擇結果
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val drink = data?.getStringExtra("drink") ?: "無"
            val sugar = data?.getStringExtra("sugar") ?: "無"
            val ice = data?.getStringExtra("ice") ?: "無"

            textView3.text = "飲料: $drink\n\n甜度: $sugar\n\n冰塊: $ice"
        }
    }
}
