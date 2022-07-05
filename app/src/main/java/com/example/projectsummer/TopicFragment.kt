package com.example.projectsummer
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.projectsummer.databinding.FragmentTopicBinding


class TopicFragment : Fragment(R.layout.fragment_topic) {

    private var _binding: FragmentTopicBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Переворот экрана в вертикальную ориентацию
        if (savedInstanceState == null) {
            activity?.apply {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            }
        }

        _binding = FragmentTopicBinding.bind(view)

        with(binding) {
            btnTopic.setOnClickListener {
                findNavController().navigate(
                    R.id.action_topicFragment_to_gameFragment
                )
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}