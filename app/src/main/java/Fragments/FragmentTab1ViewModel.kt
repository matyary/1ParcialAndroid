package Fragments

import Entities.Sport
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FragmentTab1ViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    val ItemClicked = MutableLiveData<Sport>()
}
