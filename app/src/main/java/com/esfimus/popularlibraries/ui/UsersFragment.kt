package com.esfimus.popularlibraries.ui

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.esfimus.popularlibraries.App
import com.esfimus.popularlibraries.databinding.FragmentUsersBinding
import com.esfimus.popularlibraries.mvp.model.GithubUsersRepo
import com.esfimus.popularlibraries.mvp.presenter.UsersPresenter
import com.esfimus.popularlibraries.mvp.view.UsersView
import com.google.android.material.snackbar.Snackbar
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private var _ui: FragmentUsersBinding? = null
    private val ui get() = _ui!!
    var adapter: RecyclerAdapter? = null
    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(GithubUsersRepo(), App.instance.router)
    }

    private val openLauncher =
        registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
            try {
                uri?.let { openFile(it) }
            } catch (e: Exception) {
                snackMessage("Cannot open file")
            }
        }

    private val saveLauncher =
        registerForActivityResult(ActivityResultContracts.CreateDocument("image/png")) { uri ->
            try {
                uri?.let { saveFile(it) }
            } catch (e: Exception) {
                snackMessage("Cannot save file")
            }
        }

    private var bitmap: Bitmap? = null

    companion object { fun newInstance() = UsersFragment() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentUsersBinding.inflate(inflater, container, false).also {
            _ui = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _ui = null
    }

    override fun init() {
        with (ui) {
            recyclerUsers.layoutManager = LinearLayoutManager(context)
            adapter = RecyclerAdapter(presenter.usersListPresenter)
            recyclerUsers.adapter = adapter

            openButton.setOnClickListener {
                openLauncher.launch(arrayOf("image/jpeg"))
            }

            convertButton.setOnClickListener {
                saveLauncher.launch("${timeStamp()}.png")
            }
        }
    }

    @Suppress("DEPRECATION")
    private fun openFile(uri: Uri) {
        ui.image.setImageURI(uri)

        try {
            bitmap = if (Build.VERSION.SDK_INT >= 28) {
                val source = ImageDecoder.createSource(requireActivity().contentResolver, uri)
                ImageDecoder.decodeBitmap(source)
            } else {
                MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, uri)
            }
        } catch (e: Exception) {
            snackMessage("Conversion problem")
        }
    }

    private fun saveFile(uri: Uri) {
        requireActivity().contentResolver.openOutputStream(uri)?.use {
            presenter.reactiveConvertToPng(bitmap!!, it)
        } ?: throw IllegalStateException("Cannot open output stream")
    }

    private fun timeStamp(): String {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-hh-mm-ss"))
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()

    private fun snackMessage(text: String, length: Int = Snackbar.LENGTH_SHORT) {
        Snackbar.make(requireView(), text, length).show()
    }
}