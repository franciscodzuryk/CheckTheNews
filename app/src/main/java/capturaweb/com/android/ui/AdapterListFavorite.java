package capturaweb.com.android.ui;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import capturaweb.com.android.capturaweb.R;

public class AdapterListFavorite extends BaseAdapter{

    private Context context;
    private final List<String> lista;

    public AdapterListFavorite(Context context, List<String> lista) {
        super();
        this.context = context;
        this.lista = lista;
    }

    public AdapterListFavorite(Context context) {
        super();
        this.context = context;
        lista = null;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    // Devuelve Layout de la posicion i
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vista = view;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vista = inflater.inflate(R.layout.activity_favorite_list, viewGroup, false);
        }
        TextView texto = (TextView) vista.findViewById(R.id.textView_url_favorite);
        texto.setText(lista.get(i));
        return vista;
    }
}
