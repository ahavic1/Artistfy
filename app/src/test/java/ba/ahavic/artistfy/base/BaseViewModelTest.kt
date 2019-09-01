package ba.ahavic.artistfy.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import ba.ahavic.artistfy.ui.base.viewmodel.BaseError
import ba.ahavic.artistfy.ui.base.viewmodel.NavigationAction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.mockito.Mock
import org.mockito.MockitoAnnotations

abstract class BaseViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Mock
    lateinit var errorObserver: Observer<BaseError>

    @Mock
    lateinit var navigationObserver: Observer<NavigationAction>

    @Before
    open fun setup() {
        MockitoAnnotations.initMocks(this)
    }
}

@ExperimentalCoroutinesApi
class CoroutineTestRule : TestRule {
    override fun apply(base: Statement?, description: Description?): Statement =
        object : Statement() {
            override fun evaluate() {
                Dispatchers.setMain(TestCoroutineDispatcher())
                try {
                    base?.evaluate()
                } finally {
                    Dispatchers.resetMain()
                }
            }
        }
}
