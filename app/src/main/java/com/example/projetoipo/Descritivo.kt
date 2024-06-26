package com.example.projetoipo

import android.content.DialogInterface
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.projetoipo.databinding.ActivityDescritivoBinding

class Descritivo : AppCompatActivity() {
    private lateinit var binding: ActivityDescritivoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding = ActivityDescritivoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retornoGraduacaoNome = intent.getStringExtra("graduacaoNome").toString()
        val retornoCrbm = intent.getStringExtra("crbm").toString()
        val retornoObm = intent.getStringExtra("obm").toString()
        val retornoData = intent.getStringExtra("data").toString()
        val retornoHora = intent.getStringExtra("hora").toString()
        val retornoNatureza = intent.getStringExtra("natureza").toString()
        val retornoSubNatureza = intent.getStringExtra("subNatureza").toString()
        val retornoCidade = intent.getStringExtra("cidade").toString()
        val retornoLogradouro = intent.getStringExtra("logradouro").toString()
        val retornoBairro = intent.getStringExtra("bairro").toString()
        val retornoComplemento = intent.getStringExtra("complemento").toString()
        val retornoCbAcionado = intent.getStringExtra("cbAcionado").toString()
        val retornoVtrEmpenhada = intent.getStringExtra("vtrEmpenhada").toString()
        val retornoEfetivo = intent.getStringExtra("efetivo").toString()

        val retornoVitIlesa = intent.getIntExtra("vitIlesa", 0)
        val retornoVitCod1 = intent.getIntExtra("vitCod1", 0)
        val retornoVitCod2 = intent.getIntExtra("vitCod2", 0)
        val retornoVitCod3 = intent.getIntExtra("vitCod3", 0)
        val retornoVitCod4 = intent.getIntExtra("vitCod4", 0)
        val retornoTotalVit = intent.getIntExtra("totalVit", 0)

        val retornoObsservacaoVit =
            intent?.extras?.getString("observacaoVit")?.takeIf { it.isNotBlank() }
                ?: "Não houve | Não se aplica.."

        binding.btnAvancar.setOnClickListener {

            val meioAmbiente = binding.edtDanosMeioAmbiente.text.toString()
            val danosPropriedade = binding.edtDanosPropriedade.text.toString()
            val cenario = binding.edtCenario.text.toString()
            val desdobramento = binding.edtDesdobramento.text.toString()

            val dialogo = AlertDialog.Builder(this)

                .setMessage(getString(R.string.msg_adicionarApoio))

                .setPositiveButton("Sim", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        val intent = Intent(this@Descritivo, Apoio::class.java)
                        intent.putExtra("graduacaoNome", retornoGraduacaoNome)
                        intent.putExtra("crbm", retornoCrbm)
                        intent.putExtra("obm", retornoObm)
                        intent.putExtra("data", retornoData)
                        intent.putExtra("hora", retornoHora)
                        intent.putExtra("natureza", retornoNatureza)
                        intent.putExtra("subNatureza", retornoSubNatureza)
                        intent.putExtra("cidade", retornoCidade)
                        intent.putExtra("logradouro", retornoLogradouro)
                        intent.putExtra("bairro", retornoBairro)
                        intent.putExtra("complemento", retornoComplemento)
                        intent.putExtra("cbAcionado", retornoCbAcionado)
                        intent.putExtra("vtrEmpenhada", retornoVtrEmpenhada)
                        intent.putExtra("efetivo", retornoEfetivo)
                        intent.putExtra("vitIlesa", retornoVitIlesa)
                        intent.putExtra("vitCod1", retornoVitCod1)
                        intent.putExtra("vitCod2", retornoVitCod2)
                        intent.putExtra("vitCod3", retornoVitCod3)
                        intent.putExtra("vitCod4", retornoVitCod4)
                        intent.putExtra("totalVit", retornoTotalVit)
                        intent.putExtra("obsservacaoVit", retornoObsservacaoVit)
                        intent.putExtra("meioAmbiente", meioAmbiente)
                        intent.putExtra("danosPropriedade", danosPropriedade)
                        intent.putExtra("cenario", cenario)
                        intent.putExtra("desdobramento", desdobramento)

                        startActivity(intent)
                    }
                })

                //add um botao salvar do lado do botao ok
                .setNegativeButton("Nao") { dialago, which ->
                    val intent = Intent(this, Resumo::class.java)
                    intent.putExtra("graduacaoNome", retornoGraduacaoNome)
                    intent.putExtra("crbm", retornoCrbm)
                    intent.putExtra("obm", retornoObm)
                    intent.putExtra("data", retornoData)
                    intent.putExtra("hora", retornoHora)
                    intent.putExtra("natureza", retornoNatureza)
                    intent.putExtra("subNatureza", retornoSubNatureza)
                    intent.putExtra("cidade", retornoCidade)
                    intent.putExtra("logradouro", retornoLogradouro)
                    intent.putExtra("bairro", retornoBairro)
                    intent.putExtra("complemento", retornoComplemento)
                    intent.putExtra("cbAcionado", retornoCbAcionado)
                    intent.putExtra("vtrEmpenhada", retornoVtrEmpenhada)
                    intent.putExtra("efetivo", retornoEfetivo)
                    intent.putExtra("vitIlesa", retornoVitIlesa)
                    intent.putExtra("vitCod1", retornoVitCod1)
                    intent.putExtra("vitCod2", retornoVitCod2)
                    intent.putExtra("vitCod3", retornoVitCod3)
                    intent.putExtra("vitCod4", retornoVitCod4)
                    intent.putExtra("totalVit", retornoTotalVit)
                    intent.putExtra("obsservacaoVit", retornoObsservacaoVit)
                    intent.putExtra("meioAmbiente", meioAmbiente)
                    intent.putExtra("danosPropriedade", danosPropriedade)
                    intent.putExtra("cenario", cenario)
                    intent.putExtra("desdobramento", desdobramento)
                    startActivity(intent)
                }
                .create()
                .show()

        }
    }
}