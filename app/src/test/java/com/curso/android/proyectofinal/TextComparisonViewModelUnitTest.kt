package com.curso.android.proyectofinal

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.curso.android.proyectofinal.model.TextsToCompare
import com.curso.android.proyectofinal.view.TextComparisonViewModel
import org.junit.Test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class TextComparisonViewModelUnitTest {
    private lateinit var viewModel: TextComparisonViewModel
    private lateinit var model: TextsToCompare
    private lateinit var model2: TextsToCompare
    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup(){
        Dispatchers.setMain(dispatcher)
        viewModel = TextComparisonViewModel()
        model = TextsToCompare("hola","hola")
        model2 = TextsToCompare("ola","hola")
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }
    @Test
    fun mainViewModel_CheckInitialValue() = runTest {
        val value = viewModel.resultText.value?.length
        assertEquals(null, value)
    }
    @Test
    fun mainViewModel_TestCompareTextIFEquals() = runTest {
        launch {

            viewModel.compareTexts(model)
        }
        advanceUntilIdle()
        val value = viewModel.resultText.value?.toString()
        assertEquals("Los textos son iguales.",value)
    }
    @Test
    fun mainViewModel_TestCompareTextIfNotEquals() = runTest {
        launch {
            viewModel.compareTexts(model2)
        }
        advanceUntilIdle()
        val value = viewModel.resultText.value?.toString()
        assertEquals("Los textos no son iguales.",value)
    }
}