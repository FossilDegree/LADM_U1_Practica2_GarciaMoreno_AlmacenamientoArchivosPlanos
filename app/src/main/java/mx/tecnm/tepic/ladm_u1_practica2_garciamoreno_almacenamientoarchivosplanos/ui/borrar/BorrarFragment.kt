package mx.tecnm.tepic.ladm_u1_practica2_garciamoreno_almacenamientoarchivosplanos.ui.borrar

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.tecnm.tepic.ladm_u1_practica2_garciamoreno_almacenamientoarchivosplanos.R

class BorrarFragment : Fragment() {

    companion object {
        fun newInstance() = BorrarFragment()
    }

    private lateinit var viewModel: BorrarViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.borrar_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BorrarViewModel::class.java)
        // TODO: Use the ViewModel
    }

}