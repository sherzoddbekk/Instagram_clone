package com.example.instagram_clone.Fragment

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram_clone.R
import com.example.instagram_clone.Utils.Logger
import com.example.instagram_clone.adapter.ProfileAdapter
import com.example.instagram_clone.model.Post
import com.google.android.material.imageview.ShapeableImageView
import com.sangcomz.fishbun.FishBun
import com.sangcomz.fishbun.adapter.image.impl.GlideAdapter

class ProfileFragment : BaseFragment() {

    val TAG = ProfileFragment::class.java.simpleName
    lateinit var recyclerView: RecyclerView

    var pickedPhoto: Uri? = null
    var allPhotos = ArrayList<Uri>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        recyclerView = view.findViewById(R.id.rv_profile)
        recyclerView.setLayoutManager(GridLayoutManager(activity, 2))
        val iv_profile = view.findViewById<ShapeableImageView>(R.id.iv_profile)
        iv_profile.setOnClickListener {

            pickFishBunPhoto()
        }
        refreshAdapter(loadPosts())
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
    private val photoLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
       if (it.resultCode == Activity.RESULT_OK){
           allPhotos =
               it.data?.getParcelableArrayListExtra(FishBun.INTENT_PATH) ?: arrayListOf()
           pickedPhoto = allPhotos.get(0)
           uploadPickedPhoto()
       }
    }

    private fun uploadPickedPhoto() {
        if (pickedPhoto != null){
            Logger.d(TAG,pickedPhoto!!.path.toString())
        }
    }
    private fun refreshAdapter(items: ArrayList<Post>){
        val adapter = ProfileAdapter(this,items)
        recyclerView.adapter = adapter
    }

    private fun loadPosts():ArrayList<Post>{
        val items = ArrayList<Post>()
        items.add(Post("https://images.unsplash.com/photo-1626497361649-81cc097e9bfd?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OXx8bXVzbGltfGVufDB8MnwwfHw%3D&auto=format&fit=crop&w=500&q=60"))
        items.add(Post("https://images.unsplash.com/photo-1626497361649-81cc097e9bfd?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OXx8bXVzbGltfGVufDB8MnwwfHw%3D&auto=format&fit=crop&w=500&q=60"))
        items.add(Post("https://images.unsplash.com/photo-1626497361649-81cc097e9bfd?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OXx8bXVzbGltfGVufDB8MnwwfHw%3D&auto=format&fit=crop&w=500&q=60"))
        items.add(Post("https://images.unsplash.com/photo-1649698313333-ba0e8d82db80?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHw0fHx8ZW58MHx8fHw%3D&auto=format&fit=crop&w=500&q=60"))
        return items
    }

}