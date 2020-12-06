package com.alex.registryemployers.ui.about

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alex.registryemployers.R
import androidx.databinding.DataBindingUtil


const val KEY_SHAREBODY = "key_shareBody"
class AboutFragment : Fragment(),LifecycleObserver {

    private lateinit var aboutViewModel: AboutViewModel

    private var shareBody = "Application about information"

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        aboutViewModel =
                ViewModelProvider(this).get(AboutViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_about, container, false)
        val textView: TextView = root.findViewById(R.id.text_about)
        val shareButton:TextView = root.findViewById(R.id.share)
        aboutViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
            shareButton.setOnClickListener{
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "type/palin"
                val shareSub = textView.text
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareBody)
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareSub)
                startActivity(Intent.createChooser(shareIntent, "Share about us"))
            }
        })
        if(savedInstanceState!= null){
            shareBody = savedInstanceState.getString(KEY_SHAREBODY).toString()
        }
        return root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_SHAREBODY, shareBody)
    }
}