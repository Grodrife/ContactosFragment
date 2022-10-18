package com.gabrielrf.contactosfragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.gabrielrf.contactosfragment.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {

    companion object{
        const val EXTRA_CONTACTO = "DetailActivity:Contacto"

        fun create(contacto: Contacto): DetailFragment =
            DetailFragment().apply {
                arguments = bundleOf(EXTRA_CONTACTO to contacto)
            }
    }

    private lateinit var botonLlamada: Button
    private lateinit var botonEmail: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)

        val contacto = arguments?.getParcelable<Contacto>(EXTRA_CONTACTO) ?: throw IllegalStateException()

        binding.btnllamar.setOnClickListener{
            val intentLlamada = Intent(Intent.ACTION_DIAL)
            intentLlamada.data = Uri.parse("tel:${contacto.phonenumber.toString()}")
            startActivity(intentLlamada)
        }

        binding.btnEmail.setOnClickListener{
            val intentEmail = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:${contacto.email}"))
            intentEmail.putExtra("subject","my subject")
            intentEmail.putExtra("body", "my message")
            startActivity(intentEmail)
        }

        (requireActivity() as AppCompatActivity).supportActionBar?.title = contacto.name

        binding.name.text = contacto.name

        binding.imageView.loadUrl(contacto.imagen)
    }
}