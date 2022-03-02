package mx.tecnm.tepic.ladm_u1_practica2_garciamoreno_almacenamientoarchivosplanos.ui.slideshow

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mx.tecnm.tepic.ladm_u1_practica2_garciamoreno_almacenamientoarchivosplanos.arreglos.arrAutor
import mx.tecnm.tepic.ladm_u1_practica2_garciamoreno_almacenamientoarchivosplanos.arreglos.arrID
import mx.tecnm.tepic.ladm_u1_practica2_garciamoreno_almacenamientoarchivosplanos.arreglos.arrTitulo
import mx.tecnm.tepic.ladm_u1_practica2_garciamoreno_almacenamientoarchivosplanos.databinding.FragmentSlideshowBinding
import java.io.OutputStreamWriter

class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.buscar.setOnClickListener {
            if(binding.id.text.toString()!="") {
                if(arrID.indexOf(binding.id.text.toString())!=-1) {
                    var x = arrID.indexOf(binding.id.text.toString())
                    binding.titulo.setText(arrTitulo[x])
                    binding.autor.setText(arrAutor[x])
                    binding.actualizar.setEnabled(true)
                    binding.borrar.setEnabled(true)
                }else{
                    AlertDialog.Builder(requireContext())
                        .setMessage("Libro no encontrado")
                        .setPositiveButton("Aceptar",{i,d->})
                        .show()
                }
            }else{
                AlertDialog.Builder(requireContext())
                    .setMessage("Ingrese un ID")
                    .setPositiveButton("Aceptar",{i,d->})
                    .show()
            }
        }
        binding.actualizar.setOnClickListener {
            var x = arrID.indexOf(binding.id.text.toString())
            arrID[x]= binding.id.text.toString()
            arrTitulo[x]= binding.titulo.text.toString()
            arrAutor[x]=binding.autor.text.toString()
            guardarArchivo()
            binding.actualizar.setEnabled(false)
            binding.borrar.setEnabled(false)
        }
        binding.borrar.setOnClickListener {
            var x = arrID.indexOf(binding.id.text.toString())
            arrID[x]= ""
            arrTitulo[x]= ""
            arrAutor[x]=""


            guardarArchivo()
            binding.actualizar.setEnabled(false)
            binding.borrar.setEnabled(false)
        }

        return root
    }
    private fun guardarArchivo() {

        try {
            var archivo = OutputStreamWriter(
                requireActivity().openFileOutput(
                    "archivo.txt",
                    Context.MODE_PRIVATE
                )
            )
            //var cadena = binding.id.text.toString()+"&&"+binding.titulo.text.toString()+"&&"+binding.autor.text.toString()

            //archivo.write(cadena)
            var cadena = ""
            (0..arrID.size - 1).forEach {

                cadena += arrID[it] + "&&" + arrTitulo[it] + "&&" + arrAutor[it] + "\n"

            }

            archivo.write(cadena)
            archivo.flush()
            archivo.close()
            binding.id.setText("")
            binding.titulo.setText("")
            binding.autor.setText("")
            androidx.appcompat.app.AlertDialog.Builder(requireContext())
                .setMessage("Actualizado")
                .setPositiveButton("Aceptar",{i,d->})
                .show()
        } catch (e: Exception) {


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}