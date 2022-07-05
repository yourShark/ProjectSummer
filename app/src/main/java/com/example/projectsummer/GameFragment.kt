package com.example.projectsummer
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.projectsummer.databinding.FragmentGameBinding

class GameFragment : Fragment(R.layout.fragment_game) {

    private var _binding: FragmentGameBinding? = null
    lateinit var  mTimer: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Переворот экрана в горизонтальную ориентацию
        if (savedInstanceState == null) {
            activity?.apply {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            }
        }

        mTimer = view.findViewById(R.id.tv_timer)
        _binding = FragmentGameBinding.bind(view)

        // Запуск таймера
        object : CountDownTimer(10000, 1000) {
            override fun onTick(milliseconds: Long) {
                val s: Long = milliseconds % 60000 / 1000

                mTimer.text = String.format(s.toString())
            }

            override fun onFinish() {
                mTimer.text = ""
                lifecycleScope.launchWhenResumed {
                    findNavController().navigate(R.id.action_gameFragment_to_resultFragment)
                }
            }
        }.start()

        // ПЕРЕХОД НА НОВЫЙ TopicFragment ПРИ НАЖАТИИ НА BACK
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(
                    R.id.action_gameFragment_to_resultFragment
                )
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callback)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()

    }
}