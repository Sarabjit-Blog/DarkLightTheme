package sarabjit.blog.groovytestapplication.darktheme

import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import sarabjit.blog.darktheme.Constants


class DarkThemeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val sharedPreferences: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(this)
        val themePref: String? =
            sharedPreferences.getString(Constants.THEME_PREFERENCE, ThemeHelper.DEFAULT_MODE)
        themePref?.let { ThemeHelper.applyTheme(it) }
    }
}