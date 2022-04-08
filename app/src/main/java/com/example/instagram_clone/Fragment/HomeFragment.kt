package com.example.instagram_clone.Fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.instagram_clone.R

class HomeFragment : BaseFragment() {

    val TAG = UploadFragment::class.java.simpleName
    private var listener: HomeListener? = null



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        initView(view)
        return view
    }

    /*
  onAttach is for communication of Fragments
   */

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = if (context is HomeFragment.HomeListener){
            context
        }else{
            throw RuntimeException("$context must implement UploadListener")
        }
    }
    /*
  onAttach is for communication of Fragments
   */
    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun initView(view: View) {

        val iv_camera =view.findViewById<ImageView>(R.id.iv_camera)
        iv_camera.setOnClickListener {
            listener!!.scrollToUpload()
        }
    }

    /**
     * This interface is created for communiacation with UploadFragment
     */

    interface HomeListener {
        fun scrollToUpload()
    }
}