package repository

import java.util.prefs.Preferences

object PreferencesRepository {

    private val prefs = Preferences.userNodeForPackage(PreferencesRepository::class.java)

    fun getPreferenceString(key: String): String? = prefs.get(key, null)
    fun getPreferenceBoolean(key: String, defaultValue: Boolean = false): Boolean = prefs.getBoolean(key, defaultValue)
    fun getPreferenceBooleanNullable(key: String): Boolean? {
        return if (prefs.keys().any { it == key }) {
            prefs.getBoolean(key, false)
        } else {
            null
        }
    }

    fun setPreference(key: String, value: String) = prefs.put(key, value)
    fun setPreferenceBoolean(key: String, value: Boolean) = prefs.putBoolean(key, value)
}
