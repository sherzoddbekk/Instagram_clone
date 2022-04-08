package com.example.instagram_clone.Fragment

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import com.example.instagram_clone.R
import com.example.instagram_clone.Utils.Logger
import com.example.instagram_clone.Utils.Utils
import com.sangcomz.fishbun.FishBun
import com.sangcomz.fishbun.adapter.image.impl.GlideAdapter
import java.lang.RuntimeException

class UploadFragment:BaseFragment() {

    val TAG = UploadFragment::class.java.simpleName
    private var listener :UploadListener? =null

    lateinit var fl_photo:FrameLayout
    lateinit var iv_photo:ImageView
    lateinit var et_caption:EditText
    var pickedPhoto: Uri? = null
    var allPhotos = ArrayList<Uri>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_upload,container,false)
        initView(view)
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = if (context is UploadListener){
            context
        }else{
            throw RuntimeException("$context must implement UploadListener")
        }
    }

    override fun onDetach() {
        super.onDetach()

    }

    private fun initView(view: View) {
        val fl_view = view.findViewById<FrameLayout>(R.id.fl_view)
        setViewHeight(fl_view)
        et_caption = view.findViewById(R.id.et_caption)
        fl_photo = view.findViewById(R.id.fl_photo)
        iv_photo = view.findViewById(R.id.iv_photo)
        val iv_close = view.findViewById<ImageView>(R.id.cancel)
        val iv_pick = view.findViewById<ImageView>(R.id.iv_pick)
        val  iv_upload = view.findViewById<ImageView>(R.id.iv_upload)

        iv_pick.setOnClickListener {
            pickFishBunPhoto()
        }

        iv_close.setOnClickListener {
            hidePickedPhoto()
        }
        iv_upload.setOnClickListener {
            uploadPhotoNew()
        }

    }

    /**
     * Set view heigt as screen width
     */

    private fun setViewHeight(view: View){
        val params :ViewGroup.LayoutParams = view.getLayoutParams()
        params.height = Utils.screenSize(requireActivity().application).width
        view.setLayoutParams(params)
    }

    /**
     * Pick photo using FishBun library
     */

    private fun pickFishBunPhoto(){
        FishBun.with(this)
            .setImageAdapter(GlideAdapter())
            .setMaxCount(1)
            .setMinCount(1)
            .setSelectedImages(allPhotos)
            .startAlbumWithActivityResultCallback(photoLauncher)
    }
    private fun uploadPhotoNew(){
        listener!!.scrollToHome()
        val caption = et_caption.text.toString().trim()
        if (caption.isNotEmpty() && pickedPhoto != null){
            Logger.d(TAG,caption)
            Logger.d(TAG,pickedPhoto!!.path.toString())
            resetAll()
        }
    }

    private val photoLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == Activity.RESULT_OK){
                allPhotos =
                    it.data?.getParcelableArrayListExtra(FishBun.INTENT_PATH)?: arrayListOf()
                pickedPhoto = allPhotos.get(0)
                showPickedPhoto()
            }
        }
    private fun showPickedPhoto(){
        fl_photo.visibility = View.VISIBLE
        iv_photo.setImageURI(pickedPhoto)
    }

    private fun hidePickedPhoto(){
        pickedPhoto = null
        fl_photo.visibility = View.GONE
    }
    private fun resetAll(){
        et_caption.text.clear()
        pickedPhoto = null
        fl_photo.visibility =View.GONE
    }

    /**
     * This interface is created for communiacation with HomeFragment
     */

    interface UploadListener{
        fun scrollToHome()
    }
}