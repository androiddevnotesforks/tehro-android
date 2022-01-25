package dev.yasan.metro.tehran.ui.composable.screen.station

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import dev.yasan.metro.tehran.ui.composable.common.teh.TehError
import dev.yasan.metro.tehran.ui.composable.common.teh.TehErrorType
import dev.yasan.metro.tehran.ui.composable.common.teh.TehProgress
import dev.yasan.metro.tehran.ui.composable.screen.station.modules.StationScreenSuccess
import dev.yasan.metro.tehran.util.Resource

@Composable
fun StationScreen(stationViewModel: StationViewModel, stationId: Int) {

    fun loadData() {
        stationViewModel.loadStation(stationId = stationId)
    }

    val stationResource = stationViewModel.station.observeAsState()

    when (stationResource.value) {
        is Resource.Initial -> {
            loadData()
        }
        is Resource.Error -> {
            TehError(type = TehErrorType.ERROR_GO_BACK) {
                loadData()
            }
        }
        is Resource.Success -> {
            val station = stationResource.value!!.data!!
            StationScreenSuccess(station = station)
        }
        else -> {
            TehProgress()
        }
    }

}