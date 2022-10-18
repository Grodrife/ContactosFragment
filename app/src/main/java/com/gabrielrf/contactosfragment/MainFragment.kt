package com.gabrielrf.contactosfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.gabrielrf.contactosfragment.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMainBinding.bind(view).apply {
            recycler.adapter = ContactosAdapter(contactos) { contacto ->
                navigateTo(contacto)
            }
        }
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "ContactosFragment"
    }

    private fun navigateTo(contacto: Contacto){
        parentFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.slide_right_in,
                R.anim.slide_left_out,
                R.anim.slide_left_in,
                R.anim.slide_right_out)
            .replace(R.id.fragment_container_view, DetailFragment.create(contacto))
            .setReorderingAllowed(true)
            .addToBackStack(null)
            .commit()

    }
}
private val contactos = (1..100).map {
    Contacto(
        "https://loremflickr.com/240/320/profilepic?lock=$it",
        "Nombre $it",
        "$it$it$it$it",
        "Email $it"
    )
}