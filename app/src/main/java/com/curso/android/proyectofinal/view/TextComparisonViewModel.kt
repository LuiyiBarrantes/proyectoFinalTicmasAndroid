package com.curso.android.proyectofinal.view
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.curso.android.proyectofinal.model.TextsToCompare

class TextComparisonViewModel : ViewModel() {

    private val _resultText = MutableLiveData<String>()
    val resultText: LiveData<String> = _resultText

    fun compareTexts(model: TextsToCompare) {
        val areTextsEqual = model.text1 == model.text2
        val areTextsEmpty = model.text1.isEmpty() || model.text2.isEmpty()

        _resultText.value = if (areTextsEmpty){
            "Debes completar los campos."
        }else {
            if (areTextsEqual) {
                "Los textos son iguales."
            } else {
                "Los textos no son iguales."
            }
        }
    }
}
