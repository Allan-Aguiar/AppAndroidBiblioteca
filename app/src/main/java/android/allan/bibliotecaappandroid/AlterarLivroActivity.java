package android.allan.bibliotecaappandroid;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Allan on 16/06/2016.
 */
public class AlterarLivroActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        final Modelo_Livros livros = new Modelo_Livros();
        final DAOBanco banco = new DAOBanco(AlterarLivroActivity.this.getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterarlivro);
        final EditText attlivro = (EditText) findViewById(R.id.editText_attlivro);
        Button atlivro = (Button) findViewById(R.id.button_attlivro);

        atlivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Modelo_Livros auxiliar = new Modelo_Livros();

                double cd = Double.parseDouble(attlivro.getText().toString());
                Log.d("TEXT CD", String.valueOf(cd));

                livros.setCodigo(cd);
                Log.d("LIVROS GETCod", String.valueOf(livros.getCodigo()));

                auxiliar = banco.buscarLivroCodigo(livros);
                Log.d("aux Cod", String.valueOf(auxiliar.getCodigo()));

                if (auxiliar.getCodigo() == cd) {
                    callattform(auxiliar);
                }
                else {
                    mostrarFalha("Alterar Produto ", "Livro não encontrado!");
                }
            }
        });
    }

    public void callattform(Modelo_Livros l) {
        Intent intent = new Intent(this, AlterarLivroFormActivity.class);
                Log.d("CODIGO prebundle", String.valueOf(l.getCodigo()));
        intent.putExtra("CODIGO",l.getCodigo());
        AlterarLivroActivity.this.startActivity(intent);
    }
    public void mostrarFalha (String titulo, String mensagem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(titulo);
        builder.setMessage(mensagem);
        builder.show();
    }
}
