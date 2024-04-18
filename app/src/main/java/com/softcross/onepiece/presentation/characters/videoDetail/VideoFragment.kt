package com.softcross.onepiece.presentation.characters.videoDetail

import android.media.MediaCodec.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import androidx.media3.common.MediaItem
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.softcross.onepiece.R
import com.softcross.onepiece.core.common.delegate.viewBinding
import com.softcross.onepiece.databinding.FragmentVideoBinding

class VideoFragment : Fragment(R.layout.fragment_video) {

    private val binding: FragmentVideoBinding by viewBinding(FragmentVideoBinding::bind)
    private val args: VideoFragmentArgs by navArgs()

    @UnstableApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivBack.setOnClickListener { findNavController().navigate(VideoFragmentDirections.videoToCharacters()) }

        val exoPlayer = ExoPlayer.Builder(requireContext()).build()
        with(binding.playerView) {
            this.player = exoPlayer
            val mediaItem = MediaItem.fromUri(args.videoURL)
            exoPlayer.apply {
                setMediaItem(mediaItem)
                prepare()
                play()
            }
            exoPlayer.videoScalingMode = VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
        }
        binding.frameLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
            }


            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                if (p1 == R.id.end) {
                    binding.playerView.player?.stop()
                } else if (p1 == R.id.start) {
                    binding.playerView.player?.prepare()
                    binding.playerView.player?.play()
                }

            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {}

        })
        binding.ivPlay.setOnClickListener {
            if (binding.playerView.player?.isPlaying == true) {
                binding.playerView.player?.stop()
            } else {
                binding.playerView.player?.prepare()
                binding.playerView.player?.play()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        binding.playerView.player?.apply {
            stop()
        }
    }

}