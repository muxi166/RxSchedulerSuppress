package com.huya.rxjava2.schedulers.suppress

import androidx.lifecycle.ViewModel
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import kotlin.properties.Delegates

/**
 * @author YvesCheung
 * 2020/7/9
 */
class SuppressSettingViewModel(defaultSwitch: Boolean = true) : ViewModel() {

    var switch: Boolean by Delegates.observable(defaultSwitch) { _, _, new ->
        if (new) {
            turnOn()
        } else {
            turnOff()
        }
    }

    init {
        switch = defaultSwitch
    }

    private fun turnOn() {
        SchedulerSuppress.SuppressBackground()
        AndroidSchedulerSuppress.SuppressMain()
    }

    private fun turnOff() {
        RxJavaPlugins.setComputationSchedulerHandler(null)
        RxJavaPlugins.setIoSchedulerHandler(null)
        RxAndroidPlugins.setMainThreadSchedulerHandler(null)
    }
}