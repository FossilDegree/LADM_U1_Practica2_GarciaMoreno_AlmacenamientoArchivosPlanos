package mx.tecnm.tepic.ladm_u1_practica2_garciamoreno_almacenamientoarchivosplanos.ui.home

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mx.tecnm.tepic.ladm_u1_practica2_garciamoreno_almacenamientoarchivosplanos.arreglos.arrAutor
import mx.tecnm.tepic.ladm_u1_practica2_garciamoreno_almacenamientoarchivosplanos.arreglos.arrID
import mx.tecnm.tepic.ladm_u1_practica2_garciamoreno_almacenamientoarchivosplanos.arreglos.arrTitulo
import mx.tecnm.tepic.ladm_u1_practica2_garciamoreno_almacenamientoarchivosplanos.databinding.FragmentHomeBinding
import java.io.OutputStreamWriter
import java.io.OutputStream
import java.io.BufferedReader
import java.io.File
import java.nio.file.Files.deleteIfExists

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.guardar.setOnClickListener {
            var x = arrID.indexOfFirst { it == "" }
            if(x!=-1) {
                if(!arrID.contains(binding.id.text.toString())) {
                    arrID[x] = binding.id.text.toString()
                    arrTitulo[x] = binding.titulo.text.toString()
                    arrAutor[x] = binding.autor.text.toString()
                    guardarArchivo()
                }else{
                    AlertDialog.Builder(requireContext())
                        .setMessage("Libro ya existente")
                        .setPositiveButton("Aceptar",{d,i->})
                        .show()
                }
            }else{
                AlertDialog.Builder(requireContext())
                    .setMessage("Librería llena")
                    .setPositiveButton("Aceptar",{d,i->})
                    .show()
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun guardarArchivo(){

        try {
            var archivo = OutputStreamWriter(requireActivity().openFileOutput("archivo.txt", MODE_PRIVATE))
            //var cadena = binding.id.text.toString()+"&&"+binding.titulo.text.toString()+"&&"+binding.autor.text.toString()

            //archivo.write(cadena)
            var cadena=""
            (0..arrID.size-1).forEach{

                cadena+=arrID[it]+"&&"+ arrTitulo[it]+"&&"+ arrAutor[it]+"\n"

            }

            archivo.write(cadena)
            archivo.flush()
            archivo.close()
            binding.id.setText("")
            binding.titulo.setText("")
            binding.autor.setText("")
            AlertDialog.Builder(requireContext())
                .setMessage("SE GUARDÓ")
                .setPositiveButton("Aceptar",{d,i->})
                .show()
        }catch(e:Exception){


        }
    }
}