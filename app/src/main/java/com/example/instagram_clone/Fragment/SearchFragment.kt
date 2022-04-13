package com.example.instagram_clone.Fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram_clone.R
import com.example.instagram_clone.adapter.SearchAdapter
import com.example.instagram_clone.model.User

/**
 * SearchFragment ,all registered users can be found searching keyword and followed
 */

class SearchFragment : BaseFragment() {

    val TAG = SearchFragment::class.java.simpleName
    lateinit var recyclerView: RecyclerView
    var items = ArrayList<User>()
    var users = ArrayList<User>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {

        recyclerView = view.findViewById(R.id.rv_search)
        recyclerView.setLayoutManager(GridLayoutManager(activity,1))
        val edt_search = view.findViewById<EditText>(R.id.et_search)
        edt_search.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val keyword = s.toString().trim()
                usersByKeyWord(keyword)
            }

            override fun afterTextChanged(p0: Editable?) {}

        })
        loadUsers()
        refreshAdapter(items)
    }

    private fun refreshAdapter(items: ArrayList<User>) {

        val adapter = SearchAdapter(this,items)
        recyclerView.adapter =adapter
    }

    fun usersByKeyWord(keyword:String){
        if (keyword.isEmpty())
            refreshAdapter(items)
        users.clear()
        for (user in items)
            if (user.fullname.toLowerCase().startsWith(keyword.toLowerCase()))
                users.add(user)
        refreshAdapter(users)
    }
    private fun loadUsers():ArrayList<User>{
        items= ArrayList<User>()
        items.add(User("Sherzod","sherzodjurabekov07@gmail.com"))
        items.add(User("Sarvar","sarvarkhalmatov4707@gmail.com"))
        items.add(User("Tohir","tohirrahmatullayev03357@gmail.com"))
        items.add(User("Uchqun","uchqunboy7777@gmail.com"))
        items.add(User("Sherzod","sherzodjurabekov07@gmail.com"))
        items.add(User("Sarvar","sarvarkhalmatov4707@gmail.com"))
        items.add(User("Tohir","tohirrahmatullayev03357@gmail.com"))
        items.add(User("Uchqun","uchqunboy7777@gmail.com"))
        items.add(User("Sherzod","sherzodjurabekov07@gmail.com"))
        items.add(User("Sarvar","sarvarkhalmatov4707@gmail.com"))
        items.add(User("Tohir","tohirrahmatullayev03357@gmail.com"))
        items.add(User("Uchqun","uchqunboy7777@gmail.com"))
        items.add(User("Sherzod","sherzodjurabekov07@gmail.com"))
        items.add(User("Sarvar","sarvarkhalmatov4707@gmail.com"))
        items.add(User("Tohir","tohirrahmatullayev03357@gmail.com"))
        items.add(User("Uchqun","uchqunboy7777@gmail.com"))
        return items
    }
}