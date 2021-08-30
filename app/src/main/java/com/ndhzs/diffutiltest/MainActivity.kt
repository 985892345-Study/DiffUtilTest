package com.ndhzs.diffutiltest

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ndhzs.diffutiltest.item.ListItem
import com.ndhzs.diffutiltest.item.TitleItem

class MainActivity : AppCompatActivity() {

    val s1 = "1.2.3.4.5.6.7.8.9"
    val s2 = "10.11.12.13.14.15.16.17.18"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resetData(s1, s2)
        initView()
        initRecycler()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        val textView: TextView = findViewById(R.id.textView)
        textView.text = "尽量保证不要出现重复数字, 且分割符为英文句号“.”"

        val editText1: EditText = findViewById(R.id.editText1)
        val editText2: EditText = findViewById(R.id.editText2)
        editText1.setText(s1)
        editText2.setText(s2)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val s1 = editText1.text.toString()
            val s2 = editText2.text.toString()
            resetData(s1, s2)
        }
    }

    private val mAdapter = SimpleRvAdapter2()
    private lateinit var mTitleItem: TitleItem
    private lateinit var mListItem: ListItem
    private fun initRecycler() {
        mTitleItem = TitleItem(mTitleMap)
        mListItem = ListItem(mListMap)
        val recyclerView: RecyclerView = findViewById(R.id.recycler)
        val layoutManager = GridLayoutManager(this, 3)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int = if (mTitleMap.containsKey(position)) 3 else 1
        }
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = SimpleRvAdapter2()
            .addItem(mTitleItem)
            .addItem(mListItem)
            .show()
    }

    private val mTitleMap = HashMap<Int, String>()
    private val mListMap = HashMap<Int, String>()
    private fun resetData(s1: String, s2: String) {
        mTitleMap.clear()
        mListMap.clear()
        val list1 = s1.split(".")
        val list2 = s2.split(".")
        mTitleMap[0] = "标题1"
        mTitleMap[list1.size + 1] = "标题2"
        for (i in list1.indices) {
            mListMap[i + 1] = list1[i]
        }
        for (i in list2.indices) {
            mListMap[list1.size + 2 + i] = list2[i]
        }
        if (this::mTitleItem.isInitialized) {
            mTitleItem.refreshAllItemMap(mTitleMap,
                isSameName = { oldData, newData ->
                    oldData == newData
                },
                isSameData = { oldData, newData ->
                    // 这里应该比对实际的内容, 但没有内容就这样写吧
                    oldData == newData
                })
        }
        if (this::mListItem.isInitialized) {
            mListItem.refreshAllItemMap(mListMap,
                isSameName = { oldData, newData ->
                    oldData == newData
                },
                isSameData = { oldData, newData ->
                    // 这里应该比对实际的内容, 但没有内容就这样写吧
                    oldData == newData
                })
        }
    }
}