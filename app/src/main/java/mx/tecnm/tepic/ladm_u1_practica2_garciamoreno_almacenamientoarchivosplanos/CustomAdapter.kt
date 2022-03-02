package mx.tecnm.tepic.ladm_u1_practica2_garciamoreno_almacenamientoarchivosplanos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.tecnm.tepic.ladm_u1_practica2_garciamoreno_almacenamientoarchivosplanos.arreglos.arrAutor
import mx.tecnm.tepic.ladm_u1_practica2_garciamoreno_almacenamientoarchivosplanos.arreglos.arrID
import mx.tecnm.tepic.ladm_u1_practica2_garciamoreno_almacenamientoarchivosplanos.arreglos.arrTitulo

class CustomAdapter:RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    var titulos = arrayListOf<String>()
    var autores = arrayListOf<String>()
    var id = arrayListOf<String>()


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout,viewGroup,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text=arrTitulo[i]
        viewHolder.itemDetail.text=arrAutor[i]
        viewHolder.itemID.text=arrID[i]
        viewHolder.itemImage.setImageResource(R.drawable.ic_baseline_book_24)
    }

    override fun getItemCount(): Int {
        return arrTitulo.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var itemImage:ImageView
        var itemTitle:TextView
        var itemDetail:TextView
        var itemID:TextView
        init{
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemDetail = itemView.findViewById(R.id.item_detail)
            itemID = itemView.findViewById(R.id.item_id)
        }
    }
}