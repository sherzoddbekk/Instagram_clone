package com.example.instagram_clone.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram_clone.R
import com.example.instagram_clone.adapter.FavoriteAdapter
import com.example.instagram_clone.adapter.HomeAdapter
import com.example.instagram_clone.model.Post

class FavoriteFragment:BaseFragment() {

    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite,container,false)
        initView(view)
        return view
    }

    private fun initView(view: View) {

        recyclerView =view.findViewById(R.id.recyclerView_favorite)
        recyclerView.setLayoutManager(GridLayoutManager(activity, 1))
        refreshAdapter(loadPosts())
    }

    private fun refreshAdapter(items: ArrayList<Post>) {
        val adapter = FavoriteAdapter(this, items)
        recyclerView.adapter = adapter

    }

    private fun loadPosts(): ArrayList<Post> {

        val items = ArrayList<Post>()
        items.add(Post("https://images.unsplash.com/photo-1626497361649-81cc097e9bfd?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OXx8bXVzbGltfGVufDB8MnwwfHw%3D&auto=format&fit=crop&w=500&q=60"))
        items.add(Post("https://images.unsplash.com/photo-1626497361649-81cc097e9bfd?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OXx8bXVzbGltfGVufDB8MnwwfHw%3D&auto=format&fit=crop&w=500&q=60"))
        items.add(Post("https://images.unsplash.com/photo-1626497361649-81cc097e9bfd?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OXx8bXVzbGltfGVufDB8MnwwfHw%3D&auto=format&fit=crop&w=500&q=60"))
        return items
    }
}