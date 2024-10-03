package com.example.baitap1

import UserAdapter
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var edtHoTen: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPhone: EditText
    private lateinit var rgGender: RadioGroup
    private lateinit var cbTerms: CheckBox
    private lateinit var btnLuu: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter
    private val userList = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ các view từ XML
        edtHoTen = findViewById(R.id.edtHoTen)
        edtEmail = findViewById(R.id.edtEmail)
        edtPhone = findViewById(R.id.edtPhone)
        rgGender = findViewById(R.id.rg_gender)
        cbTerms = findViewById(R.id.cb_terms)
        btnLuu = findViewById(R.id.btnLuu)
        recyclerView = findViewById(R.id.recyclerView)

        // Thiết lập RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        userAdapter = UserAdapter(userList)
        recyclerView.adapter = userAdapter

        // Xử lý sự kiện click nút "Lưu thông tin"
        btnLuu.setOnClickListener {
            saveUserInfo()
        }
        }
    private fun saveUserInfo() {
        val hoTen = edtHoTen.text.toString().trim()
        val email = edtEmail.text.toString().trim()
        val phone = edtPhone.text.toString().trim()
        val genderId = rgGender.checkedRadioButtonId
        val gender = when (genderId) {
            R.id.rb_male -> "Nam"
            R.id.rb_female -> "Nữ"
            R.id.rb_other -> "Khác"
            else -> ""
        }
        if (!cbTerms.isChecked) {
            Toast.makeText(this, "Vui lòng đồng ý với điều khoản sử dụng", Toast.LENGTH_SHORT).show()
            return
        }

        // Kiểm tra các trường nhập liệu
        if (TextUtils.isEmpty(hoTen) || TextUtils.isEmpty(email) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(gender)) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            return
        }
        // Thêm người dùng vào danh sách và cập nhật RecyclerView
        val user = User(hoTen, email, phone, gender)
        userList.add(user)
        userAdapter.notifyDataSetChanged()

        // Xóa các trường nhập liệu sau khi lưu
        clearFields()
}
    private fun clearFields() {
        edtHoTen.text.clear()
        edtEmail.text.clear()
        edtPhone.text.clear()
        rgGender.clearCheck()
        cbTerms.isChecked = false
    }
}


