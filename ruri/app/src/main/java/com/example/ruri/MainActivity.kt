package com.example.ruri

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.EditText
import android.widget.TextView
import android.widget.RadioGroup
import android.widget.Button
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val edName = findViewById<EditText>(R.id.edName)
        val tvText = findViewById<TextView>(R.id.tvText)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val btnScissor = findViewById<Button>(R.id.btnScissor)

        val tvName = findViewById<TextView>(R.id.tvName)
        val tvWinner = findViewById<TextView>(R.id.tvWinner)
        val tvMyMora = findViewById<TextView>(R.id.tvMyMora)
        val tvPCMora = findViewById<TextView>(R.id.tvPCMora)
        val btnMora = findViewById<Button>(R.id.btnMora)

        btnMora.setOnClickListener {
            val playerName = edName.text.toString().trim()

            // 檢查是否輸入玩家名稱
            if (playerName.isEmpty()) {
                Toast.makeText(this, "請輸入玩家名稱！", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 取得玩家選擇的手勢
            val playerChoice = when (radioGroup.checkedRadioButtonId) {
                R.id.btnScissor -> "剪刀"
                R.id.btnStone -> "石頭"
                R.id.btnPaper -> "布"
                else -> {
                    Toast.makeText(this, "請選擇一個手勢！", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            // 電腦隨機選擇手勢
            val moraOptions = arrayOf("剪刀", "石頭", "布")
            val pcChoice = moraOptions[Random.nextInt(3)]

            // 判斷勝負
            val winner = when {
                playerChoice == pcChoice -> "平手"
                (playerChoice == "剪刀" && pcChoice == "布") ||
                        (playerChoice == "石頭" && pcChoice == "剪刀") ||
                        (playerChoice == "布" && pcChoice == "石頭") -> playerName

                else -> "電腦"
            }

            tvName.text = "玩家: $playerName"
            tvWinner.text = "勝利者: $winner"
            tvMyMora.text = "我方出拳: $playerChoice"
            tvPCMora.text = "電腦出拳: $pcChoice"
        }
    }
}