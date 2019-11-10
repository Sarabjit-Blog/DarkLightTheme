package sarabjit.blog.darktheme

import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*
import sarabjit.blog.groovytestapplication.darktheme.ThemeHelper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeThemeButton.setOnClickListener { showDialog() }
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        val currentNightMode = newConfig.uiMode and Configuration.UI_MODE_NIGHT_MASK

        when (currentNightMode) {
            Configuration.UI_MODE_NIGHT_NO -> {
                recreate()
            }
            Configuration.UI_MODE_NIGHT_YES -> {
                recreate()
            }
            Configuration.UI_MODE_NIGHT_UNDEFINED -> {
                recreate()
            }
        }
        super.onConfigurationChanged(newConfig)
    }

    fun showDialog() {
        val listItems =
            arrayOf(ThemeHelper.DARK_MODE, ThemeHelper.LIGHT_MODE, ThemeHelper.DEFAULT_MODE)
        val mBuilder = AlertDialog.Builder(this@MainActivity)
        mBuilder.setTitle(Constants.THEME_TITLE)
        mBuilder.setSingleChoiceItems(listItems, -1) { dialogInterface, position ->
            val sharedPreferences: SharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this)
            sharedPreferences.edit()
                .putString(Constants.THEME_PREFERENCE, listItems[position])
                .apply()
            ThemeHelper.applyTheme(listItems[position])
            dialogInterface.dismiss()
        }

        mBuilder.setNeutralButton(Constants.DIALOG_CANCEL_BUTTON) { dialog, which ->
            // Do something when click the neutral button
            dialog.cancel()
        }

        val mDialog = mBuilder.create()
        mDialog.show()
    }
}
