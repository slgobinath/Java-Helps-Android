package com.javahelps.usermanagement

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.javahelps.usermanagement.service.Model
import com.javahelps.usermanagement.service.ServiceFactory
import com.javahelps.usermanagement.service.UserService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val service by lazy {
        val factory = ServiceFactory.getInstance("http://10.0.2.2:8080", "admin", "admin")
        factory.build(UserService::class.java)
    }
    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun createUser(view: View) {
        // Construct a user object
        val user = Model.User(
            etUsername.text.toString(),
            etPassword.text.toString(),
            etName.text.toString(),
            etEmail.text.toString()
        )

        this.disposable = this.service.create(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ showResult("Successfully created the new user ${user.username}") },
                { showResult("Failed to create the new user!") })
    }

    fun readUser(view: View) {
        val username = etUsername.text.toString()

        this.disposable = this.service.read(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ user ->
                run {
                    etUsername.setText(user.username)
                    etPassword.setText(user.password)
                    etName.setText(user.name)
                    etEmail.setText(user.email)
                }
            },
                { showResult("Failed to read the user $username") })
    }

    fun updateUser(view: View) {
        val username = etUsername.text.toString()

        // Construct a user object
        val user = Model.User(
            etUsername.text.toString(),
            etPassword.text.toString(),
            etName.text.toString(),
            etEmail.text.toString()
        )

        this.disposable = this.service.update(username, user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ showResult("Successfully updated the user $username") },
                { showResult("Failed to update the user $username") })
    }

    fun deleteUser(view: View) {
        val username = etUsername.text.toString()

        // Prevent from accidentally deleting the admin user
        if ("admin" == username) {
            showResult("Cannot delete admin!")
            return
        }

        this.disposable = this.service.delete(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ showResult("Successfully deleted the user $username") },
                { showResult("Failed to delete the user $username") })
    }

    private fun showResult(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}
