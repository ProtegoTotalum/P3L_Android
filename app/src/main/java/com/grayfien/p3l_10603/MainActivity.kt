package com.grayfien.p3l_10603


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.grayfien.p3l_10603.dataClass.UserResponse
import com.shashank.sony.fancytoastlib.FancyToast
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var inputUsername: TextInputEditText
    private lateinit var inputPassword: TextInputEditText
    private lateinit var mainLayout: ConstraintLayout
    //private lateinit var shareP: Preference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //shareP = Preference(this)

        mainLayout = findViewById(R.id.mainLayout)
        val btnClear: Button = findViewById(R.id.btnClear)
        val btnLogin: Button = findViewById(R.id.btnLogin)


        btnClear.setOnClickListener {
            inputUsername.setText("")
            inputPassword.setText("")

            Snackbar.make(mainLayout, "Text Cleared Success", Snackbar.LENGTH_LONG).show()
        }
        inputUsername = findViewById(R.id.input_username)
        inputPassword = findViewById(R.id.input_password)

        btnLogin.setOnClickListener(View.OnClickListener {
            val username: String = inputUsername.text.toString().trim()
            val password: String = inputPassword.text.toString().trim()

            Log.d("LoginRequest", "Username: $username")
            Log.d("LoginRequest", "Password: $password")

            if (username.isEmpty()) {
                FancyToast.makeText(this@MainActivity, "Username tidak boleh kosong!", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show()
            }else if (password.isEmpty()) {
                FancyToast.makeText(this@MainActivity, "Password tidak boleh kosong!", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show()
            }else{
                login()

            }
        })
        //getBundle()

    }

    private fun login() {
        Log.d("LoginResponse", "login() function called")
        val username = inputUsername.text.toString().trim()
        val password = inputPassword.text.toString().trim()


        val call = RClient.api.login(username, password)
        call.enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                Log.d("LoginResponse", "onResponse called")
                if(response.isSuccessful){
                    val login = response.body()!!

                    FancyToast.makeText(
                        this@MainActivity, "Login Success!", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false
                    ).show()
                    val idUserLogin = login.user?.id_user_login
                    val role = login.user?.role
                    Log.d("cek", "id_user_login: $idUserLogin")
                    if (role != "member" && role != "instruktur" && role != "mo"){
                        FancyToast.makeText(this@MainActivity, "Sorry You Don't Have Access To Login", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show()
                    }else{
                        val moveHome = Intent(
                            this@MainActivity, HomeActivity::class.java
                        ).apply { putExtra("id_user_login",  idUserLogin) }
                        startActivity(moveHome)
                    }
                }else{
                    FancyToast.makeText(
                        this@MainActivity,
                        "Login failed!",
                        FancyToast.LENGTH_LONG,
                        FancyToast.ERROR,
                        false
                    ).show()

                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("LoginResponse", "API call failed", t)
            }
        })
    }


//    fun getBundle() {
//        val bundle: Bundle? = intent.extras
//        val name: String? = bundle?.getString("username")
//
//        inputUsername = findViewById(R.id.inputLayoutUsername)
//        inputUsername.setText(name)
//    }
}