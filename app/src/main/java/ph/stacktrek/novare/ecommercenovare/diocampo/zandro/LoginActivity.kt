package ph.stacktrek.novare.ecommercenovare.diocampo.zandro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.snackbar.Snackbar
import ph.stacktrek.novare.ecommercenovare.diocampo.zandro.databinding.ActivityLoginBinding
import ph.stacktrek.novare.ecommercenovare.diocampo.zandro.utility.PreferenceUtility

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val launchRegister = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val data = result.data

        if (result.resultCode == RESULT_OK) {
            val registeredUsername = data?.getStringExtra("username")
            val registeredPassword = data?.getStringExtra("password")

            binding.usernametext.setText(registeredUsername)
            binding.passwordtext.setText(registeredPassword)

            Snackbar.make(
                binding.root,
                "Registered ${data!!.getStringExtra("username")}",
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.registerButton.setOnClickListener {
            val goToRegister = Intent(
                applicationContext,
                RegisterActivity::class.java
            )

            launchRegister.launch(goToRegister)
        }

        binding.loginButton.setOnClickListener {

            var username = binding.usernametext.text.toString()
            var password = binding.passwordtext.text.toString()

            if(username == "admin" && password == "admin") {
                val goToMain = Intent(
                    applicationContext,
                    MainActivity::class.java
                )

                goToMain.putExtra("username", username)

                val bundle = Bundle()
                bundle.putString("bundle_username", username)
                goToMain.putExtras(bundle)

                PreferenceUtility(applicationContext).apply {
                    saveStringPreferences("username", binding.usernametext.text.toString())
                    saveStringPreferences("password", binding.passwordtext.text.toString())
                }

                startActivity(goToMain)
                finish()
            }else{
                Snackbar.make(binding.root,
                    "Please provide admin/admin",
                    Snackbar.LENGTH_SHORT).show()
            }
        }
        PreferenceUtility(applicationContext).apply {
            binding.usernametext.setText(getStringPreferences("username" ))
            binding.passwordtext.setText(getStringPreferences("password" ))
        }
    }

}